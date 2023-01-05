package com.lee.www.service.impl;

import com.lee.www.dao.inter.OrderRoomDao;
import com.lee.www.dao.inter.RoomDao;
import com.lee.www.dao.inter.UserDao;
import com.lee.www.po.OrderRoom;
import com.lee.www.po.Room;
import com.lee.www.po.User;
import com.lee.www.service.Result;
import com.lee.www.service.inter.OrderRoomService;
import com.lee.www.util.BeanFactory;
import com.lee.www.util.BeanUtils;
import com.lee.www.vo.PagesVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedList;

import static com.lee.www.service.constant.Status.*;
import static com.lee.www.util.ServiceUtils.setOrderRoomResult;
import static com.lee.www.util.ServiceUtils.setResult;
import static com.lee.www.util.UUIDUtils.getUUID;

/**
 * @author ming
 * @date 2023-01-04 10:17
 * @description 负责房间订单相关的服务
 */
public class OrderRoomServiceImpl implements OrderRoomService {

    private OrderRoomDao dao = (OrderRoomDao) BeanFactory.getBean(BeanFactory.DaoType.OrderRoomDao);
    private RoomDao roomDao = (RoomDao) BeanFactory.getBean(BeanFactory.DaoType.RoomDao);
    private UserDao userDao = (UserDao) BeanFactory.getBean(BeanFactory.DaoType.UserDao);


    @Override
    public Result add(HttpServletRequest req, HttpServletResponse resp) {
        OrderRoom order = (OrderRoom) BeanUtils.toObject(req.getParameterMap(), OrderRoom.class);
        String userName = req.getParameter("user");
        order.setId(getUUID());
        order.setUserId(userDao.getId(userName));
        if (dao.addOrderRoom(order)) {
            return setResult(order, SUCCESS);
        }
        return setResult(ERROR);
    }

    @Override
    public Result delete(HttpServletRequest req, HttpServletResponse resp) {
        OrderRoom order = (OrderRoom) BeanUtils.toObject(req.getParameterMap(), OrderRoom.class);

        //TODO 处理房间已经有人预定的情况
        if (order.getId() == null) {
            order.setId(dao.getId(order.getNumber()));
        }

        if (dao.delete(order)) {
            return setResult(SUCCESS);
        }

        return setResult(ERROR);
    }

    @Override
    public Result find(HttpServletRequest req, HttpServletResponse resp) {
        OrderRoom order = (OrderRoom) BeanUtils.toObject(req.getParameterMap(), OrderRoom.class);
        order = dao.getOrderRoom(order.getNumber());
        if (order == null) {
            return setResult(NOT_FOUNT);
        }
        PagesVo vo = new PagesVo();
        LinkedList<OrderRoom> orders = new LinkedList<>();
        orders.add(order);
        Room room = roomDao.getRoomById(order.getRoomId());
        User user = userDao.getUserById(order.getUserId());
        LinkedList<User> users = new LinkedList<>();
        LinkedList<Room> rooms = new LinkedList<>();
        rooms.add(room);
        users.add(user);
        setVoData(vo, orders, users, rooms);
        return new Result(SUCCESS, vo);
    }
    private void setVoData(PagesVo vo, LinkedList<OrderRoom> orders, LinkedList<User> users, LinkedList<Room> rooms) {
        vo.setOrderRooms(orders);
        vo.setRooms(rooms);
        vo.setUsers(users);
    }

    // 返回该用户用户名对应的订单
    @Override
    public Result listByUserName(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("user");
        String userId = userDao.getId(userName);
        LinkedList<OrderRoom> orders = dao.listByUserId(userId);
        LinkedList<User> users = new LinkedList<>();
        LinkedList<Room> rooms = new LinkedList<>();
        if (orders != null) {
            for (OrderRoom order:orders) {
                rooms.add( roomDao.getRoomById(order.getRoomId()));
                users.add(userDao.getUserById(order.getUserId()));
            }
            PagesVo vo = new PagesVo();
            setVoData(vo,orders,users,rooms);
            return new Result(SUCCESS,vo);
        }
        return setOrderRoomResult(orders, NO_RESULT);
    }
}
