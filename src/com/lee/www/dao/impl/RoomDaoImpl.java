package com.lee.www.dao.impl;

import com.lee.www.dao.inter.RoomDao;
import com.lee.www.po.Room;
import com.lee.www.util.JdbcUtils;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 20:47
 * @description 负责对房间的CRUD
 */
public class RoomDaoImpl extends BaseDaoImpl implements RoomDao {

    private final int limit = 10;


     // 本类操作的数据库表名
    private final String TABLE_NAME = " " + JdbcUtils.getTableName(Room.class) + " ";


     // 表中所有字段对应的查询语句
    private final String ALL_FIELD_NAME = " id,name,number,photo,type,area,bed_width,price,book_status,level,"
            + "score,remark,remark_num,hotel_id,status,gmt_create,gmt_modified ";

    private final String[] ALL_FIELD_ARRAY = new String[]{"id", "name", "number", "photo", "type", "area", "bed_width",
            "price", "book_status", "score", "remark","remark_num", "hotel_id", "status", "gmt_create", "gmt_modified"};

    // 通过房间编号检查一个房间是否已经存在
    @Override
    public boolean isExist(String roomNumber) {
        return getRoom(roomNumber) != null;
    }
    // 添加一个房间到数据库
    @Override
    public boolean addRoom(Room room) {
        if (room == null || room.getNumber() == null) {
            return false;
        }
        return super.insert(room) == 1;
    }
    // 根据房间编号查询一个房间的所有信息
    @Override
    public Room getRoom(String roomNum) {
        if (roomNum == null) {
            return null;
        }
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where number  = ?";
        return (Room) super.queryObject(sql, new Object[]{roomNum}, Room.class);
    }
    // 根据房间id查询一个房间的所有信息
    @Override
    public Room getRoomById(String id) {
        if (id == null) {
            return null;
        }
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where id  = ?";
        return (Room) super.queryObject(sql, new Object[]{id}, Room.class);
    }

    // 返回该房间编号对应的id
    @Override
    public String getId(String roomNum) {
        String sql = "select id from " + TABLE_NAME + " where number = ?";
        return (String) super.queryValue(sql, new Object[]{roomNum});
    }
    // 获取表中所有房间的信息，并以LinkedList的形式返回
    @Override
    public LinkedList<Room> getAllRooms() {
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME;
        LinkedList<Object> list = super.queryList(sql, null, Room.class);
        return toRoomList(list);
    }

    private LinkedList<Room> toRoomList(LinkedList<Object> list) {
        LinkedList<Room> rooms = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            Room room = (Room) list.get(i);
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public boolean deleteById(String id) {
        if (id == null) {
            return false;
        }
        Room room = new Room();
        room.setId(id);
        return super.delete(room) == 1;
    }

    @Override
    public boolean deleteByNumber(String roomNumber) {
        return roomNumber != null && deleteById(getId(roomNumber));
    }

    @Override
    public boolean delete(Room room) {
        return deleteById(room.getId());
    }

    @Override
    public boolean update(Room room) {
        return room != null && super.update(room) == 1;
    }

    // 通过房间名进行模糊查询,含参数page
    @Override
    public LinkedList<Room> findByName(String name, int page) {
        /**
         * 不允许page小于1
         */
        page = page < 1 ? 1 : page;
        int offset = (page - 1) * limit;
        int maxOffset = getCountByName(name);
        /**
         * 不允许offset大于maxOffset
         */
        offset = offset > maxOffset ? maxOffset : offset;

        LinkedList list = null;
        System.out.println(name);
        if (name != null) {
            //System.out.println("begin query=====");
            String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME +
                    " where name like ? limit " + limit + " offset " + offset;
            list = super.queryList(sql, new String[]{"%" + name + "%"}, Room.class);
            System.out.println(list);
        } else {
            //System.out.println("begin query=====");
            String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME +
                    " limit " + limit + " offset " + offset;
            list = super.queryList(sql, new String[0], Room.class);
            //System.out.println(list);
        }
        return toRoomList(list);
    }
    // 通过房间名进行模糊查询
    @Override
    public LinkedList<Room> findByName(String name) {
        Room room = new Room();
        room.setName("%" + name + "%");
        LinkedList<Object> list = super.queryWhereLikeAnd(ALL_FIELD_ARRAY, room);
        return toRoomList(list);
    }

    @Override
    public int getCountByName(String name) {
        String sql = "select count(*) from " + TABLE_NAME + " where name like ? ";
        return Math.toIntExact((long) super.queryValue(sql, new String[]{"%" + name + "%"}));
    }

    @Override
    public int getMaxPageByName(String name) {
        return getCountByName(name)/limit+1;
    }
}
