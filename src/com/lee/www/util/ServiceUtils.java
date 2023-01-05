package com.lee.www.util;

import com.lee.www.po.OrderRoom;
import com.lee.www.po.Room;
import com.lee.www.po.User;
import com.lee.www.service.Result;
import com.lee.www.service.constant.Status;
import com.lee.www.vo.PagesVo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-04 9:53
 * @description 用于service的工具类，主要是数据检测功能
 */
public class ServiceUtils {
    /*
     **************************************************************
     *               返回数据
     **************************************************************
     */

    // 负责给Service层返回数据和状态
    public static Result setResult(LinkedList<Room> rooms, Status status) {
        //把数据装入vo
        PagesVo vo = new PagesVo();
        vo.setRooms(rooms);
        // 把vo和枚举类装入Result对象
        return new Result(status, vo);
    }
    // 负责给Service层返回数据和状态
    public static Result setResult(Room room, Status status) {
        PagesVo vo = new PagesVo();
        LinkedList<Room> list = new LinkedList();
        list.add(room);
        vo.setRooms(list);
        return new Result(status, vo);
    }
    // 负责给Service层返回数据和状态
    public static Result setResult(User user, Status status) {
        PagesVo vo = new PagesVo();
        LinkedList<User> list = new LinkedList<>();
        list.add(user);
        vo.setUsers(list);
        return new Result(status, vo);
    }
    // 负责给Service层返回数据和状态
    public static Result setUserResult(LinkedList<User> users, Status status) {
        PagesVo vo = new PagesVo();
        vo.setUsers(users);
        return new Result(status, vo);
    }
    // 负责给Service层返回数据和状态
    public static Result setOrderRoomResult(LinkedList<OrderRoom> orderRooms, Status status) {
        PagesVo vo = new PagesVo();
        vo.setOrderRooms(orderRooms);
        return new Result(status, vo);
    }
    // 负责给Service层返回数据和状态
    public static Result setResult(OrderRoom orderRoom, Status status) {
        PagesVo vo = new PagesVo();
        LinkedList<OrderRoom> list = new LinkedList<>();
        list.add(orderRoom);
        vo.setOrderRooms(list);
        return new Result(status, vo);
    }
    // 负责给Service层返回状态
    public static Result setResult(Status status) {
        return new Result(status, null);
    }
    /*
     **************************************************************
     *               检查用户信息，校验
     **************************************************************
     */

    public static boolean isValidRegist(User user) {
        return user != null && (isValidUserName(user.getName()) && isValidPwd(user.getPassword()));
    }

    public static boolean isValidUserInfo(User user) {

        return user != null && isValidIdNumber(user.getIdNumber()) && isValidNickName(user.getNickName()) && isValidPhoneNum(user.getPhoneNumber());
    }

    public static boolean isValidUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            return false;
        }
        String regex = "[\\w_]{6,20}$";
        return userName.matches(regex);
    }

    public static boolean isValidPwd(String pwd) {
        if (pwd == null || pwd.trim().isEmpty()) {
            return false;
        }
        String regex = "[\\w_]{6,20}$";
        return pwd.matches(regex);
    }

    public static boolean isValidPhoneNum(String number) {
        if (number == null || number.trim().isEmpty()) {
            return false;
        }
        String regex = "0?(13|14|15|17|18|19)[0-9]{9}";
        return number.matches(regex);
    }

    public static boolean isValidIdNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            return false;
        }
        String regex = "\\d{17}[\\d|x]|\\d{15}";
        return number.matches(regex);
    }

    public static boolean isValidNickName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() > 20) {
            return false;
        }
        return true;
    }
    /*
     **************************************************************
     *               检查房间信息
     **************************************************************
     */
    public static boolean isValidRoom(Room room) {
        return isValidName(room) && isValidArea(room) && isValidBedWidth(room) && isValidPrice(room);
    }
    // 合理长度100
    private static boolean isValidName(Room room) {
        return room != null && room.getName().length() < 100;
    }
    // 房间面积合理范围10-300
    private static boolean isValidArea(Room room) {
        return room != null && room.getArea() != null && room.getArea().compareTo(new BigDecimal(10)) > 0 && room.getArea().compareTo(new BigDecimal(300)) < 0;
    }
    // 床宽合理范围1-10
    private static boolean isValidBedWidth(Room room) {
        return room != null && room.getBedWidth() != null && room.getBedWidth().compareTo(new BigDecimal(1)) > 0 && room.getBedWidth().compareTo(new BigDecimal(5)) < 0;
    }
    // 价格合理范围0-100000
    private static boolean isValidPrice(Room room) {
        return room != null && room.getPrice() != null && room.getPrice().compareTo(new BigDecimal(0)) > 0 && room.getPrice().compareTo(new BigDecimal(100000)) < 0;
    }
    /*
     **************************************************************
     *               检查订单信息
     **************************************************************
     */

    // 检查订单
    public static boolean isValidRoomOrder(OrderRoom order) {

        //TODO 需要检查重复性
        return isValidDate(order);
    }
    // 日期校验
    public static boolean isValidDate(OrderRoom order) {
        Date start = null;
        Date end = null;

        try {
            start = new SimpleDateFormat("yyyy-mm-dd").parse(order.getStartTime());
            end = new SimpleDateFormat("yyyy-mm-dd").parse(order.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TODO debug
        if(start.before(end)){
            System.out.println("start 在 end 之前");
        }
        else {
            System.out.println("start 在 end 之后");
        }
        return start.before(end);
    }


}
