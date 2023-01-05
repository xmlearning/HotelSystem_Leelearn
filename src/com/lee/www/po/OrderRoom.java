package com.lee.www.po;

import com.lee.www.po.abs.Order;

/**
 * @author ming
 * @date 2023-01-02 21:04
 * @description 用于存储房间订单信息
 */
public class OrderRoom extends Order {
    private String roomId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
