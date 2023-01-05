package com.lee.www.dao.inter;

import com.lee.www.po.User;

import java.util.LinkedList;

/**
 * @author ming
 * @date 2023-01-03 16:10
 * description 提供User类的Dao接口
 */
public interface UserDao {
    // 通过用户名检查一个用户是否已经存在
    boolean isExist(String userName);
    // 添加一个用户到数据库
    boolean addUser(User user);
    // 根据id查询一个用户的所有信息
    User getUserById(String id);
    // 根据用户名查询一个用户的所有信息
    User getUser(String userName);
    // 返回该用户名对应的登陆密码
    String getPassword(String userName);
    // 返回该用户名对应的id
    String getId(String userName);
    // 获取表中所有用户的信息，并以LinkedList的形式返回
    LinkedList<User> getAllUsers();
    // 将该id对应的用户从数据库中删除
    boolean deleteById(String id);
    // 将该用户名对应的用户从数据库中删除
    boolean deleteByUserName(String userName);
    // 将一个用户对象从数据库中删除
    boolean delete(User user);
    // 更新一个用户的信息，不包括登陆密码和支付密码
    boolean update(User user);
    // 更新一个用户的信息，包括密码
    boolean updateAll(User user);
}
