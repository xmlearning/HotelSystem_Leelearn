package com.lee.www.dao.inter;

import com.lee.www.po.Remark;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 15:49
 * @description 负责评论的CRUD
 */
public interface RemarkDao {
    // 负责将一条评论插入数据库
    boolean add(Remark remark);
    // 返回所有评论
    LinkedList<Remark> listAll();
    // 返回该用户名对应的留言数量
    int getUserRemarkCount(String userName);
}
