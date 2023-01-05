package com.lee.www.service.impl;

import com.lee.www.dao.inter.RemarkDao;
import com.lee.www.po.Remark;
import com.lee.www.service.Result;
import com.lee.www.service.inter.RemarkService;
import com.lee.www.util.BeanFactory;
import com.lee.www.util.BeanUtils;
import com.lee.www.vo.PagesVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;

import static com.lee.www.service.constant.Status.*;
import static com.lee.www.util.ServiceUtils.setResult;
import static com.lee.www.util.StringUtils.toLegalText;
import static com.lee.www.util.UUIDUtils.getUUID;

/**
 * @author ming
 * @date 2023-01-04 10:17
 */
public class RemarkServiceImpl implements RemarkService {

    private RemarkDao dao = (RemarkDao) BeanFactory.getBean(BeanFactory.DaoType.RemarkDao);

    // 添加记录
    @Override
    public Result add(HttpServletRequest req, HttpServletResponse resp) {
        Remark remark = (Remark) BeanUtils.toObject(req.getParameterMap(), Remark.class);
        String user = remark.getUserName();
        if (dao.getUserRemarkCount(user) > 20) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(dao.listAll());
            return new Result(DATA_TOO_MUCH, vo);
        }
        remark.setId(getUUID());
        /**
         * 使用toLegalText过滤非法字符
         */
        remark.setRemark(toLegalText(remark.getRemark()));
        if (remark.getRemark().trim().isEmpty()) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(dao.listAll());
            return new Result(DATA_ILLEGAL, vo);
        }
        /**
         * 此处将留言中的用户名字段修改为用户的昵称
         */
        String userName = remark.getUserName();
        remark.setUserName(userName);
        if (dao.add(remark)) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(dao.listAll());
            return new Result(SUCCESS, vo);
        }
        return setResult(ERROR);
    }

    // 返回所有评论
    @Override
    public Result listAll(HttpServletRequest req, HttpServletResponse resp) {
        LinkedList<Remark> remarks = dao.listAll();
        if (remarks != null) {
            PagesVo vo = new PagesVo();
            vo.setRemarks(remarks);
            return new Result(SUCCESS, vo);
        }
        return setResult(ERROR);
    }



}
