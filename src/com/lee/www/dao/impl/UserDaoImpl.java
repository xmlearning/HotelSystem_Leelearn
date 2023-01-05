package com.lee.www.dao.impl;

import com.lee.www.dao.inter.UserDao;
import com.lee.www.po.User;
import com.lee.www.util.JdbcUtils;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 21:04
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

     // 本类操作的数据库表名

    private final String TABLE_NAME = " " + JdbcUtils.getTableName(User.class) + " ";



     // 表中所有字段对应的查询语句

    private final String ALL_FIELD_NAME = " id,name,password,phone_number,type,id_number,nick_name,"
            + "photo,status,balance,pay_pwd,gmt_create,gmt_modified ";

    // 通过用户名检查一个用户是否已经存在
    @Override
    public boolean isExist(String userName) {
        return getUser(userName) != null;
    }
    // 添加一个用户到数据库
    @Override
    public boolean addUser(User user) {
        if (user == null || user.getName() == null) {
            return false;
        }
        return super.insert(user) == 1;
    }
    // 根据id查询一个用户的所有信息
    @Override
    public User getUserById(String id) {
        if (id == null) {
            return null;
        }
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where id = ?";
        return (User) super.queryObject(sql, new Object[]{id}, User.class);
    }
    // 根据用户名查询一个用户的所有信息
    @Override
    public User getUser(String userName) {
        if (userName == null) {
            return null;
        }
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME + " where name = ?";
        return (User) super.queryObject(sql, new Object[]{userName}, User.class);
    }
    // 返回该用户名对应的登陆密码
    @Override
    public String getPassword(String userName) {
        String sql = "select password from " + TABLE_NAME + " where name = ?";
        return (String) super.queryValue(sql, new Object[]{userName});
    }
    // 返回该用户名对应的id
    @Override
    public String getId(String userName) {
        String sql = "select id from " + TABLE_NAME + " where name = ?";
        return (String) super.queryValue(sql, new Object[]{userName});
    }
    // 获取表中所有用户的信息，并以LinkedList的形式返回
    @Override
    public LinkedList<User> getAllUsers() {
        String sql = "select " + ALL_FIELD_NAME + " from " + TABLE_NAME;
        LinkedList<Object> list = super.queryList(sql, null, User.class);
        LinkedList<User> users = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);
            users.add(user);
        }

        return users;
    }
    // 将该id对应的用户从数据库中删除
    @Override
    public boolean deleteById(String id) {
        if(id==null){
            return false;
        }
        User user = new User();
        user.setId(id);
        return super.delete(user) == 1;
    }
    // 将该用户名对应的用户从数据库中删除
    @Override
    public boolean deleteByUserName(String userName) {
        return userName == null ? false : deleteById(getId(userName));
    }
    // 将一个用户对象从数据库中删除
    @Override
    public boolean delete(User user) {
        return user != null && deleteById(user.getId());
    }
    // 更新一个用户的信息，不包括登陆密码和支付密码
    @Override
    public boolean update(User user) {
        if (user == null) {
            return false;
        }
        /**
         * 使用克隆对象，防止影响原来对象的值
         */
        User clone = user.clone();
        clone.setId(getId(clone.getName()));
        clone.setPassword(null);
        clone.setPayPwd(null);
        return super.update(clone) == 1;
    }
    // 更新一个用户的信息，包括密码
    @Override
    public boolean updateAll(User user) {
        if(user==null){
            return false;
        }
        user.setId(getId(user.getName()));
        return super.update(user) == 1;
    }
}
