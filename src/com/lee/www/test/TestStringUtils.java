package com.lee.www.test;

import java.lang.reflect.InvocationTargetException;

import static com.lee.www.util.StringUtils.toLegalText;
import static com.lee.www.util.UUIDUtils.getUUID;

/**
 * @description 用于测试String工具类
 */
public class TestStringUtils {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        System.out.println(getUUID());
        System.out.println(toLegalText("<h1>"));
    }
}
