<%--
  Created by IntelliJ IDEA.
  User: Leelearn
  Date: 2022-12-29
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.lee.www.controller.constant.Pages" %>
<%@ page import="com.lee.www.controller.constant.Methods" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>XHotel</title>
    <meta charset="utf-8">
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=yes"
          id="viewport" name="viewport">
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

  </head>
  <body style="background-image: linear-gradient(45deg, #40B028, #1B6EC6)">

  <c:if test="${data == null}">
    <c:redirect url="/room?method=${Methods.FIND_DO.name()}&find=all&name=&page=1"/>
  </c:if>

  <%-- 网页头部 --%>
  <nav class="navbar navbar-default" role="navigation" style="margin-bottom: 0px">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#example-navbar-collapse">
          <span class="sr-only">切换导航</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a title="回到首页" class="navbar-brand" href="${pageContext.request.contextPath}/${Pages.INDEX_JSP.toString()}">X-HOTEL</a>
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
                <li><a href="${Pages.USER_JSP.toString()}?view=update&update=info&name=${USER}">编辑个人信息</a>
                </li>
                <li><a href="${Pages.USER_JSP.toString()}?view=update&update=pwd&name=${USER}">修改登陆密码</a>
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
          <c:if test="${ADMIN != null}">
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
                    <input type="text" class="form-control" name="name"
                           placeholder="酒店名称" value="${param.name}">
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
  <c:if test="${message != null}">
    <div class="alert alert-warning alert-dismissable">
      <button type="button" class="close" data-dismiss="alert"
              aria-hidden="true">
        &times;
      </button>
      提示：${message}
    </div>
  </c:if>

  <div class="page-head">

  </div>
  <ul class="pagination pagination-lg" style="position: fixed;left: 30%">
    <li>
      <a href="room?method=${Methods.FIND_DO}&find=${param.find}&name=${param.name}&page=${param.page>1?param.page-1:1}">&laquo;</a>
    </li>
    <c:if test="${data.maxPage > 0}">
      <c:forEach var="i" begin="1" end="${data.maxPage-1}">
        <li><a href="room?method=${Methods.FIND_DO}&find=${param.find}&name=${param.name}&page=${i}">${i}</a></li>
      </c:forEach>
    </c:if>
    <li>
      <a href="room?method=${Methods.FIND_DO}&find=${param.find}&name=${param.name}&page=${param.page<data.maxPage?param.page+1:data.maxPage}">&raquo;</a>
    </li>
  </ul>
  <br>

  <c:if test="${data.rooms.size() > 0}">
    <c:forEach var="i" begin="0" end="${data.rooms.size()-1}">
      <div class="info-panel" >
        <div class="well well-lg" style="height: 280px;width: 75%">
          <a href="${pageContext.request.contextPath}/room.jsp?view=room&number=${data.rooms[i].number}">
              <%-- 主图和名称--%>
            <div class="photo-name">
              <img class="main_info" src="/file/photo/${data.rooms[i].photo}" width="250" height="250">
<%--              <h4>${data.rooms[i].name}</h4>--%>
            </div>
            <div class="room-info">
              <p>
                房间名称： <b>${data.rooms[i].name}</b>
              </p>
              <p>

                房间编号：${data.rooms[i].number}

              </p>
              <p>
                房间类型：${data.rooms[i].type}

              </p>
              <p>
                房间面积：${data.rooms[i].area}

              </p>
              <p>
                床宽：${data.rooms[i].bedWidth}|
                预订状态：${data.rooms[i].bookStatus}|
                房间等级：${data.rooms[i].level}|
                房间评分：${data.rooms[i].score}|
                评价数量：${data.rooms[i].remarkNum}
              </p>
              <strong>
                <h1 style="text-align: right;color: red">
                  $${data.rooms[i].price}
                </h1>
              </strong>
            </div>
          </a>
        </div>
      </div>
      <c:if test="${ADMIN!=null}">

        <div class="color-input-field" style="
    height: 40px;margin-bottom: 10px">
          <form name="delete" onsubmit="return deleteRoom()"
                action="${pageContext.request.contextPath}/room?method=${Methods.DELETE_DO}&number=${data.rooms[i].number}"
                method="post">
            <input type="submit" class="form-control" value="删除房间" style="
       background-color: orangered;
    width: 200px;
    height: 40px;
    /* float: right; */
    color: #FFFFFF;
    position: absolute;
    right: 230px;">
          </form>
          <form name="update"
                action="${pageContext.request.contextPath}/${Pages.ROOM_JSP.toString()}?view=update&number=${data.rooms[i].number}"
                method="post">
            <input type="submit" class="form-control" value="修改房间" style="
       background-color: seagreen;
    width: 200px;
    height: 40px;
    /* float: right; */
    color: #FFFFFF;
    position: absolute;
    right: 450px;">
          </form>
        </div>
        <script>
          function

          deleteRoom() {
            if (confirm("此操作将会永久删除此房间，是否继续？")) {
              return true;
            } else {
              return false;
            }
          }

        </script>
      </c:if>
    </c:forEach>
  </c:if>


  </body>
  <style>
    .info-panel {
      width: 100%;
      position: relative;
      margin-left: 140px;
    }

    .photo-name {
      height: 180px;
      width: 30%;
      float: left;

    }

    .room-info {
      height: 180px;
      width: 500px;
      position: relative;
      float: left;
    }

    .navbar-default {
      width: 100%;
      position: fixed;
      z-index: 999
    }

    .pagination-lg {
      position: fixed;
      z-index: 999;
      top: 90%;
      left: 52%;
    }

    .alert-warning {
      margin-bottom: 0;
      z-index: 998;
      position: fixed;
      margin-top: 58px;
      width: 100%;
    }
    .background{
      background-image: linear-gradient(45deg,#40B028,#1B6EC6);
      position: fixed;
      height: 100%;
      width: 100%;
    }
    .page-head{
      position: relative;
      height: 100px;
    }
  </style>
</html>
