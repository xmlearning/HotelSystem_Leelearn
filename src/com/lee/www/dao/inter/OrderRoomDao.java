package com.lee.www.dao.inter;

import com.lee.www.po.OrderRoom;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 15:44
 * @description 负责房间订单的CRUD
 */
public interface OrderRoomDao {
    // 通过订单编号检查一个订单是否已经存在
    boolean isExist(String orderNumber);
    // 添加一个订单到数据库
    boolean addOrderRoom(OrderRoom order);
    // 根据订单编号查询一个订单的所有信息
    OrderRoom getOrderRoom(String orderNum);
    // 返回该订单编号对应的id
    String getId(String orderNum);
    // 获取表中所有订单的信息，并以LinkedList的形式返回
    LinkedList<OrderRoom> getAllOrderRooms();
    // 将该id对应的订单从数据库中删除
    boolean deleteById(String id);
    // 将该订单编号对应的订单从数据库中删除
    boolean deleteByNumber(String orderNumber);
    // 将一个订单对象对象从数据库中删除
    boolean delete(OrderRoom order);
    // 更新一个订单的信息
    boolean update(OrderRoom order);
    // 通过一个用户id返回该用户的所有订单
    LinkedList<OrderRoom> listByUserId(String userId);
}
