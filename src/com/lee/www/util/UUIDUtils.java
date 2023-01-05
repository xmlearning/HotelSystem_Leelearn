package com.lee.www.util;

import java.util.UUID;

/**
 * @author ming
 * @date 2023-01-02 22:02
 * @description 用于提供一个uuid，并去除其中的横线
 */
public class UUIDUtils {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","");
    }
}
