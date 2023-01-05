package com.lee.www.annotation;

import java.lang.annotation.*;

/**
 * @author ming
 * @date 2023-01-02 11:01
 * @description 用注执行插入语句于标
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InsertSQL {
    String value() default "";
}
