package com.lee.www.dao.impl;

import com.lee.www.dao.inter.RemarkDao;
import com.lee.www.po.Remark;
import com.lee.www.util.JdbcUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ming
 * @date 2023-01-03 20:44
 */
public class RemarkDaoImpl extends BaseDaoImpl implements RemarkDao {

    private final int limit = 10;
    // 本类操作的数据库表名
    private final String TABLE_NAME = " " + JdbcUtils.getTableName(Remark.class) + " ";

    // 负责将一条评论插入数据库
    @Override
    public boolean add(Remark remark) {
        return super.insert(remark) == 1;
    }

    // 返回所有评论
    @Override
    public LinkedList<Remark> listAll() {
        String sql = "select id,user_name,remark,status,gmt_create,gmt_modified from " + TABLE_NAME + " order by gmt_create desc";
        LinkedList<Remark> remarks = new LinkedList<>();
        List list = super.queryList(sql, new String[0], Remark.class);
        if (list != null) {
            for (Object obj : list) {
                Remark remark = (Remark) obj;
                remarks.add(remark);
            }
            return remarks;
        }
        return null;
    }

    // 返回该用户名对应留言数量
    @Override
    public int getUserRemarkCount(String userName) {
        String sql = "select count(*) from " + TABLE_NAME + " where user_name = ? ";
        return Math.toIntExact((Long) super.queryValue(sql, new String[]{userName}));
    }
}
