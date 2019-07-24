
<%--
  Created by IntelliJ IDEA.
  User: christine
  Date: 2019/7/17
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"  %>
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

    <link href="common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <%--<link rel="stylesheet" type="text/css" href="../css/index/index.css">--%>
    <link rel="stylesheet" type="text/css" href="css/management/work-management.css">

</head>
<body>
<%@ include file="../common/nav.jsp" %>
<main>
    <h1>
    <c:if test="${painting.paintingID == 0}">
        New Artwork
    </c:if>
        <c:if test="${painting.paintingID != 0}">
            Change Info
        </c:if>
    </h1>
<section id="info">
<form action="upload" method="post" name="changeForm" role="form" id="form-change" enctype="multipart/form-data" >
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
        <div id="showFile"><img src="博物馆图片资源/其他/${painting.imageFileName}"  onerror="this.src='./images/assert/icon/ico-no-image.svg'"></div>
    </div>
    <div class="form-group">
        <label for="video">上传视频</label>
        <input type="file" name="video" id="video" onchange="previewVideo()" required="required">
        <div id="showVideo"></div>
    </div>

    <input hidden="hidden" name="artworkID" id="artworkID" value="${painting.paintingID}">

</form>
    <button type="submit" class="btn btn-primary" id="btn-change">保存修改</button>
<c:if test="${painting.paintingID != 0}">
    <button type="button" class="btn btn-delete btn-primary" id="btn-${painting.paintingID}">删除</button>
</c:if>
    <button type="button" class="btn btn-primary" id="btn-add">添加作品</button>
</section>
</main>

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
<script src="js/management/work-management.js"></script>

<script>
    $("#btn-change").on("click", function() {
        var file=document.getElementById("video").files[0];
        var pic=document.getElementById("file").files[0];
        if ($("#title").val() === "" || $("#description").val() === "" || $("#place").val() === "" || $("#year").val() === "") {
            $.simplyToast("请输入完整信息！");
            return;
        }
        else if (videoCheck === 0 && file != null) {
            $.simplyToast("请输入正确的视频！");
            return;
        }
        else if (picCheck === 0 && pic !=null) {
            $.simplyToast("请输入正确的照片！");
            return;
        }
        var form = new FormData(document.getElementById("form-change"));
        $.ajax({
            url: "./upload.work",
            type: "POST",
            data: form,
            dataType: "json",
            processData: false,
            contentType: false,
            success: function (data) {
                if (data.type === "true") {
                    $.simplyToast("上传成功", 'info');
                } else {
                    $.simplyToast("上传失败，可能由于重复的名称/图片", 'warning');
                }

                //window.location.href='localhost/pj2/upload.html';
            },
            error: function (e) {
                alert(e);
                window.clearInterval(timer);
            }
        });


    });

    $("#btn-add").on("click", function () {
        location.href = "./work.display?artworkID=0";
    });

    $(".btn-delete").on("click", function () {
        //如果已登录，收藏
        var artworkID = $(this).attr("id").substring(4);
        $.post("./delete.work", {
            artworkID: artworkID
        }, function (result) {
            $.simplyToast(result.msg, 'info');
        });

    });

</script>

</html>


