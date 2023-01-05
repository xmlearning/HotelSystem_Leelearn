package com.lee.www.service.inter;

import com.lee.www.service.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ming
 * @date 2023-01-03 21:33
 * @description 负责房间预约订单的服务
 */
public interface OrderRoomService {
    Result add(HttpServletRequest req, HttpServletResponse resp);

    Result delete(HttpServletRequest req, HttpServletResponse resp);


    Result find(HttpServletRequest req, HttpServletResponse resp);

    // 返回该用户用户名对应的订单
    Result listByUserName(HttpServletRequest req, HttpServletResponse resp);
}
