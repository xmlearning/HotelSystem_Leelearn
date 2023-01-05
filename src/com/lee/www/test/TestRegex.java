package com.lee.www.test;

/**
 * @description 用于测试正则表达式
 */
public class TestRegex {
    public static void main(String[] args) {
        String regex = "[\\w_]{6,20}$";
        System.out.println(regex.matches(""));
        System.out.println("tdsflj14321fasdf\n".matches(regex));
        String regex1= "login*";

        System.out.println("alogin".matches(regex1));
    }
}
