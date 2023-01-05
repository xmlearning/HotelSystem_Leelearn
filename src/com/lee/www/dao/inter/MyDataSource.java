package com.lee.www.dao.inter;

import com.lee.www.exception.DaoException;

import java.sql.Connection;

/**
 * @author ming
 * @date 2023-01-03 14:09
 * @description 负责提供数据库连接池，向dao实现类提供数据库连接
 */
public interface MyDataSource {
    // 数据库连接的数量受到配置文件中最大值的限制
    Connection getConnection() throws DaoException;
    // 数据库连接
    void freeConnection(Connection conn);
    // 当前已经创建的连接数
    int getCurrentCount();
    // 当前空闲连接数
    int getfreeCount();

}
