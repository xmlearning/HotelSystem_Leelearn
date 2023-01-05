package com.lee.www.dao.inter;

import java.sql.ResultSet;

/**
 * @author ming
 * @date 2023-01-03 13:18
 * @description 用于处理执行查询语句之后返回的结果集，将结果集映射为对象
 */
public interface ResultMapper {
    // 需要映射的结果集
    Object doMap(ResultSet rs);
}
