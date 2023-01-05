package com.lee.www.test;

import com.lee.www.dao.impl.MyDataSourceImpl;
import com.lee.www.dao.inter.MyDataSource;

import java.sql.Connection;

import static com.lee.www.util.JdbcUtils.getConnection;

/**
 * @description 用于测试数据库连接池
 */
public class TestMyDataSource {


    public static void main(String[] args) throws ClassNotFoundException {

        getConnection();
        System.out.println("测试直接从连接池获取连接");
        MyDataSource dataSource = MyDataSourceImpl.getInstance();
        Connection conn = dataSource.getConnection();
        System.out.println("获取一个连接\n");
        System.out.println(conn);
        System.out.println("当前已创建连接数 = " + dataSource.getCurrentCount());
        System.out.println("当前空闲连接数 = " + dataSource.getfreeCount());
        System.out.println("释放一个连接");
        dataSource.freeConnection(conn);
        for (int i = 0; i < 10; i++) {
            conn = dataSource.getConnection();
            System.out.println(conn);
            dataSource.freeConnection(conn);
        }
        System.out.println("释放一个连接");
        dataSource.freeConnection(conn);
        System.out.println("当前已创建连接数 = " + dataSource.getCurrentCount());
        System.out.println("当前空闲连接数 = " + dataSource.getfreeCount());



    }
}
