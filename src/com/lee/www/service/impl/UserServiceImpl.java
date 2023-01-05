package com.lee.www.service.impl;

import com.lee.www.dao.inter.UserDao;
import com.lee.www.po.User;
import com.lee.www.service.Result;
import com.lee.www.service.inter.UserService;
import com.lee.www.util.BeanFactory;
import com.lee.www.util.BeanUtils;
import com.lee.www.util.ServiceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedList;

import static com.lee.www.service.constant.Status.*;
import static com.lee.www.util.Md5Utils.getDigest;
import static com.lee.www.util.ServiceUtils.*;
import static com.lee.www.util.UUIDUtils.getUUID;
import static com.lee.www.util.UploadUtils.uploadPhoto;

/**
 * @author ming
 * @date 2023-01-03 21:39
 * @description 负责用户相关的业务逻辑，包括用户注册登陆的功能
 */
public class UserServiceImpl implements UserService {

    private UserDao dao = (UserDao) BeanFactory.getBean(BeanFactory.DaoType.UserDao);

    // 用于添加一个用户
    @Override
    public Result add(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) BeanUtils.toObject(req.getParameterMap(), User.class);
        if (dao.isExist(user.getName())) {
            return setResult(user, NOT_FOUNT);
        }
        if (!isValidUserInfo(user)) {
            return setResult(user, DATA_ILLEGAL);
        }
        user.setId(getUUID());
        user.setPassword(getDigest(user.getPassword()));
        uploadPhoto(req, user);
        if (dao.addUser(user)) {
            return setResult(user, SUCCESS);
        }
        return setResult(ERROR);
    }

    // 负责用户的注册功能
    @Override
    public Result regist(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) BeanUtils.toObject(req.getParameterMap(), User.class);
        if (!isValidRegist(user)) {
            return setResult(DATA_ILLEGAL);
        }
        if (dao.isExist(user.getName())) {
            return setResult(ACCOUNT_ALREADY_EXIST);
        }
        user.setPassword(getDigest(user.getPassword()));
        user.setId(getUUID());
        if (dao.addUser(user)) {
            return ServiceUtils.setResult(user, SUCCESS);
        }
        return setResult(ERROR);
    }

    // 负责用户的登陆功能
    @Override
    public Result login(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) BeanUtils.toObject(req.getParameterMap(), User.class);
        if (!dao.isExist(user.getName())) {
            return setResult(NOT_FOUNT);
        }
        if (!dao.getPassword(user.getName()).equals(getDigest(user.getPassword()))) {
            return setResult(PASSWORD_INCORRECT);
        }
        user.setType(dao.getUser(user.getName()).getType());
        return ServiceUtils.setResult(user, SUCCESS);
    }

    // 负责更新用户信息的功能
    @Override
    public Result updateInfo(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) BeanUtils.toObject(req.getParameterMap(), User.class);
        if (!isValidUserInfo(user)) {
            return ServiceUtils.setResult(user, DATA_ILLEGAL);
        }
        uploadPhoto(req, user);
        if (dao.update(user)) {
            return ServiceUtils.setResult(user, SUCCESS);
        }
        return setResult(ERROR);
    }

    // 负责用户更新登陆密码的功能
    @Override
    public Result updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) BeanUtils.toObject(req.getParameterMap(), User.class);
        String newPwd = req.getParameter("newPwd");

        if (!isValidPwd(newPwd)) {
            return ServiceUtils.setResult(user, DATA_ILLEGAL);
        }
        if (!dao.getPassword(user.getName()).equals(getDigest(user.getPassword()))) {
            return ServiceUtils.setResult(user, PASSWORD_INCORRECT);
        }
        user = dao.getUser(user.getName());
        if (user == null) {
            return ServiceUtils.setResult(user, NOT_FOUNT);
        }
        user.setPassword(getDigest(newPwd));
        if (dao.updateAll(user)) {
            return ServiceUtils.setResult(user, SUCCESS);
        }
        return setResult(ERROR);
    }

    // 负责用户更新支付密码的功能
    @Override
    public Result updatePayPwd(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) BeanUtils.toObject(req.getParameterMap(), User.class);
        String newPwd = req.getParameter("newPwd");
        String oldPwd = user.getPayPwd();
        user = dao.getUser(user.getName());
        if (!isValidPwd(newPwd)) {
            return ServiceUtils.setResult(user, DATA_ILLEGAL);
        }
        if (user == null) {
            return ServiceUtils.setResult(user, NOT_FOUNT);
        }
        if (!user.getPayPwd().equals(getDigest(oldPwd))) {
            return ServiceUtils.setResult(user, PASSWORD_INCORRECT);
        }
        user.setPayPwd(getDigest(newPwd));
        if (dao.updateAll(user)) {
            return ServiceUtils.setResult(user, SUCCESS);
        }
        return setResult(ERROR);
    }
    // 负责返回用户的个人信息
    @Override
    public Result find(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) BeanUtils.toObject(req.getParameterMap(), User.class);

        user = dao.getUser(user.getName());

        if (user != null) {
            return ServiceUtils.setResult(user, SUCCESS);
        } else {

            return ServiceUtils.setResult(user, NOT_FOUNT);
        }
    }

    @Override
    public Result listAll(HttpServletRequest req, HttpServletResponse resp) {
        LinkedList<User> users = dao.getAllUsers();
        if (users != null && users.size() > 0) {
            return setUserResult(users, SUCCESS);
        }

        return setResult(ERROR);
    }
}
