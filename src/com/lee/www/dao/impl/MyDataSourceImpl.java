package com.lee.www.dao.impl;

import com.lee.www.dao.inter.MyDataSource;
import com.lee.www.exception.DaoException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author ming
 * @date 2023-01-03 16:19
 * @description 实现了MyDataSource接口，负责提供数据库连接池，向dao实现类提供数据库连接
 */
public class MyDataSourceImpl implements MyDataSource {
     // 配置文件路径
    private static String PROP_PATH ="data_source.properties";

     // 测试数据库连接的等待时长
    private static int TIMEOUT;

     // 初始连接数
    private static int INIT_SIZE;

     // 最大连接数
    private static int MAX_SIZE;

     // 当前已经创建的连接数
    private static int currentCount = 0;

    private static String url;
    private static String user;
    private static String password;
    private static MyDataSourceImpl instance;

     // 数据库连接池
    private LinkedList<Connection> connPool = new LinkedList<>();

    static {
        try {
            /**
             * 加载配置文件
             * data_source.properties
             */
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH));
            /**
             * 驱动类名称
             */
            String driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            MAX_SIZE = Integer.parseInt(prop.getProperty("MAX_SIZE"));
            INIT_SIZE = Integer.parseInt(prop.getProperty("INIT_SIZE"));
            TIMEOUT = Integer.parseInt(prop.getProperty("TIMEOUT"));
            /**
             * 注册驱动
             */
            Class.forName(driver);
            instance= new MyDataSourceImpl();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }



     // 负责提供数据库连接池实例
    public static MyDataSourceImpl getInstance() {
        return instance;
    }


     // 负责从数据库连接池中获取数据库连接
    @Override
    public Connection getConnection() throws DaoException {
        if (connPool.size() > 0) {

            // 先检查连接是否可用，如果不可用，关闭该连接，返回一个新连接
            Connection conn = connPool.removeLast();
            try {
                if(conn.isValid(TIMEOUT)){
                    return conn;
                }else {
                    destroyConnection(conn);
                    return createConnection();
                }
            } catch (SQLException e) {
                throw new DaoException("测试数据库连接产生异常",e);
            }
        } else if (currentCount < MAX_SIZE) {
            return createConnection();
        } else {
            throw new DaoException("数据库连接数已达到最大值");
        }
    }


     // 用于将数据库连接放回连接池中

    @Override
    public void freeConnection(Connection conn) {
        this.connPool.addLast(conn);
    }


     // 负责返回当前已经创建的连接数
    @Override
    public int getCurrentCount() {
        return currentCount;
    }


     // 负责返回当前池中剩余的空闲连接数
    @Override
    public int getfreeCount() {
        return this.connPool.size();
    }

     // 创建连接("协议+访问的数据库名”，“用户名”，“密码”）
    private Connection createConnection() throws DaoException {
        currentCount++;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DaoException("无法创建数据库连接",e);
        }
    }



     // 关闭数据库连接

    private void destroyConnection(Connection conn) throws DaoException {
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            throw new DaoException("关闭数据库连接异常",e);
        }
    }


     // 创建连接池实例，初始化数据库连接池

    private MyDataSourceImpl() {
        for (int i = 0; i < INIT_SIZE; i++) {
            this.connPool.add(this.createConnection());
        }
    }
}
