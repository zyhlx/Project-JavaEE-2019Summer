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
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="../common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="../css/nav/header_line.css">
    <link href="../common/awesome-bootstrap-checkbox.css" rel="stylesheet">

    <style>
        main {
            padding-top: 50px!important;
        }

        img {
            height: 120px;
            width: 120px;
        }

        .btn-add {
            margin-top: 1em;
            margin-left: 10%;
        }

        .user-result {
            margin-top: 2em;
            margin-left: 7%;
            margin-right: 7%;
            padding: 1em;
            width: 86%;
        }

        .info-title {
            font-weight: bold;
        }

        .user-name {
            font-size: 20px;
        }

        main h1 {
            font-family: "MetSerif", "Georgia", serif;
            font-size: 48px;
            font-weight: 400;
            line-height: 1.166;
            margin-left: 1.7em;
            margin-top: 0.7em;
        }
        .btn-search{
            margin-left: 5em;
            margin-top: 1.7em;
        }

    </style>

</head>
<%@ include file="../common/nav.jsp" %>
<body>
<main>
<h1>
    Friends
</h1>
    <section>
<c:forEach items="${friends}" var="friendItem" >
    <div class="row border user-result">
        <div class="col-2">
            <p class="type">${friendItem.client.type}</p>
            <img src="images/user/${friendItem.client.type}.jpg" alt="">
        </div>
        <div class="col-8 panel panel-default">
            <div class="panel-heading user-name">${friendItem.client.username}</div>
            <table class="table">
                <tr><td class="info-title">邮箱</td><td>${friendItem.client.email}</td></tr>
                <tr><td class="info-title">个性签名</td><td>${friendItem.client.signature}</td></tr>
                <tr><td class="info-title">最近收藏</td><td><c:forEach items="${friendItem.client.favours}" var="favourItem">
                    <a href="./detailDisplay?paintingID=${favourItem.painting.paintingID}"> ${favourItem.painting.title} </a>
                </c:forEach></td>
                </tr>
            </table>
        </div>
    </div>
</c:forEach>

    </section>
    <p>
       <a href="./friendSearch">  <button class="btn btn-search" type="button"> 搜索</button> </a>
    </p>
</main>
</body>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="../common/simply-toast/simply-toast.js"></script>
<script src="../js/nav/registe.js"></script>
<script src="../js/nav/login.js"></script>
<script src="../js/nav/moveline.js"></script>
<script src="../js/nav/nav.js"></script>
<%--页面相应逻辑--%>
<script>
    $(".btn-delete").on("click", function () {
        var userID = $(this).attr("id").substring(7);
        $.post("./userManagement", {
            function: "0",
            userID: userID
        }, function (result) {
            window.location.reload();
            $.simplyToast(result.msg, 'info');
        });
    });

    $(".btn-change").on("click", function () {
        var userID = $(this).attr("id").substring(7);
        $.post("./userManagement", {
            function: "1",
            userID: userID
        }, function (result) {
            window.location.reload();
            $.simplyToast(result.msg, 'info');
        });
    });


</script>
</html>
