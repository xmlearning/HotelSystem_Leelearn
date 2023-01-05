package com.lee.www.annotation;

import java.lang.annotation.*;

/**
 * @author ming
 * @date 2023-01-02 10:59
 * @description 用于描述DML语句（update/delete/insert）
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DML {
    String value() default "";
}
