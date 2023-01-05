package com.lee.www.test;

import com.lee.www.dao.inter.MyUserDao;
import com.lee.www.po.User;
import com.lee.www.proxy.SQLHandler;

import java.lang.reflect.Proxy;

import static com.lee.www.util.UUIDUtils.getUUID;

/**
 * @description
 */
public class TestMyUserDao {
    public static void main(String[] args) {
        User user = new User();
        user.setId(getUUID());
        user.setName("testtest");
        MyUserDao dao = (MyUserDao) Proxy.newProxyInstance(SQLHandler.class.getClassLoader(),new Class[]{MyUserDao.class},new SQLHandler());
        dao.insert(user);

    }
}
