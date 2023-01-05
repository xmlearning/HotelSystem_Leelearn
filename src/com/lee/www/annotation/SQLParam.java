package com.lee.www.annotation;

import java.lang.annotation.*;

/**
 * @author ming
 * @date 2023-01-02 11:02
 * @description 用于标注PrepareStatement的参数索引
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface SQLParam {
    int index() default 1;
}
