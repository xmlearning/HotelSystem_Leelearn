# 酒店管理系统
## 前景介绍
原项目来源于 https://github.com/misterchaos/HotelSystem 

非常感谢大佬！

xhotel是一个酒店管理系统，提供查看房间，预订房间，个人信息管理，房间和酒店信息管理(管理员)等功能

该项目为Java开发，由Java,tomcat,mysql,servlet,jsp实现，未使用任何框架。

Leelearn于2022年12月29日开始学习本项目，并在原始项目进行改动（个人想法）。
## 信息
开发者：leelearn

联系邮箱：lee_learn@foxmail.com

项目开发时间：2022年12月29日至 

版本号：1.0.0

开源许可：

运行环境：
- tomcat 8.5.42
- mysql 5.7.39 （本人用的是服务器上的）
- jdk 1.8

##学习路程
### 1、idea创建一个Java项目，并添加web支持

### 2、导入jar包
- jsp-api.jar
- servlet-api.jar
- mysql-connector-java-8.0.15.jar
- jstl-1.2.jar
- jstl-api-1.2.jar
- jstl-impl-1.2.jar
- standard-1.1.2.jar

### 3、了解业务需求、功能
![](.\images\Xhotel项目需求分析.png)
![](.\images\Xhotel功能模块分析.png)

### 4、taglib标签库描述文件了解！
tld全称为：Tag Library Description，即标签库描述文件

tld文件用来配置标签库的基本信息

taglib.tld主要元素
```xml
<?xml version="1.0"encoding="UTF-8" ?>

<taglib

xmlns="http://java.sun.com/xml/ns/j2ee"

xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"

xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee web-jsptaglibrary_2_0.xsd"

version="2.0">

    <tlibversion>1.0</tlibversion>

    <jspversion>2.0</jspversion>

    <shortname>taglib</shortname>

    <uri>http://notes.javaee.jsp.com/taglib</uri>

    <info>Private Taglib</info>

    <tag>

        <name>copyright</name>

        <tagclass>notes.javaee.jsp.taglib.Copyright</tagclass>

        <bodycontent>JSP</bodycontent>

        <info>Copyright tag.</info>

    </tag>

</taglib>
```
- shortname指明推荐使用的prefix
- uri指明引用这个标签库时使用的uri
- tag指明要定义标签的信息

| 属性          | 描述                                                                                                            |
|-------------|---------------------------------------------------------------------------------------------------------------|
| name        | 定义属性的名称。每个标签的是属性名称必须是唯一的。                                                                                     |
| tagclass    | 指定映射的Java类。                                                                                                   |
| required    | 指定属性是否是必须的或者可选的,如果设置为false为可选。                                                                                |
| rtexprvalue | 声明在运行表达式时，标签属性是否有效。                                                                                           |
| type        | 定义该属性的Java类类型 。默认指定为String。                                                                                   |
| description | 描述信息。                                                                                                         |
| fragment    | 如果声明了该属性,属性值将被视为一个JspFragment。                                                                                |
| bodycontent  | 指明标签体的限制，有3种取值：empty、JSP和tagdependent empty：不允许有标签体存在。 JSP：允许有标签体存在。 tagdependent：允许有标签体存在，但是标签体内的JSP代码不会被执行。 |

如果tld文件位于/WEB-INF/下面，Tomcat会自动加载tld文件中的标签库。

如果位于其他的位置，可以在web.xml中配置。

```xml
<jsp-config>

    <taglib>

        <taglib-uri>http://notes.javaee.jsp.com/taglib</taglib-uri>

        <taglib-location>/WEB-INF/taglib.tld</taglib-location>

    </taglib>

</jsp-config>
```

或者在JSP中直接使用

```xml
<%@ taglib uri="/WEB-INF/taglib.tld" prefix="taglib"%>
```

此处的话，原作者采用多种方式，便于我们更好的理解标签库的使用！

最好回顾一下jstl，我基础没打牢固，忘了。。。

https://www.runoob.com/jsp/jsp-jstl.html 

随着学习，我发现为什么原作者复制了需要使用的tld文件在WEB-INF中，因为在jstl-1.1版本要求standard.jar，
在jstl-1.2中并不要求standard.jar,但是还是需要导入standard.jar包，不然的话会找不到Cannot resolve taglib with uri http://java.sun.com/jsp/jstl/core

此处还需要导入jstl-api.jar,jstl-impl.jar包，不然的话会报错 java.lang.NoClassDefFoundError: javax/servlet/jsp/jstl/core/ConditionalTagSupport

那么在此时我就使用jstl-1.2版本。

### 5、前端页面编写

整个项目我看了，在后端上我觉得是非常值得学习的！ 但是个人感觉而言，前端不太有美感,但是我也没有美感（唉。。。。可能觉得不好，但是就是写不出我想要的感觉，也许还是能力不足）
，所以在前端上，本人有自己修改，但是还是唉、、能力不够耶

### 6、后端编写
包的创建及功能
- annotation:
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MthCache {
    String key();
}
```
  - @Documented 注解：指明修饰的注解，可以被例如javadoc此类的董局工具文档化。只负责标记，没有成员取值。
  - @Target 注解：指明了修饰的这个注解的使用范围，即被描述的注解可以用在哪里。
    
    TYPE:类，接口或者枚举 ;FIELD:域，包含枚举常量 ;METHOD:方法 ;PARAMETER:参数 ;CONSTRUCTOR:构造方法 ;LOCAL_VARIABLE:局部变量 ;ANNOTATION_TYPE:注解类型 ;PACKAGE:包

  - @Retention 注解:指明修饰的注解的生存周期，即会保留到哪个阶段。

    SOURCE：源码级别保留，编译后即丢弃。

    CLASS:编译级别保留，编译后的class文件中存在，在jvm运行时丢弃，这是默认值。

    RUNTIME： 运行级别保留，编译后的class文件中存在，在jvm运行时保留，可以被反射调用。
  - Inherited注解：允许子类继承父类中的注解。

  
- controller:
  - constant： 常量 方法、页面枚举类
  - filter：里面包含有页面编码过滤器，以及登录页面过滤器。
    - @WebFilter：注解，该注解就是简化web.xml中的filter的配置。
```java
@WebFilter(servletNames = {"SimpleServlet"},filterName="SimpleFilter") 
public class SimpleFilter implements Filter{...}
```
```xml
<filter> 
　　<filter-name>SimpleFilter</filter-name> 
　　<filter-class>xxx</filter-class> 
</filter> 
<filter-mapping> 
　　<filter-name>SimpleFilter</filter-name> 
　　<servlet-name>SimpleServlet</servlet-name> 
</filter-mapping>
```
String类：equals() 会判断大小写区别，equalsIgnoreCase() 不会判断大小写区别： 
- dao:
- exception:
- po:
  - Cloneable是标记型的接口，它们内部都没有方法和属性，实现 Cloneable来表示该对象能被克隆，能使用Object.clone()方法。如果没有实现 Cloneable的类对象调用clone()就会抛出CloneNotSupportedException。

- proxy:
- service:
- util: newInstance()->通过 Class 类的 newInstance() 方法创建对象，该方法要求该 Class 对应类有无参构造方法。执行 newInstance()方法实际上就是使用对应类的无参构造方法来创建该类的实例，其代码的作用等价于Super sup = new Super()。
- vo

遵循原作者的规定，项目仅供学习交流，勿商用

#　总结
第一次系统学习，不太清楚如何去学习一个完整的项目，所以决定去看一个项目实战视频，了解一下流程。。。。。。


