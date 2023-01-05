package com.lee.www.util;

/**
 * @author ming
 * @date 2023-01-04 10:03
 * @description 用于字符串的转换
 */
public class StringUtils {
    public static String field2GetMethod(String field) {
        StringBuilder method = new StringBuilder("get" + field);
        method.setCharAt(3, (char) (method.charAt(3) - 32));
        return method.toString();
    }

    public static String field2SqlField(String field) {
        byte[] bytes = field.getBytes();
        StringBuilder name = new StringBuilder();
        for (byte aByte : bytes) {
            if (aByte > 'A' && aByte < 'Z') {
                name.append('_');
            }
            name.append((char) aByte);
        }
        return name.toString();
    }

    public static String field2SetMethod(String field) {
        StringBuilder method = new StringBuilder("set" + field);
        method.setCharAt(3, (char) (method.charAt(3) - 32));
        return method.toString();
    }

    // 去除输入中的不合法字符，防止标签注入
    public static String toLegalText(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        String styleLabel = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String scriptLabel = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String htmlLabel = "<[^>]+>";
        str=str.replaceAll(styleLabel, "");
        str=str.replaceAll(scriptLabel, "");
        str=str.replaceAll(htmlLabel, "");
        str=str.replaceAll("\\s*|\t|\r|\n", "");
        str=str.replace("\"", "");
        return str.trim();
    }


}
