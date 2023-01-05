package com.lee.www.service.inter;

import com.lee.www.service.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ming
 * @date 2023-01-03 21:33
 * @description 负责提供房间相关的服务
 */
public interface RoomService {
    Result add(HttpServletRequest req, HttpServletResponse resp);

    Result delete(HttpServletRequest req, HttpServletResponse resp);

    Result update(HttpServletRequest req, HttpServletResponse resp);

    Result find(HttpServletRequest req, HttpServletResponse resp);

    // 返回所有的房间
    Result listAll(HttpServletRequest req, HttpServletResponse resp);

    // 通过名称模糊查询相关的房间
    Result listByName(HttpServletRequest req, HttpServletResponse resp);
}
