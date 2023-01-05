package com.lee.www.dao.inter;

import com.lee.www.annotation.InsertSQL;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 13:07
 * @description 提供共有的数据库操作
 */
public interface BaseDao {
    /*
     **************************************************************
     *          负责数据库insert,update,delete等功能
     **************************************************************
     */
    // 必须传入一个SqlMapper的实现类用于映射预编译语句
    int executeUpdate(Object obj,SqlMapper sqlMapper);
    // 把一个对象插入一张表
    @InsertSQL()
    int insert(Object obj);
    // 根据传入的表名和id，从该表中更新一条记录
    int update(Object obj);
    // 将一个对象从表中删除
    int delete(Object obj);
    // 执行一条预编译指令，并且填入参数
    Object executeQuery(String sql,Object[] params,ResultMapper mapper);
    // 执行输入的sql语句，并且将结果以LinkedList的形式返回
    LinkedList queryList(String sql,Object[] params,Class clazz);
    // 执行输入的sql语句，并且将结果映射为对象，以对象的形式返回
    Object queryObject(String sql,Object[] params,Class clazz);
    // 执行一条sql语句，返回一个字段的值
    Object queryValue(String sql,Object[] params);
    // 返回一个排序结果
    LinkedList queryOrderBy(String[] selectFields,String orderBy,boolean isDesc,String tableName);
    // 统计查询
    Long queryCount(String tableName,String countField);
    // 返回一个where A ? B and/or/not C ? D 形式查询语句的查询结果
    LinkedList queryWhere(String[] selectFields,Object obj,String conj,String condition);
    // 返回一个where A = B and C = D 形式查询语句的查询结果
    LinkedList queryWhereAndEquals(String[] selectFields,Object obj);
    // 返回一个where A like B and C like D 形式查询语句的查询结果
    LinkedList queryWhereLikeAnd(String[] selectFields,Object obj);
    // 返回一个where A like B or C like D 形式查询语句的查询结果
    LinkedList queryWhereLikeOr(String[] selectFields,Object obj);
    // 返回一个分页查询的结果
    LinkedList queryPages(String[] selectFields,String tableName,String limit,String offset);
    // 把一个对象映射成为属性名集合和属性值集合
    void fieldMapper(Object obj,LinkedList fieldNames,LinkedList fieldValues);
    // 根据传入的表名生成一个select语句，如select user_name from user
    String selectMapper(String tableName, Object[] selectFields);
    // 生成一个where语句，如where id = ?
    String whereMapper(Object[] whereFields, String conj, String condition);
    // 生成一个where like 语句，如where id like ?
    String likeMapper(Object[] likeFields, String conj);
    // 返回一个Order语句，如order by id
    String orderByMapper(String orderBy, boolean isDesc);
    // 返回一个分页查询语句，如limit 3 offset 0
    String pageMapper(String limit, String offset);
    // 返回一个每页LIMIT条记录的分页查询语句，如limit 10 offset 0
    // LIMIT 由本类的成员变量控制a
    String pageOffsetMapper(String offset);

}
