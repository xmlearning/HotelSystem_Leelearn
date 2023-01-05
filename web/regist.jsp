<%--
  Created by IntelliJ IDEA.
  User: 25148
  Date: 2022-12-30
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.lee.www.controller.constant.Pages" %>
<%@ page import="com.lee.www.controller.constant.Methods" %>
<html>
<head>
    <title>${param.title}</title>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1"/>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://www.recaptcha.net/recaptcha/api.js"></script>
</head>
<body>
<%--message 提示信息--%>

<form action="${pageContext.request.contextPath}/user?method=${Methods.REGIST_DO}" method="post" class="login">
    <p>XHotel</p>
    <c:if test="${message!=null}">
        <div class="alert alert-warning alert-dismissable" style="margin-bottom: 0">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
            提示：${message}
        </div>
    </c:if>
    <input type="text" required="required" name="name" title="6-20位英文字母，数字或下划线" placeholder="用户名">
    <input type="password" required="required" title="6-20位英文字母，数字或下划线" name="password" placeholder="密码">

    <input id="agree" type="checkbox" name="agreement" checked="checked" value="true">我已阅读并同意<a href="#">《XHotel用户协议》</a>
    <input type="submit" class="btn" value="注  册">
    <br>
    已有账号？<a href="${Pages.LOGIN_JSP.toString()}">请登陆</a>
</form>

</body>
<style type="text/css">
    .form-control {
        padding: 10px;
        height: 42px
    }

    .login-body {
        width: 100%;
        height: 800px;
    }

    .login-layout {
        position: relative;
        float: right;
        width: 80%;
        min-height: 540px;
        max-width: 450px;
        margin-right: 72px;
        margin-top: 40px;
        margin-left: 72px;
    }

    .login-box {
        position: relative;
        left: 0;
        right: 0;
        top: 50%;
        background: #fff;
        padding: 20px 20px 20px 20px;
        min-height: 300px;
        border-radius: 4px;
        -webkit-box-shadow: 0 1px 6px rgba(0, 0, 0, .1);
        box-shadow: 0 1px 6px rgba(0, 0, 0, .1);
    }

    * {
        user-select: none;
        /* 无法选中，整体感更强 */
    }

    body {
        background: url("/img/wallpaper.jpg") no-repeat;
        background-size: cover;
        background-attachment: fixed;
    }

    .login {
        position: absolute;
        top: 50%;
        margin-top: -200px;
        left: 50%;
        margin-left: -200px;
        /* absolute居中的一种方法 */
        background-color: whitesmoke;
        width: 400px;
        height: 400px;
        border-radius: 25px;
        text-align: center;
        padding: 5px 40px;
        box-sizing: border-box;
        /* 这样padding就不会影响大小 */
    }

    p {
        font-size: 42px;
        font-weight: 600;
    }

    input[type="text"] {
        background-color: whitesmoke;
        width: 100%;
        height: 48px;
        margin-bottom: 10px;
        border: none;
        border-bottom: 2px solid silver;
        /* 下面的会覆盖上面的步伐 */
        outline: none;
        font-size: 22px;
    }

    input[type="password"] {
        background-color: whitesmoke;
        width: 100%;
        height: 48px;
        margin-bottom: 10px;
        border: none;
        border-bottom: 2px solid silver;
        /* 下面的会覆盖上面的步伐 */
        outline: none;
        font-size: 22px;
    }

    input[type="submit"] {
        /*background-color: whitesmoke;*/
        width: 100%;
        height: 48px;
        margin-bottom: 10px;
        border: none;
        border-bottom: 2px solid silver;
        /* 下面的会覆盖上面的步伐 */
        outline: none;
        font-size: 22px;
    }

    .btn {
        background-color: #59c2c5;
        width: 38%;
        height: 48px;
        border-radius: 8px;
        margin-top: 40px;
        font-size: 28px;
        font-weight: 600;
        color: white;
    }

    .btn:hover {
        background-color: #59c2a0;
    }
</style>
</html>
