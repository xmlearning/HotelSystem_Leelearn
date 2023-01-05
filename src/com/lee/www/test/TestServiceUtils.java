package com.lee.www.test;

import com.lee.www.po.Room;
import com.lee.www.util.ServiceUtils;

import java.math.BigDecimal;

/**
 * @description 用于测试ServiceUtils
 */
public class TestServiceUtils {
    public static void main(String[] args) {
        Room room = new Room();
        room.setPrice(BigDecimal.valueOf(999));
        room.setArea(BigDecimal.valueOf(50));
        room.setBedWidth(BigDecimal.valueOf(3));
        System.out.println(ServiceUtils.isValidRoom(room));
    }
}
