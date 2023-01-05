package com.lee.www.test;

import com.lee.www.po.User;
import com.lee.www.util.BeanUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * @description 用于测试BeanUtils工具类
 */
public class TestBeanUtils {
    public static void main(String[] args) {
        HashMap map = new HashMap();
        String[] value = new String[1];
        value[0]="test";
        map.put("userName", value);
        value[0]="3";
        map.put("balance", value);
        Date d = new Date();
        Class clazz = User.class;
        User user = (User) BeanUtils.toObject(map,clazz);
        System.out.println(user.getName());
        System.out.println(user.getBalance());
    }
}
