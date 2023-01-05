<%--
  Created by IntelliJ IDEA.
  User: 25148
  Date: 2023-01-02
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<c:if test="${message!=null}">
    <div class="alert alert-warning alert-dismissable" style="margin-bottom: 0">
        <button type="button" class="close" data-dismiss="alert"
                aria-hidden="true">
            &times;
        </button>
        提示：${message}
    </div>
</c:if>
<div class="error">
    404
</div>
</body>
<style>
    .error{
        font-size: xxx-large;
    }
</style>
</html>
