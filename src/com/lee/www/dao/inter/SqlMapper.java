package com.lee.www.dao.inter;

/**
 * @author ming
 * @date 2023-01-03 13:13
 * @description 用于将属性映射成sql预编译语句
 */
public interface SqlMapper {
    // 返回映射之后的预编译sql语句
    String doMap(Object... parms);
}
