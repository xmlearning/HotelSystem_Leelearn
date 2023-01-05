package com.lee.www.service.inter;

import com.lee.www.service.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ming
 * @date 2023-01-03 21:33
 * @description 负责评论的CRUD
 */
public interface RemarkService {
    // 添加记录
    Result add(HttpServletRequest req, HttpServletResponse resp);

    // 返回所有评论
    Result listAll(HttpServletRequest req, HttpServletResponse resp);
}
