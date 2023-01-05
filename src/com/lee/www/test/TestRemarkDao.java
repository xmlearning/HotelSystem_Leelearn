package com.lee.www.test;

import com.lee.www.dao.impl.RemarkDaoImpl;
import com.lee.www.dao.inter.RemarkDao;

/**
 * @description 测试RemarkDao
 */
public class TestRemarkDao {
    public static void main(String[] args) {
        RemarkDao dao  = new RemarkDaoImpl();
        System.out.printf(dao.listAll().get(0).getRemark());
        System.out.println(dao.getUserRemarkCount("test8888(我的新昵称)"));
    }
}
