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
    <link rel="stylesheet" type="text/css" href="css/index/index.css">

    <style>
        img{
            height: 120px;
            width: 120px;
        }
        .btn-add{
            margin-top: 1em;
            /*margin-left: 7%;*/
        }
        .user-result{
            margin-top: 2em;
            /*margin-left: 7%;*/
            /*margin-right: 7%;*/
            padding: 1.5em;
            //width: 86%;
        }
        .info-title{
            font-weight: bold;
        }
        .user-name{
            font-size: 20px;
        }


    </style>

</head>
<%@ include file="../common/nav.jsp"%>
<body>
<main>

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
        <p><button type="button" class="btn btn-change" id="change-${userItem.userID}">
            <c:if test="${userItem.type.equals(\"normal\")}">
                提升为管理员
            </c:if>
            <c:if test="${userItem.type.equals(\"admin\")}">
                降为普通用户
            </c:if>
        </button></p>
        <p><button type="button" class="btn btn-delete" id="delete-${userItem.userID}">删除</button></p>
    </div>
</div>
</c:forEach>
    <a href="#" class="nav-link" data-toggle="modal" data-target="#AddUser">
    <button type="button" class="btn btn-add">添加用户</button>
    </a>
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
                        <label for="pwd-confirm-add" id="pwd-confirm-add-label">确认密码：</label>
                        <input type="password" class="form-control" name="pwd-confirm-add" id="pwd-confirm-add"
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

    function addUser() {
        var form1= document.getElementById('form-add');
        if (document.getElementById('username-add').value === "") {
            $.simplyToast("用户名不能为空",'warning');
            return false;
        }
        else if (document.getElementById('password-add').value === "") {
            $.simplyToast("密码不能为空",'warning');
            return false;
        }
        else {
            $.ajax({
                //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "./userManagement",//url
                data: $(form1).serialize(),
                success: function (result) {

                    if (result.type === "true") {
                        $.simplyToast(result.msg, 'success');
                        setTimeout('history.go(0)', 2000);


                    } else {
                        $.simplyToast(result.msg, 'info');
                        setTimeout('history.go(0)', 2000);
                        // setTimeout('history.go(0)',2000);
                    }
                },
                error: function () {
                    $.simplyToast("未知错误", 'warning');
                    // alert("异常！");
                }
            });
        }

    }

</script>

</html>
