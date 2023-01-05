package com.lee.www.test;


import java.io.File;

/**
 * @description 用于测试与文件路径相关的方法
 */
public class TestFile {
    public static void main(String[] args) {
        File file = new File("./src/db_config.properties");
        System.out.println(file.getAbsolutePath());
    }
}
