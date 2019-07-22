<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/7/21
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Letter</title>
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
<%@ include file="common/nav.jsp" %>
<main>
    <div class="detail-container">

        <div class="container-set">
            <div class="user-image" title="Your profile image"></div>
        </div>
        <h1 class="image-name"></h1>
        <div>

            <div class="tablist-div" role="tablist">

                <div class="line_100"></div>
                <ul class="nav nav-tabs ul" role="tablist" id="move_information">

                    <li class="nav-item">
                        <a class="nav-link active" data-toggle="tab" href="#home">我收到的信</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" data-toggle="tab" href="#menu1">我发出的信</a>
                    </li>
                </ul>
            </div>
        </div>


        <!-- Tab panes -->
        <div class="tab-content">
            <div id="home" class="container tab-pane active"><br>
                <h3>收信箱</h3>
                <div>
                    <ul class="list-group">
                        <c:choose>
                        <c:when test="${requestScope.receiveLetters.size()==0 && requestScope.friendRequests.size() == 0}">
                        <li class="list-group-item"><span>您还没有收到信息呦~</span>
                            </c:when>
                            <c:otherwise>
                            <c:forEach items="${requestScope.receiveLetters}" var="receiveLetter"
                                       varStatus="letterStatus">
                        <li class="list-group-item row">
                            <span class='col-sm-1'>发件人：<c:out value="${receiveLetter.receiveName}"/>;</span>
                            <span class='col-sm-1'>发送时间：<c:out value="${receiveLetter.timeReleased}"/>;</span>
                            <c:if test="${receiveLetter.status==0}">
                                echo "<span class='col-sm-1' id='status<c:out value="${receiveLetter.letterID}"/>'>接收状态：未查收;</span>"
                            </c:if>
                            <c:if test="${receiveLetter.status==1}">
                                <span class='col-sm-1'
                                      id='status<c:out value="${receiveLetter.letterID}"/>'>接收状态：已阅;</span>
                            </c:if>
                            <span class='col-sm-7 oneLeave'><c:out value="${receiveLetter.contents}"/></span>
                            <span class='col-sm-1'>
        <button type="button" class="btn btn-primary" data-toggle=\"modal\" data-target=\"#receive\"
                onclick="receiveLetter(<c:out value="${receiveLetter.letterID}"/>)">点击阅读</button>
    </span>
                        </li>

                    </c:forEach>
                                <c:forEach items="${requestScope.friendRequests}" var="friendRequestItem">
                                <li class="list-group-item row">
                                    <span class='col-sm-1'>${friendRequestItem.patron.username}请求添加您为好友</span>

                                    <span class='col-sm-1'>
        <button type="button" class="btn btn-primary"
                onclick="acceptRequest(${friendRequestItem.patronID}, ${friendRequestItem.clientID})">接受</button>
    </span>
                                </li>


                    </c:forEach>
                    </c:otherwise>
                    </c:choose>
                    </ul>
                </div>
            </div>

        <div id="menu1" class="container tab-pane fade"><br>
                <div class="d-flex justify-content-between"><h3>发信箱</h3>
                    <a href="" data-toggle="modal" data-target="#write" class="btn btn-primary m-1">发信</a></div>
                <div>
                    <ul class="list-group">

                        <c:choose>
                            <c:when test="${requestScope.sendLetters.size()==0}">
                                <li class="list-group-item">
                                    <span>您还没有发过信息呦~</span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${requestScope.sendLetters}" var="sendLetter"
                                           varStatus="letterStatus">
                                    <li class="list-group-item row">
                                        <span class='col-sm-1'>收件人：<c:out value="${sendLetter.receiveName}"/>;</span>
                                        <span class='col-sm-1'>发送时间：<c:out value="${sendLetter.timeReleased}"/>;</span>
                                        <c:if test="${sendLetter.status==0}">
                                            <span class='col-sm-1'>接收状态：未查收;</span>
                                        </c:if>
                                        <c:if test="${sendLetter.status==1}">
                                            <span class='col-sm-1'>接收状态：已阅;</span>
                                        </c:if>
                                        <span class='col-sm-7 oneLeave'><c:out value="${sendLetter.contents}"/></span>
                                        <span class='col-sm-1'>
                                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#receive"
                                        onclick="resee(<c:out value="${sendLetter.letterID}"/>)">查看详情</button></span>
                                    </li>
                                </c:forEach> </c:otherwise>
                        </c:choose>


                    </ul>
                </div>
            </div>



        <div class="modal fade" id="receive">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- 模态框头部 -->
                    <div class="modal-header">
                        <h4 class="modal-title">信件</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- 模态框主体 -->
                    <div class="modal-body">
                        <div class="border card">
                            <section class="card-body">
                                <form method="get" class="form-group" id="receive-form">
                                    <div class="row col-sm-12"><label for="sender">发信人：</label>
                                        <input class="form-control" name="sender" id="sender" readonly="readonly"></div>
                                    <div class="row col-sm-12"><label for="rece">收信人：</label>
                                        <input class="form-control" name="receiver" id="rece" readonly="readonly"></div>
                                    <div class="row col-sm-12"><label for="tim">发送时间：</label>
                                        <input class="form-control" name="time" id="tim" readonly="readonly">
                                    </div>
                                    <div class="row col-sm-12">
                                        <label for="cont">内容：</label>
                                        <textarea rows="20" cols="60" class="form-control" name="content" id="cont"
                                                  readonly="readonly"></textarea>
                                    </div>
                                    <br>
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


        <div class="modal fade" id="write">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- 模态框头部 -->
                    <div class="modal-header">
                        <h4 class="modal-title">写信</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- 模态框主体 -->
                    <div class="modal-body">
                        <div class="border card">
                            <section class="card-body">
                                <form method="post" class="form-group" id="write-form">
                                    <input hidden name="userID" value="<c:out value="${sessionScope.userID}"/>"/>
                                    <div class="row col-sm-12"><label for="receiver">收信人：</label>
                                        <input class="form-control" name="receiver" id="receiver"></div>
                                    <div class="row col-sm-12">
                                        <label for="content">内容：</label>
                                        <textarea rows="20" cols="60" class="form-control" name="content"
                                                  id="content"></textarea>
                                    </div>
                                    <br>
                                    <div class="d-flex justify-content-end">

                                        <p class="btn-group">
                                            <button class="btn btn-secondary" type="button" onclick="writeLetter()">
                                                Submit
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

<script src="js/letter/letter.js"></script>

</html>
