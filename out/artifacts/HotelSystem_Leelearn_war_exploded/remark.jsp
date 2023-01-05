<%--
  Created by IntelliJ IDEA.
  User: 25148
  Date: 2023-01-02
  Time: 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lee.www.controller.constant.Pages" %>
<%@ page import="com.lee.www.controller.constant.Methods" %>
<html>
<head>
    <title>Xhotel</title>
    <meta charset="UTF-8">
    <meta id="viewport" name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body  style="background-image: linear-gradient(45deg, #40B028, #1B6EC6)">

<c:if test="${message!=null}">
    <div class="alert alert-warning alert-dismissable" style="margin-bottom: 0">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
        提示：${message}
    </div>
</c:if>

<%-- 网页头部 --%>
<nav class="navbar navbar-default" role="navigation" style="margin-bottom: 0px    ; top: 0;width: 100%;
        position: fixed;
        z-index: 999;
        margin-top: 0;">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/${Pages.INDEX_JSP.toString()}">X-HOTEL</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">

                <c:if test="${USER == null}">
                    <li class="active"><a href="${Pages.LOGIN_JSP.toString()}">登陆</a></li>
                    <li><a href="${Pages.REGIST_JSP.toString()}">注册</a></li>
                </c:if>
                <c:if test="${USER != null}">
                    <li class="active"></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">个人中心<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="${Pages.USER_JSP.toString()}?view=user&name=${USER}">查看个人信息</a></li>
                            <li>
                                <a href="${Pages.USER_JSP.toString()}?view=update&update=info&name=${USER}">编辑个人信息</a>
                            </li>
                            <li>
                                <a href="${Pages.USER_JSP.toString()}?view=update&update=pwd&name=${USER}">修改登陆密码</a>
                            </li>
                            <li>
                                <a href="${Pages.USER_JSP.toString()}?view=update&update=pay_pwd&name=${USER}">修改支付密码</a>
                            </li>
                            <li>
                                <a href="${Pages.ORDER_JSP.toString()}?view=order&user=${USER}">查看个人订单</a>
                            </li>
                            <li><a href="/user?method=${Methods.LOGOUT_DO.toString()}">退出登陆</a></li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${ADMIN!=null}">
                    <li class="active"></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">管理员中心<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/hotel.html">查看酒店信息</a></li>
                            <li><a href="${Pages.ROOM_JSP.toString()}?view=add">添加房间</a></li>
                            <li><a href="${Pages.USER_JSP.toString()}?view=add">添加用户</a></li>
                        </ul>
                    </li>
                </c:if>


                <li><a href="${Pages.REMARK_JSP.toString()}">留言板</a></li>
                <li>
                    <form id="search" class="bs-example bs-example-form" role="form"
                          action="room?method=${Methods.FIND_DO}&find=name&page=1"
                          method="post">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="input-group"
                                     style="width: 400px; position: relative; left: 20%;margin-top: 8px">
                                    <input type="text" required="required" class="form-control" name="name"
                                           placeholder="请输入关键词" value="${param.name}">
                                    <span class="input-group-btn"><input type="submit" value="搜索"
                                                                         class="btn btn-default"></span>

                                </div>
                            </div>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>





<c:if test="${data==null}">
    <c:redirect url="/remark?method=${Methods.FIND_DO}"/>
</c:if>
<div class="well well-lg" style="height:fit-content;width: 80%;margin: 50px;max-width: 800px;padding: 10px;margin-top: 100px;margin-left: 200px;">

    <form action="/remark?method=${Methods.ADD_DO}" method="post">
        <textarea name="remark" class="form-control" autofocus="autofocus" cols="100"
                  required="required" maxlength="200" style="height: 120px;width: 100%;
                  margin-bottom: 15px;resize: none;outline: none" placeholder="在这里写一些东西吧！(留言一旦提交不可修改和删除)"></textarea>
        <input type="text" name="userName" value="${USER}" hidden="hidden">
        <input type="submit" class="form-control" style="width: 150px;margin-left:628px ">
    </form>
</div>
<c:if test="${data.remarks.size()>0}">
    <c:forEach var="i" begin="0" end="${data.remarks.size()-1}">
        <div class="well well-lg" style="height:fit-content;width: 80%;margin: 50px;max-width: 800px;padding: 10px;margin-left: 200px;">
            <p>${data.remarks[i].remark}</p>
            <hr style="height: 1px;background-color: #4E4E4E;">
            <p style="text-align: right">${data.remarks[i].userName} 写于 ${data.remarks[i].gmtCreate}</p>
        </div>
    </c:forEach>
</c:if>
</div>
</body>

<style>

    .navbar-default {
        width: 100%;
        margin-bottom: 0px;
        position: fixed;
        z-index: 999
    }


    .alert-warning {
        margin-bottom: 0;
        z-index: 998;
        position: fixed;
        margin-top: 65px;
        width: 100%;
    }

    .background {
        background-image: linear-gradient(45deg, #40B028, #1B6EC6);
        position: fixed;
        height: 100%;
        width: 100%;

    }

    .navbar-default {

    }
    .alert-warning {
        margin-bottom: 0;
        z-index: 998;
        position: fixed;
        margin-top: 0px;
        width: 100%;
    }
</style>

</html>
