<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Painting" %>
<%@ page import="db.DBUtil" %><%--
  Created by IntelliJ IDEA.
  bean.User: christine
  Date: 2019/7/14
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="../common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">

    <link href="../common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="../css/nav/header_line.css">
    <link href="../common/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="../css/detail/detail.css" rel="stylesheet">
    <style>


    </style>
</head>
<body>
<%@ include file="../common/nav.jsp"%>
<main>
<section id="content">
<c:choose>
    <c:when test="${favours.isEmpty()}">
        <div class="row border favour-result">
            <span>没有收藏~</span>
        </div>
    </c:when>
    <c:otherwise>
        <c:forEach items="${favours}" var="favourItem">
            <div class="row border favour-result">
                <div class="col-2">
                    <p class="type text-muted">Artwork</p>
                    <a href="./detailDisplay?paintingID=${favourItem.painting.paintingID}">
                        <img src="博物馆图片资源/其他/${favourItem.painting.imageFileName}" alt="">
                    </a>
                </div>
                <div class="col-8">
                    <p class="title">${favourItem.painting.title}</p>
                        <%--<p>heat:${favourItem.msrp}</p>--%>
                    <table>
                        <tr>
                            <td>permission</td>
                            <td>Favoured time</td>
                            <td>On view</td>
                        </tr>
                        <tr>
                            <td>
                                <c:if test="${favourItem.open == 0}">
                                    private
                                </c:if>
                                <c:if test="${favourItem.open != 0}">
                                    public
                                </c:if>
                            </td>

                            <td>
                                    ${favourItem.displayTime};
                            </td>
                            <td>${favourItem.painting.gallery}</td>
                        </tr>
                    </table>
                </div>
                <div class="col-2">
                    <p><button type="button" class="btn btn-delete" id="btn-delete-${favourItem.favourID}"> 删除</button> </p>
                    <p><button type="button" class=" btn btn-change" id="btn-change-${favourItem.favourID}">
                        <c:if test="${favourItem.open == 0}">
                            设为公开
                        </c:if>
                        <c:if test="${favourItem.open != 0}">
                            设为私密
                        </c:if>
                    </button></p>
                </div>
            </div>
        </c:forEach>

    </c:otherwise>
</c:choose>


</section>
</main>

</body>

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="../js/session.js"></script>
<script src="../js/favour/favour.js"></script>



<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="../common/simply-toast/simply-toast.js"></script>
<script src="../js/nav/registe.js"></script>
<script src="../js/nav/login.js"></script>
<script src="../js/nav/moveline.js"></script>
<script src="../js/nav/nav.js"></script>
</html>
