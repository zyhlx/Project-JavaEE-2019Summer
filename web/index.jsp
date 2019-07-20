<%--
  Created by IntelliJ IDEA.
  bean.User: DELL
  Date: 2019/7/10
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Art Museum</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link href="common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <link rel="stylesheet" type="text/css" href="css/index/index.css">

</head>
<body>
<%@ include file="common/nav.jsp" %>

<%--<div class="container">--%>
<%--<h3>折叠导航栏</h3>--%>
<%--<p>通常，小屏幕上我们都会折叠导航栏，通过点击来显示导航选项。</p>--%>
<%--<p>提示: 如果你删除 .navbar-expand-md 类，导航链接会一直隐藏，且切换按钮会一直显示。</p>--%>
<%--</div>--%>
<%
    String flag = (String) request.getAttribute("flag");
    if (flag == null) {
        request.getRequestDispatcher("/index").forward(request, response);
    }
%>

<main class="d-flex flex-column">
    <%--<h1 class="title">热门展品展示</h1>--%>
    <div class="card card-border-none" id="hot_arts">
        <div class="card-body">
            <%--mb-3 mr-3 ml-3--%>
            <div id="demo" class="carousel slide " data-ride="carousel">
                <!-- 指示符 -->
                <ul class="carousel-indicators">
                    <li data-target="#demo" data-slide-to="0" class="active"></li>
                    <li data-target="#demo" data-slide-to="1"></li>
                    <li data-target="#demo" data-slide-to="2"></li>
                </ul>
                <!-- 轮播图片 -->
                <div class="carousel-inner">
                    <c:forEach items="${hotPaintings}" var="paintingItem" begin="0" end="0">
                        <%-- media border p-1--%>
                        <div class="carousel-item media p-1 active">
                            <img src="博物馆图片资源/其他/<c:out value="${paintingItem.imageFileName}"/>"
                                 class="img-responsive center-block mr-3 mt-1"
                                 height='200px'>
                            <div class="carousel-caption">
                                <a href="detail.jsp?PaintingID=<c:out value="${paintingItem.paintingID}"/>">
                                    <h3><c:out value="${paintingItem.title}"/></h3>
                                </a>
                                <p><c:out value="${paintingItem.description}"/></p>
                            </div>
                        </div>
                    </c:forEach>

                    <c:forEach items="${hotPaintings}" var="paintingItem" begin="1" end="2" step="1"
                               varStatus="paintingStatus">
                        <div class="carousel-item media p-1">
                            <img src="博物馆图片资源/其他/<c:out value="${paintingItem.imageFileName}"/>"
                                 class="img-responsive center-block mr-3 mt-1"
                                 height='200px'>
                            <div class="carousel-caption">
                                <a href="detail.jsp?PaintingID=<c:out value="${paintingItem.paintingID}"/>">
                                    <h3><c:out value="${paintingItem.title}"/></h3>
                                </a>
                                <p><c:out value="${paintingItem.description}"/></p>
                            </div>
                        </div>
                    </c:forEach>

                </div>

                <!-- 左右切换按钮 -->
                <a class="carousel-control-prev" href="#demo" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demo" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>
        </div>
    </div>

    <div class="card card-border-none">
        <div class="card-body row">
            <c:forEach items="${lastPublish}" var="paintingItem" varStatus="paintingStatus">
                <div class="p-3 col-sm-4 d-flex flex-column">
                    <div class="d-flex justify-content-center">
                        <img src="博物馆图片资源/其他/<c:out value="${paintingItem.imageFileName}"/>"
                             class="rounded-circle border-3" height='250px'>
                    </div>
                    <div class="text-center mt-3">
                        <h4 class="card-title"><c:out value="${paintingItem.title}"/></h4>
                        <p class="card-text text-leave"><c:out value="${paintingItem.description}"/></p>
                        <a href='detail.jsp?paintingID=<c:out value="${paintingItem.paintingID}"/>'
                           class="btn btn-primary">See Profile</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>


<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>
<script src="js/nav/registe.js"></script>
<script src="js/nav/login.js"></script>
<script src="js/nav/moveline.js"></script>

<script src="js/nav/nav.js"></script>
<%--<script>--%>

<%--</script>--%>
<%--<script>--%>

<%--</script>--%>


</body>
</html>
