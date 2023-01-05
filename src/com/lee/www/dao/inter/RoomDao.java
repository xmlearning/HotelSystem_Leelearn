package com.lee.www.dao.inter;

import com.lee.www.po.Room;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 15:53
 * @decription  负责对房间进行CRUD操作
 */
public interface RoomDao {
    // 通过房间编号检查一个房间是否已经存在
    boolean isExist(String roomNumber);
    // 添加一个房间到数据库
    boolean addRoom(Room room);
    // 根据房间编号查询一个房间的所有信息
    Room getRoom(String roomNum);
    // 根据房间id查询一个房间的所有信息
    Room getRoomById(String id);
    // 返回该房间编号对应的id
    String getId(String roomNum);
    // 获取表中所有房间的信息，并以LinkedList的形式返回
    LinkedList<Room> getAllRooms();
    // 将该id对应的房间从数据库中删除
    boolean deleteById(String id);
    // 将房间编号对应的房间从数据库中删除
    boolean deleteByNumber(String roomNumber);
    // 将一个房间对象从数据库中删除
    boolean delete(Room room);
    // 更新一个房间的信息
    boolean update(Room room);
    // 通过房间名进行模糊和分页查询
    LinkedList<Room> findByName(String name,int page);
    LinkedList<Room> findByName(String name);
    // 统计通过名称模糊查询的记录数
    int getCountByName(String name);
    // 统计通过名称模糊查询的记录页数
    int getMaxPageByName(String name);
}
