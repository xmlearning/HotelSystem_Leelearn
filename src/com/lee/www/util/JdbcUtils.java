package com.lee.www.util;

import com.lee.www.dao.impl.MyDataSourceImpl;
import com.lee.www.dao.inter.MyDataSource;
import com.lee.www.exception.DaoException;
import com.sun.deploy.util.SyncAccess;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author ming
 * @date 2023-01-03 13:09
 */
public class JdbcUtils {
    private static MyDataSource dataSrc = MyDataSourceImpl.getInstance();
    private final static String PROP_PATH = "db_config.properties";

    private JdbcUtils(){

    }

    // 负责从数据库连接池中获取数据库连接
    public static Connection getConnection(){
        return dataSrc.getConnection();
    }
    // 用于将数据库连接放回连接池中,并释放Statement和ResultSet的资源
    public static void close(ResultSet rs, Statement st,Connection conn){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn != null) {
            dataSrc.freeConnection(conn);
        }
    }

    // 用于将一个数据库连接放回连接池中
    public static void close(Connection conn){
        if (conn != null){
            dataSrc.freeConnection(conn);
        }
    }

    // 负责返回当前池中剩余的空闲连接数
    public static int getfreeCount(){
        return dataSrc.getfreeCount();
    }

    // 负责返回当前已经创建的连接数
    public static int getCurrentCount(){
        return dataSrc.getCurrentCount();
    }

    // 负责根据传入的参数数组给PreparedStatement填入参数
    public static void setParams(PreparedStatement ps,Object[] params){
        if (params != null){
            for (int i = 0; i < params.length; i++) {
                try {
                    ps.setObject(i + 1, params[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new DaoException("预编译参数异常：" + ps.toString(), e);
                }
            }
        }
    }

    // 负责返回对象对应的表名
    public static String getTableName(Object obj) {
        return obj == null ? null : getConfig(obj.getClass().getSimpleName());
    }
    // 负责返回该类对应的表名
    public static String getTableName(Class clazz) {
        return clazz == null ? null : getConfig(clazz.getSimpleName());
    }
    // 负责返回该表名对应的类
    public static Class getClass(String tableName) {
        try {
            return tableName == null ? null : Class.forName(getConfig(tableName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DaoException("无法加载表名对应的类:" + tableName, e);
        }
    }
    // 负责加载配置文件，向Dao层提供配置信息
    public static String getConfig(String key) {

        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH));
            return key == null ? null : prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DaoException("无法加载配置文件:"+PROP_PATH, e);
        }
    }



}
