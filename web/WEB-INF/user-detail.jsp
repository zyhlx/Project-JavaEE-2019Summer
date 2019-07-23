<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/7/20
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Information</title>
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
    <link rel="stylesheet" type="text/css" href="css/user-detail/user-detail.css">


</head>
<body>
<%@ include file="../common/nav.jsp" %>
<main>
    <div class="detail-container">

        <div class="container-set">
            <div class="user-image" title="Your profile image"></div>
        </div>
        <h1 class="image-name"></h1>
        <div>

            <div class="tablist-div" role="tablist">
                <%--<div class="tablist-node-div" aria-selected="true" role="tab" tabindex="0"  data-toggle="tab">--%>
                <%--<div class="favourites-div" style="  top: 25.4875px; left: 60.575px; width: 118px; height: 118px;   "></div>--%>
                <%--<span class="favourites-span">Favourites</span>--%>
                <%--<div class="after-span"></div>--%>
                <%--</div>--%>
                <%--<div class="tablist-node-div" aria-selected="false" role="tab" tabindex="-1" aria-controls="panel-2"--%>
                <%--data-tabid="gl">--%>
                <%--<div class="favourites-div"--%>
                <%--style="top: 29.4875px; left: 42.95px; width: 108px; height: 108px;"></div>--%>
                <%--<span  class="favourites-span">Galleries</span></div>--%>
                <%--<div class="tablist-underline" style="width: 118px; left: 656px;"></div>--%>
                <div class="line_100"></div>
                <ul class="nav nav-tabs ul" role="tablist" id="move_information">

                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#home">个人信息</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu1">艺术品展示</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu2">我的好友</a>
                    </li>

                </ul>
            </div>
        </div>


        <!-- Tab panes -->
        <div class="tab-content">
            <div id="home" class="container tab-pane active"><br>
                <h3>个人信息</h3>
                <div>
                    <ul class="list-group">
                        <li class="list-group-item">用户：<c:out value="${requestScope.user.username} "/></li>
                        <li class="list-group-item">邮箱：<c:out value="${requestScope.user.email}"/></li>
                        <li class="list-group-item">个性签名：<c:out value="${requestScope.user.signature}"/></li>
                    </ul>
                    <br>
                    <c:if test="${requestScope.user.userID == sessionScope.userID}">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#change">修改
                        </button>
                    </c:if>
                </div>
            </div>



            <div id="menu1" class="container tab-pane fade"><br>
            <h3>艺术品展示</h3>
            <c:choose>
            <c:when test="${favoursShow.size()==0}">
                <div class="row border favour-result mx-auto">
                    <span>没有收藏~</span>
                </div>
            </c:when>
            <c:otherwise>
            <c:forEach items="${favoursShow}" var="favourItem">
                <div class="row border favour-result card">
                    <div class="col-2">
                        <p class="type text-muted">Artwork</p>
                        <a href="detailDisplay?paintingID=${favourItem.painting.paintingID}">
                            <img src="博物馆图片资源/其他/${favourItem.painting.imageFileName}" alt="">
                        </a>
                    </div>
                    <div class="col-8 card-body table-responsive">
                        <p class="title">${favourItem.painting.title}</p>
                            <%--<p>heat:${favourItem.msrp}</p>--%>
                        <table class="table table-hover">
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
                </div>
            </c:forEach>
            </c:otherwise>
            </c:choose>
        </div>

    </div>


        <div class="modal fade" id="change">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- 模态框头部 -->
                    <div class="modal-header">
                        <h4 class="modal-title">修改个人信息</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- 模态框主体 -->
                    <div class="modal-body">
                        <div class="border card">
                            <section class="card-body">
                                <form class="form-group" id="form_changeInformation">
                                    <input hidden name="userID" value="<c:out value="${requestScope.user.userID}"/>"/>
                                    <input hidden name="oldName" value="<c:out value="${requestScope.user.username}"/>"/>
                                    <div>
                                        <ul class="list-group">
                                            <li class="list-group-item"><label for="username">用户名：</label><input
                                                    id="username" type="text" name="username" placeholder="<c:out
        value="${requestScope.user.username}"/>" value="<c:out value="${requestScope.user.username}"/>"></li>
                                            <li class="list-group-item"><label for="email">邮箱：</label><input id="email"
                                                                                                             type="email"
                                                                                                             name="email"
                                                                                                             placeholder="<c:out
        value="${requestScope.user.email}"/>" value="<c:out value="${requestScope.user.email}"/>"></li>
                                            <li class="list-group-item"><label for="signature">个性签名：</label><input
                                                    id="signature" type="text" name="signature" placeholder="<c:out
        value="${requestScope.user.signature}"/>" value="<c:out value="${requestScope.user.signature}"/>"></li>
                                            <li class="list-group-item"><label for="password">输入密码：</label><input
                                                    type="password" name="password" id="password"></li>
                                        </ul>
                                    </div>
                                    <br>
                                    <div class="d-flex justify-content-end">

                                        <p class="btn-group">
                                            <button class="btn btn-secondary" type="button" onclick="rechange()">Submit
                                            </button>
                                            <button class="btn btn-secondary" type="reset">Clear</button>
                                        </p>
                                    </div>
                                </form>
                            </section>
                        </div>
                    </div>

                    <!-- 模态框底部 -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    </div>

                </div>
            </div>
        </div>


</main>
</body>
<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>
<script src="js/nav/registe.js"></script>
<script src="js/nav/login.js"></script>
<script src="js/nav/moveline.js"></script>
<script src="js/nav/nav.js"></script>
<script src="js/user-detail/user-detail.js"></script>
</html>
