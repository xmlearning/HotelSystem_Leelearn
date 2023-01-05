<%--
  Created by IntelliJ IDEA.
  User: 25148
  Date: 2023-01-02
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lee.www.controller.constant.Pages" %>
<html>
<head>
    <title>图片</title>
    <meta charset="UTF-8">
    <meta id="viewport" name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${data==null}">
    <c:redirect url="${Pages.INDEX_JSP.toString()}"/>
</c:if>


<%-- 网页头部 --%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/${Pages.INDEX_JSP.toString()}">X-HOTEL</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${Pages.LOGIN_JSP.toString()}">登陆</a></li>
                <li><a href="${Pages.REGIST_JSP.toString()}">注册</a></li>
            </ul>
        </div>
    </div>
</nav>
<c:if test="${message!=null}">
    <div class="alert alert-warning alert-dismissable" style="margin-bottom: 0">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
        提示：${message}
    </div>
</c:if>

<c:if test="${data.pictures.size()>0}">
    <c:forEach var="i" begin="0" end="${data.pictures.size()-1}">
        <img src="/file/photo/${date.pictures[i]}" style="width: 500px ;float: left">
    </c:forEach>
</c:if>

</body>
</html>
