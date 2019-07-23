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
    <link href="../css/friend-display/friend-display.css" rel="stylesheet">

</head>
<%@ include file="../common/nav.jsp" %>
<body>
<main>
<h1 class="">
    Friends <a href="./friendSearch">  <button class="btn btn-search btn-primary" type="button"> 搜索</button> </a>
</h1>

    <section>
<c:forEach items="${friends}" var="friendItem" >
    <div class="row border user-result">
        <div class="col-2">
            <p class="type">${friendItem.client.type}</p>
            <img src="images/user/${friendItem.client.type}.jpg" alt="">
        </div>
        <div class="col-8 panel panel-default">
            <div class="panel-heading user-name"><a href="userDetail?userID=<c:out value="${friendItem.client.userID}"/>">${friendItem.client.username}</a></div>
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
<script src="../js/friend-display/friend.js"></script>
</html>
