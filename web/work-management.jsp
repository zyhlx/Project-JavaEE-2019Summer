
<%--
  Created by IntelliJ IDEA.
  User: christine
  Date: 2019/7/17
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%--%>
    <%--String flag = (String) request.getAttribute("flag");--%>
    <%--if (flag == null) {--%>

        <%--request.getRequestDispatcher("/workDisplay").forward(request, response);--%>
    <%--}--%>
<%--%>--%>

<html>
<head>
    <title>Art Museum</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
    <link href="common/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <link rel="stylesheet" type="text/css" href="css/index/index.css">

    <style>
        #info{
            margin-left: 7%;
            margin-right: 7%;
            margin-top: 5em;
        }
        img{
            height: 150px;
            width: auto;
        }
    </style>

</head>
<body>
<%@ include file="common/nav.jsp" %>
<section id="info">
<form action="./upload" method="post" name="changeForm" role="form" id="changeForm" enctype="multipart/form-data" >
    <div class="form-group">
        <label for="title">作品名:</label>

        <input type="text" name="title" class="form-control" id="title" required="required" value="${painting.title}">
    </div>
    <div class="form-group">
        <label for="description">简介:</label>
        <input type="text" name="description" class="form-control" id="description" required="required" value="${painting.description}">
    </div>
    <div class="form-group">
        <label for="place">馆藏地点:</label>
        <input type="text" name="place" class="form-control" id="place" required="required" value="${painting.gallery}">
    </div>
    <div class="form-group">
        <label for="year">出土/完成年份:</label>
        <input type="number" name="year" class="form-control" id="year" required="required" value="${painting.yearOfWork}">
    </div>
    <div class="form-group">
        <label for="file">上传图片</label>
        <input type="file" name="file" id="file" onchange="previewPic()" required="required" >
        <div id="showFile"><img src="博物馆图片资源/其他/${painting.imageFileName}"></div>
    </div>
    <div class="form-group">
        <label for="video">上传视频</label>
        <input type="file" name="video" id="video" onchange="previewVideo()" required="required">
        <div id="showVideo"></div>
    </div>

    <input hidden="hidden" value="${painting.paintingID}">
    <button type="submit" class="btn" id="btn-change">保存修改</button>
</form>
<c:if test="${painting.paintingID != 0}">
    <button type="button" class="btn btn-delete" id="btn-${painting.paintingID}">删除</button>

    <button type="button" class="btn" id="btn-add">添加作品</button>
</c:if>
</section>
</body>

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>

<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>



<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast.js"></script>
<%--<script src="js/nav/registe.js"></script>--%>
<%--<script src="js/nav/login.js"></script>--%>
<%--<script src="js/nav/moveline.js"></script>--%>
<script src="js/nav/nav.js"></script>
<script src="js/management/work-management.js"></script>

</html>


