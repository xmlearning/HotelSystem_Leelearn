package com.lee.www.test;

import com.lee.www.dao.inter.RoomDao;
import com.lee.www.po.Room;
import com.lee.www.util.BeanFactory;

import java.math.BigDecimal;
import java.util.LinkedList;

import static com.lee.www.util.UUIDUtils.getUUID;

/**
 * @description 用于测试RoomDao
 */
public class TestRoomDao {
    public static void main(String[] args) {
        RoomDao roomDao = (RoomDao) BeanFactory.getBean(BeanFactory.DaoType.RoomDao);

        /**
         * 测试查询房间全部信息功能
         */
        System.out.println("测试查询房间全部信息功能");
        Room room = roomDao.getRoom("1020");
        if (room != null) {
            System.out.println(room.getNumber());
            System.out.println(room.getId());
            System.out.println(room.getStatus());
            System.out.println(room.getArea());
            System.out.println(room.getGmtModified());
        } else {
            System.out.println(room);
        }

        /**
         * 测试获取所有房间信息的功能
         */
        System.out.println("测试获取所有房间信息的功能");
        LinkedList list = roomDao.getAllRooms();
        System.out.println(list.size());
        for (int i = 0; i <0 ; i++) {
        room = (Room) list.get(i);
        System.out.println(room.getGmtModified());
        System.out.println(room.getNumber());
        System.out.println(room.getName());

        }
        /**
         * 测试通过房间id删除房间的功能
         */
        System.out.println("测试通过房间id删除房间的功能");
        System.out.println(roomDao.deleteById("5"));
        /**
         * 测试通过编号删除房间的功能
         */
        System.out.println("测试通过房间编号删除房间的功能");
        System.out.println(roomDao.deleteByNumber("0"));
        /**
         * 测试更新房间信息的功能
         */
        System.out.println("测试更新房间信息的功能");
        room = roomDao.getRoom("0000");
        if (room != null) {
            room.setBedWidth(BigDecimal.valueOf(2.0));
            room.setArea(BigDecimal.valueOf(1000.90));
            System.out.println(roomDao.update(room));
        }


        /**
         * 测试增加房间的功能
         */
        System.out.println("测试增加房间的功能");
        room = new Room();
        room.setNumber("1020");
        room.setPrice(BigDecimal.valueOf(99.99));
        room.setPhoto("test2");
        System.out.println(roomDao.addRoom(null));

        /**
         * 测试模糊查询
         */
        System.out.println(roomDao.findByName("test").size());
        System.out.println(roomDao.findByName(null,1).size());
        /**
         * 批量增加房间
         */
        for (int i = 0; i < 0; i++) {
            room=new Room();
            room.setId(getUUID());
            room.setNumber("452834"+i);
            room.setName("文莱帝国酒店(Empire Hotel& Country Club)商务间"+"452834"+i);
            room.setArea(new BigDecimal(i+110));
            room.setType("多人");
            room.setLevel("8星级");
            room.setScore(new BigDecimal(4.9));
            room.setBedWidth(new BigDecimal(3));
            room.setPhoto("e93f57968f8d413f853b9130ff00bf0f.jpg");
            room.setPrice(new BigDecimal(8999+i*100));
            room.setRemark("“十万元一晚的帝王级享受，对于懂得享受和追求奢华感觉的人士绝对是最佳的选择”");
            System.out.println("add room");
            roomDao.addRoom(room);
        }


    }
}
