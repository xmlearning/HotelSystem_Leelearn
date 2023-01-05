package com.lee.www.test;

import com.lee.www.dao.impl.BaseDaoImpl;
import com.lee.www.dao.inter.UserDao;
import com.lee.www.po.User;
import com.lee.www.util.BeanFactory;

import java.util.Iterator;

/**
 * @description 用于测试Dao实现类
 */
public class TestDao {
    public static void main(String[] args) {
        UserDao userDao = (UserDao) BeanFactory.getBean(BeanFactory.DaoType.UserDao);
        /**
         * 测试BaseDao
         */
        BaseDaoImpl baseDao = new BaseDaoImpl();
        User user = new User();

        System.out.println(baseDao.queryCount("t_user", "balance"));
        System.out.println(baseDao.queryWhereAndEquals(new String[]{"user_name"}, user).size());

        /**
         * 测试插入
         */
        user.setName("test");
        baseDao.insert(null);

        /***
         * 测试分页查询
         */
        System.out.println("pages num " + baseDao.queryPages(new String[]{"user_name"}, "t_user", "30", "0").size());
        Iterator it = baseDao.queryPages(new String[]{"user_name"}, "t_user", "10", "0").listIterator();
        while (it.hasNext()) {
            user = (User) it.next();
            System.out.println(user.getName());
        }

        /**
         * 测试模糊查询
         */
        user.setName("%new%");
        user.setPassword("12345");
        it = baseDao.queryWhereLikeAnd(new String[]{"user_name"}, user).listIterator();
        while (it.hasNext()) {
            user = (User) it.next();
            System.out.println(user.getName());
        }
        /**
         * 测试查询很多记录
         */
        System.out.println(baseDao.queryList("select * from t_user",new Object[]{},User.class).size());
    }
}
