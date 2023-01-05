package com.lee.www.service.inter;

import com.lee.www.service.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ming
 * @date 2023-01-03 21:33
 * @description 负责用户相关的业务逻辑，包括用户注册登陆的功能
 */
public interface UserService {

    // 用于添加一个用户
    Result add(HttpServletRequest req, HttpServletResponse resp);

    // 负责用户的注册功能
    Result regist(HttpServletRequest req, HttpServletResponse resp);

    // 负责用户的登陆功能
    Result login(HttpServletRequest req, HttpServletResponse resp);

    // 负责更新用户信息的功能
    Result updateInfo(HttpServletRequest req, HttpServletResponse resp);

    // 负责用户更新登陆密码的功能
    Result updatePwd(HttpServletRequest req, HttpServletResponse resp);

    // 负责用户更新支付密码的功能
    Result updatePayPwd(HttpServletRequest req, HttpServletResponse resp);

    // 负责返回用户的个人信息
    Result find(HttpServletRequest req, HttpServletResponse resp);

    // 负责查找用户
    Result listAll(HttpServletRequest req, HttpServletResponse resp);


}
