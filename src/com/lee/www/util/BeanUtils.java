package com.lee.www.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Map;

import static com.lee.www.util.ReflectUtils.getFields;
import static com.lee.www.util.ReflectUtils.getMethods;
import static com.lee.www.util.StringUtils.toLegalText;

/**
 * @author ming
 * @date 2023-01-03 21:49
 * @description 用于将请求参数映射为Javabean对象
 */
public class BeanUtils {
    // 负责将request中的parameterMap映射成为一个对象
    public static Object toObject(Map<String, String[]> map, Class clazz) {
        /**
         * 本方法仅用来将request中的参数映射为对象，并不承担<br>
         * 数据检查的职责，因此本方法中产生的异常没有打印堆栈信息
         */

        LinkedList<Method> methods=null;
        LinkedList<Field>fields=null;
        Object obj;
        try {
            obj = clazz.newInstance();
            methods = getMethods(obj);
            fields = getFields(obj);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("无法实例化类：" + clazz.getName());
        }


        for (Field f : fields) {
            /**
             * 获取每个属性的String类型参数的构造器
             */
            Constructor cons = null;
            try {
                cons = f.getType().getConstructor(String.class);
            } catch (Exception e) {
                /**
                 * 某些属性确实没有这种构造器，在这里不需要处理这个问题
                 */
                System.out.println("该变量没有String类型参数的构造器"+f.getName());
            }
            /**
             * 构造属性
             */
            String[] param = map.get(f.getName());
            if (param != null && param[0] != null) {
                Object value = null;
                try {
                    if (cons != null) {
                        /**
                         * 编码格式转换
                         */
                        param[0] = new String (param[0].getBytes(), StandardCharsets.UTF_8);
                        /**
                         * 过滤非法字符
                         */
                        param[0] = toLegalText(param[0]);
                        value = cons.newInstance(param[0]);
                    }
                    for (Method m : methods) {

                        if (m.getName().equalsIgnoreCase(StringUtils.field2SetMethod(f.getName()))) {
                            //TODO
                            System.out.println("初始化属性："+f.getName()+"属性值："+value);
                            m.invoke(obj, value);
                        }
                    }
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    /**
                     * 某些属性可能由于非法输入而无法初始化，这里无需处理
                     */
                    System.out.println("无法初始化该属性: "+f.getName()+" 属性值："+param[0]);
                }
            }
        }
        return obj;
    }

}
