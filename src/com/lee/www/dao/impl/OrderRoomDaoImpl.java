package com.lee.www.dao.impl;

import com.lee.www.dao.inter.OrderRoomDao;
import com.lee.www.po.OrderRoom;
import com.lee.www.util.JdbcUtils;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 19:56
 * @description 负责房间订单的CRUD
 */
public class OrderRoomDaoImpl extends BaseDaoImpl implements OrderRoomDao {

    // 本类操作的数据库表名
    private final String TABLE_NAME = " " + JdbcUtils.getTableName(OrderRoom.class) + " ";
    // 表中所有字段对应的查询语句
    private final String ALL_FIELD_NAME = " id,number,user_id,room_id,start_time,end_time,amount,remark,"
            + "status,gmt_create,gmt_modified ";

    // 通过订单编号检查一个订单是否已经存在
    @Override
    public boolean isExist(String orderNumber) {
        return getOrderRoom(orderNumber) != null;
    }
    // 添加一个订单到数据库
    @Override
    public boolean addOrderRoom(OrderRoom order) {
        if (order == null || order.getNumber() == null) {
            return false;
        }
        return super.insert(order) == 1;
    }
    // 根据订单编号查询一个订单的所有信息
    @Override
    public OrderRoom getOrderRoom(String orderNum) {
        if (orderNum == null) {
            return null;
        }
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where number  = ?";
        return (OrderRoom) super.queryObject(sql, new Object[]{orderNum}, OrderRoom.class);
    }

    // 返回该订单编号对应的id
    @Override
    public String getId(String orderNum) {
        String sql = "select id from " + TABLE_NAME + " where number = ?";
        return (String) super.queryValue(sql, new Object[]{orderNum});
    }

    // 获取表中所有订单的信息，并以LinkedList的形式返回
    @Override
    public LinkedList<OrderRoom> getAllOrderRooms() {
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME;
        return toOrderList(sql, null);
    }

    // 通过一个用户id返回该用户的所有订单

    @Override
    public LinkedList<OrderRoom> listByUserId(String userId) {
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where user_id = ?";
        return toOrderList(sql, new String[]{userId});
    }

    private LinkedList<OrderRoom> toOrderList(String sql, Object[] params) {
        LinkedList<Object> list = super.queryList(sql, params, OrderRoom.class);
        LinkedList<OrderRoom> orders = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            OrderRoom order = (OrderRoom) list.get(i);
            orders.add(order);
        }
        return orders;
    }


    // 将该id对应的订单从数据库中删除
    @Override
    public boolean deleteById(String id) {
        if (id == null) {
            return false;
        }
        OrderRoom orderRoom = new OrderRoom();
        orderRoom.setId(id);
        return super.delete(orderRoom) == 1;
    }

    // 将该订单编号对应的订单从数据库中删除
    @Override
    public boolean deleteByNumber(String orderNumber) {
        return deleteByNumber(getId(orderNumber));
    }
    // 将一个订单对象对象从数据库中删除
    @Override
    public boolean delete(OrderRoom order) {
        return deleteById(order.getId());
    }
    // 更新一个订单的信息
    @Override
    public boolean update(OrderRoom order) {
        if (order == null){
            return false;
        }
        return super.update(order) == 1;
    }
}
