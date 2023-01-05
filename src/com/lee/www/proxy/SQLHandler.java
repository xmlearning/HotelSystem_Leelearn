package com.lee.www.proxy;

import com.lee.www.annotation.InsertSQL;
import com.lee.www.dao.impl.BaseDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author ming
 * @date 2023-01-04 10:26
 */
public class SQLHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getAnnotation(InsertSQL.class) != null) {
            BaseDaoImpl baseDao = new BaseDaoImpl();
            /**
             * 默认没有sql语句，使用直接插入对象的方法
             */
            if ("".equalsIgnoreCase(method.getAnnotation(InsertSQL.class).value())) {
                Object obj = args[0];
                return baseDao.insert(obj) ;
            } else {
                String sql = method.getDeclaredAnnotation(InsertSQL.class).value();
                return baseDao.executeUpdate(sql, args);
            }
        }
        return null;
    }
}
