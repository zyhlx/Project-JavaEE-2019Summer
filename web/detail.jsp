<%--
  Created by IntelliJ IDEA.
  User: christine
  Date: 2019/7/12
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <link href="common/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/paintingDetail/detail.css">

</head>
<body>
<%@ include file="common/nav.jsp" %>
<main>
<section>
    <div class="row" id="upper-part">

        <div class="col-4" id="description-container">
            <h3><b>${painting.title}</b>,${painting.yearOfWork}
            </h3>

            <p>${painting.description}</p>

        </div>
        <div class="col-7 bg-light text-dark" id="picture-container">
            <img src="博物馆图片资源/其他/${painting.imageFileName}" alt="" id="picture">
            <div id="icons">
                <button type="button" class="btn btn-light text-left btn-favour" id="btn-favour-${painting.paintingID}">
                    <i class="fa fa-heart-o"></i>
                </button>
                <button type="button" class="btn btn-light text-left btn-manage" id="btn-manage-${painting.paintingID}">
                    <i class="fa fa-folder-open"></i>
                </button>

            </div>

        </div>

    </div>

    <HR>

    <div class="panel panel-default" id="detail-panel">
        <div class="panel-heading">Object Details</div>
        <table class="table">
            <tr>
                <td class="detail-title">On view:</td>
                <td>${painting.gallery}
                </td>
            </tr>
            <tr>
                <td class="detail-title">Year of work:</td>
                <td>${painting.yearOfWork}</td>
            </tr>
            <tr>
                <td class="detail-title">Heat:</td>
                <td>${painting.msrp}</td>
            </tr>
        </table>
    </div>
<c:if test="${!painting.videoPath.equals(\"\")}">
    <div>
        <video width="40%" height="30%" controls="controls">" +
            <source src="博物馆图片资源/videos/${painting.videoPath}" type="video/mp4" />
            <source src="博物馆图片资源/videos/${painting.videoPath}" type="video/ogg" />
            <source src="博物馆图片资源/videos/${painting.videoPath}" type="video/webm" />
            <object data="博物馆图片资源/videos/${painting.videoPath}"   width="40%" height="30%">
            <embed src="博物馆图片资源/videos/${painting.videoPath}" width="40%" height="30%" />
            </object>
            </video>
    </div>
</c:if>

</section>
</main>
</body>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="js/session.js"></script>
<script>

    $(".btn-favour").on("click", function () {
        //如果已登录，收藏
        var userName = "<%=session.getAttribute("user")%>";
        var artworkID = $(this).attr("id").substring(11);
        if (userName !== "null") {
            $.post("./favour", {
                userName: userName,
                artworkID: artworkID
            }, function (result) {
                $.simplyToast(result.msg, 'info');
            });
        }
        else {
            $.simplyToast("请先登录", 'warning');
        }
    });

    $(".btn-manage").on("click", function () {
        var artworkID = $(this).attr("id").substring(11);
        location.href = "./workDisplay?artworkID="+artworkID;
    });
</script>


<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>
<script src="js/nav/registe.js"></script>
<script src="js/nav/login.js"></script>
<script src="js/nav/moveline.js"></script>
<script src="js/nav/nav.js"></script>

</html>

