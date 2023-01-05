package com.lee.www.test;

import com.lee.www.util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static com.lee.www.util.JdbcUtils.getConnection;

/**
 * @description 用于测试JdbcUtils
 */
public class TestJdbcUtils {
    public static void main(String[] args) throws SQLException {

        /*
         ***************************************************************
         *              测试从jdbcUtils取链接
         * ************************************************************
         * */

        System.out.println("当前连接数：" + JdbcUtils.getCurrentCount());
        System.out.println("空闲连接数：" + JdbcUtils.getfreeCount());
        for (int i = 0; i < 3; i++) {

            System.out.println(getConnection());
        }
        System.out.println("当前连接数：" + JdbcUtils.getCurrentCount());
        System.out.println("空闲连接数：" + JdbcUtils.getfreeCount());

        Connection conn=null;
        for (int i = 0; i < 10; i++) {
            conn = getConnection();
            JdbcUtils.close(conn);
            System.out.println(conn);
        }
        System.out.println("当前连接数：" + JdbcUtils.getCurrentCount());
        System.out.println("空闲连接数：" + JdbcUtils.getfreeCount());
    }
}
