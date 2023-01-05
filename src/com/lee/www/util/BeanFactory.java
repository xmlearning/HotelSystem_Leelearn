package com.lee.www.util;

import com.lee.www.exception.DaoException;

import java.io.IOException;
import java.util.Properties;

/**
 * @author ming
 * @date 2023-01-03 21:41
 * @description 负责生产实现类
 */
public class BeanFactory {
    // 配置文件
    private static final String PROPERTIES = "factory.properties";

    private BeanFactory() {

    }

    public enum DaoType {
        /**
         * Dao实现类
         */
        UserDao("UserDao"), RoomDao("RoomDao"), OrderRoomDao("OrderRoomDao"),
        HotelDao("HotelDao"),RemarkDao("RemarkDao");

        private String name;
        DaoType(String name) {
            this.name=name;
        }
    }
    public enum ServiceType{
        /**
         * Service实现类
         */
        UserService("UserService"),RoomService("RoomService"),OrderRoomService("OrderRoomService"),RemarkService("RemarkService");
        private String name;
        ServiceType(String name) {
            this.name=name;
        }
    }
    public static Object getBean(Enum beanType) {
        String className;
        Object bean;
        /**
         * 加载配置文件
         */
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES));
            className = prop.getProperty(beanType.name());
        } catch (IOException e) {
            e.printStackTrace();
            throw new DaoException("无法加载配置文件 ：" + PROPERTIES, e);
        }
        try {
            bean = Class.forName(className).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DaoException("无法加载类 : " + className, e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new DaoException("无法实例化类 : " + className, e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new DaoException("无法初始化实例 ：" + className, e);
        }
        return bean;
    }
}
