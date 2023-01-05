package com.lee.www.annotation;

import java.lang.annotation.*;

/**
 * @author ming
 * @date 2023-01-02 11:02
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableName {
    String value() default "";
}
