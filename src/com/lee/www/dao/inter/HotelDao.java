package com.lee.www.dao.inter;

import com.lee.www.po.Hotel;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 15:29
 * @description 负责酒店的CRUD
 */
public interface HotelDao {
    // 通过酒店编号检查一个酒店是否存在
    boolean isExist(String hotelNumber);
    // 添加一个酒店到数据库中
    boolean addHotel(Hotel hotel);
    // 根据酒店编号查询一个酒店的所有信息
    Hotel getHotel(String hotelNum);
    // 返回该酒店编号对应的id
    String getId(String hotelNum);
    // 获取表中所有酒店的信息，并以LinkedList的形式返回
    LinkedList<Hotel> getAllHotels();
    // 将该id对应的酒店从数据库中删除
    boolean deleteById(String Id);
    // 将该酒店编号对应的酒店从数据库中删除
    boolean deleteByNumber(String hotelNumber);
    // 将一个酒店对象从数据库中删除
    boolean delete(Hotel hotel);
    // 更新一个酒店的信息
    boolean update(Hotel hotel);
    // 通过酒店名进行模糊查询
    LinkedList<Hotel> findByName(String name);
}
