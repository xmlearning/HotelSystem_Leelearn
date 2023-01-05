package com.lee.www.service.impl;

import com.lee.www.dao.inter.RoomDao;
import com.lee.www.po.Room;
import com.lee.www.service.Result;
import com.lee.www.service.inter.RoomService;
import com.lee.www.util.BeanFactory;
import com.lee.www.util.BeanUtils;
import com.lee.www.util.ServiceUtils;
import com.lee.www.vo.PagesVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedList;

import static com.lee.www.service.constant.Status.*;
import static com.lee.www.util.ServiceUtils.isValidRoom;
import static com.lee.www.util.ServiceUtils.setResult;
import static com.lee.www.util.UUIDUtils.getUUID;
import static com.lee.www.util.UploadUtils.uploadPhoto;

/**
 * @author ming
 * @date 2023-01-04 10:16
 * @description 负责房间相关的服务
 */
public class RoomServiceImpl implements RoomService {


    private RoomDao dao = (RoomDao) BeanFactory.getBean(BeanFactory.DaoType.RoomDao);


    @Override
    public Result add(HttpServletRequest req, HttpServletResponse resp) {
        Room room = (Room) BeanUtils.toObject(req.getParameterMap(), Room.class);
        if (dao.isExist(room.getNumber())) {
            return setResult(room, ROOM_ALREADY_EXIST);
        }
        if (!isValidRoom(room)) {
            return setResult(room, DATA_ILLEGAL);
        }
        room.setId(getUUID());

        uploadPhoto(req, room);
        //TODO 处理房间编号，计算编号
        if (dao.addRoom(room)) {
            return setResult(room, SUCCESS);
        }
        return setResult(ERROR);
    }

    @Override
    public Result delete(HttpServletRequest req, HttpServletResponse resp) {
        Room room = (Room) BeanUtils.toObject(req.getParameterMap(), Room.class);
        if (!dao.isExist(room.getNumber())) {
            return setResult(NOT_FOUNT);
        }

        //TODO 处理房间已经有人预定的情况
        if (room.getId() == null) {
            room.setId(dao.getId(room.getNumber()));
        }
        if (dao.delete(room)) {
            return ServiceUtils.setResult(room, SUCCESS);
        }
        return setResult(ERROR);
    }

    @Override
    public Result update(HttpServletRequest req, HttpServletResponse resp) {
        Room room = (Room) BeanUtils.toObject(req.getParameterMap(), Room.class);
        if (!isValidRoom(room)) {
            /**
             * 重新加载头像
             */
            room.setPhoto(dao.getRoom(room.getNumber()).getPhoto());
            return ServiceUtils.setResult(room, DATA_ILLEGAL);
        }
        if (room.getId() == null) {
            room.setId(dao.getId(room.getNumber()));
        }
        uploadPhoto(req, room);
        if (dao.update(room)) {
            return ServiceUtils.setResult(room, SUCCESS);
        }
        return setResult(ERROR);
    }

    @Override
    public Result find(HttpServletRequest req, HttpServletResponse resp) {
        Room room = (Room) BeanUtils.toObject(req.getParameterMap(), Room.class);
        room = dao.getRoom(room.getNumber());
        if (room != null) {
            return ServiceUtils.setResult(room, SUCCESS);
        }
        return setResult(ERROR);
    }

    // 返回所有的房间
    @Override
    public Result listAll(HttpServletRequest req, HttpServletResponse resp) {
        int page = Integer.parseInt(req.getParameter("page"));
        LinkedList<Room> rooms = dao.findByName(null, page);
        if (rooms != null && rooms.size() > 0) {
            return setResult(rooms, SUCCESS);
        }
        return setResult(ERROR);
    }
    // 通过名称模糊查询相关的房间
    @Override
    public Result listByName(HttpServletRequest req, HttpServletResponse resp) {
        int page = Integer.parseInt(req.getParameter("page"));
        String name = req.getParameter("name");
        //System.out.println("=====this====");
        LinkedList<Room> rooms = dao.findByName(name, page);
        //System.out.println("pass======");
        if (rooms != null && rooms.size() > 0) {
            PagesVo vo = new PagesVo();
            vo.setRooms(rooms);
            vo.setMaxPage(dao.getMaxPageByName(name));
            return new Result(SUCCESS,vo);
        }
        return setResult(rooms, NO_RESULT);
    }
}
