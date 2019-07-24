<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: christine
  Date: 2019/7/16
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Museum</title>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
    <link href="common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <link href="css/management/user-management.css" rel="stylesheet">
</head>
<%@ include file="../common/nav.jsp"%>
<body>
<main>
    <a href="#" class="nav-link" data-toggle="modal" data-target="#AddUser">
        <button type="button" class="btn btn-add btn-primary">添加用户</button>
    </a>
<c:forEach items="${users}" var="userItem">
<div class="row border user-result">
    <div class="col-2">
        <p class="type">${userItem.type}</p>
        <img src="images/user/${userItem.type}.jpg" alt="">
    </div>
    <div class="col-8 panel panel-default">
        <div class="panel-heading user-name">${userItem.username}</div>
        <table class="table">
            <tr><td class="info-title">ID</td><td>${userItem.userID}</td></tr>
            <tr><td class="info-title">邮箱</td><td>${userItem.email}</td></tr>
            <tr><td class="info-title">最近登录</td><td>
                ${userItem.displayTime}
            </td></tr>
        </table>
    </div>
    <div class="col-2">
        <p><button type="button" class="btn btn-change btn-primary" id="change-${userItem.userID}">
            <c:if test="${userItem.type.equals(\"normal\")}">
                提升为管理员
            </c:if>
            <c:if test="${userItem.type.equals(\"admin\")}">
                降为普通用户
            </c:if>
        </button></p>
        <p><button type="button" class="btn btn-delete btn-primary" id="delete-${userItem.userID}">删除</button></p>
    </div>
</div>
</c:forEach>

</main>

<!-- 模态框 -->
<div class="modal fade" id="AddUser">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">注册</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form action="register" method="post" name="form_add" id="form-add">
                    <div class="form-group">
                        <label for="username-add" id="username-add-label">用户名:</label>
                        <input type="text" class="form-control" id="username-add" name="username-add"
                        >
                    </div>
                    <div class="form-group">
                        <label for="password-add" id="password-add-label">密码:</label>
                        <input type="password" class="form-control" name="password-add" id="password-add"
                        >
                    </div>

                    <div class="form-group">
                        <label for="email-add" id="email-add-label">邮箱:</label>
                        <input type="email" class="form-control" id="email-add" name="email-add" placeholder="Enter email"
                        >
                    </div>
                    <div class="form-group">
                        <label for="address-add" id="address-add-label">地址:</label>
                        <input type="text" class="form-control" id="address-add" name="address-add" placeholder="Enter address"
                        >
                    </div>
                    <div class="form-group">
                        <label for="tel-add" id="tel-add-label">电话:</label>
                        <input type="tel" class="form-control" id="tel-add" name="tel-add" placeholder="Enter tel"
                        >
                    </div>


                    <div class="d-flex justify-content-end">
                        <button type="button" onclick="addUser()" class="btn btn-primary btn-add-user">Submit</button>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="function" name="function" value="3" hidden="hidden"
                        >
                    </div>
                </form>
            </div>

            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>

        </div>
    </div>
</div>


</body>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>

<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>



<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>

<script src="js/nav/registe.js"></script>
<script src="js/nav/login.js"></script>
<script src="js/nav/moveline.js"></script>
<script src="js/nav/nav.js"></script>

<script src="js/management/user-management.js"></script>

<script>
    $(".btn-delete").on("click", function () {
        var userID = $(this).attr("id").substring(7);
        $.post("./delete.user",{
            function: "0",
            userID: userID
        },function (result) {
            $.simplyToast(result.msg, 'info');
            setTimeout('history.go(0)',2000);
        });
    });

    $(".btn-change").on("click", function () {
        var userID = $(this).attr("id").substring(7);
        $.post("./changePermission.user",{
            function: "1",
            userID: userID
        },function (result) {
            $.simplyToast(result.msg, 'info');
            setTimeout('history.go(0)',2000);
        });
    });
</script>


</html>
