<%--
  Created by IntelliJ IDEA.
  User: christine
  Date: 2019/7/16
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <link href="common/awesome-bootstrap-checkbox.css" rel="stylesheet">

    <style>
        img{
            height: 120px;
            width: 120px;
        }
        .btn-add{
            margin-top: 1em;
            margin-left: 10%;
        }
        .user-result{
            margin-top: 2em;
            margin-left: 10%;
            margin-right: 10%;
            padding: 1.5em;
            width: 80%;
        }
        .info-title{
            font-weight: bold;
        }
        .user-name{
            font-size: 20px;
        }

    </style>

</head>
<%@ include file="common/nav.jsp"%>
<body>
<c:forEach items="${friends}" var="friendItem" >
    <div class="row border user-result">
        <div class="col-2">
            <p class="type">${friendItem.type}</p>
            <img src="images/user/${friendItem.type}.jpg" alt="">
        </div>
        <div class="col-8 panel panel-default">
            <div class="panel-heading user-name">${friendItem.username}</div>
            <table class="table">
                <tr><td class="info-title">邮箱</td><td>${friendItem.email}</td></tr>
                <tr><td class="info-title">个性签名</td><td>${friendItem.signature}</td></tr>
                <tr><td class="info-title">最近收藏</td><td></td>
                <c:forEach items="${friendItem.favours}" var="favourItem">
                    <a href="./detailDisplay?paintingID=${favourItem.paintingID}"> ${favourItem.title} </a>
                </c:forEach>
                </tr>
            </table>
        </div>
    </div>
</c:forEach>

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
<%--页面相应逻辑--%>
<script>
    $(".btn-delete").on("click", function () {
        var userID = $(this).attr("id").substring(7);
        $.post("./userManagement",{
            function: "0",
            userID: userID
        },function (result) {
            window.location.reload();
            $.simplyToast(result.msg, 'info');
        });
    });

    $(".btn-change").on("click", function () {
        var userID = $(this).attr("id").substring(7);
        $.post("./userManagement",{
            function: "1",
            userID: userID
        },function (result) {
            window.location.reload();
            $.simplyToast(result.msg, 'info');
        });
    });

</script>


<%--<script>--%>
    <%--$('#move').moveline({--%>
        <%--color: '#1a73e8',--%>
        <%--position: 'inner',--%>
        <%--click: function (ret) {--%>
            <%--ret.ele.addClass('active').siblings().removeClass('active');--%>
        <%--}--%>
    <%--});--%>
<%--</script>--%>
<%--<script>--%>
    <%--$(".mobile-nav-taggle").click(function () {--%>
        <%--var mobileMenu = $("#mobile-menu");--%>
        <%--if (mobileMenu.hasClass("show-nav")) {--%>
            <%--setTimeout(function () {--%>
                <%--mobileMenu.addClass("hide-nav").removeClass("show-nav");--%>
            <%--}, 100)--%>
        <%--}--%>
        <%--else {--%>
            <%--setTimeout(function () {--%>
                <%--mobileMenu.addClass("show-nav").removeClass("hide-nav");--%>
            <%--}, 100)--%>
        <%--}--%>
    <%--});--%>
<%--</script>--%>

</html>
