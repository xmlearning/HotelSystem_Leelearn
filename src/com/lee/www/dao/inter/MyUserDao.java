package com.lee.www.dao.inter;

import com.lee.www.annotation.InsertSQL;
import com.lee.www.annotation.SQLParam;

/**
 * @author ming
 * @date 2023-01-03 15:38
 */
public interface MyUserDao extends BaseDao {
    @InsertSQL("insert into t_user (id,name) values (?,?)")
    boolean insert(@SQLParam() String id,@SQLParam (index = 2) String name);
}
