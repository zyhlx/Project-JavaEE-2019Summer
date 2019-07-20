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
<%@ include file="common/nav.jsp" %>
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
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore
                    et dolore magna aliqua.</p>
            </div>
            <div id="menu1" class="container tab-pane fade"><br>
                <h3>艺术品展示</h3>
                <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                    consequat.</p>
            </div>
            <div id="menu2" class="container tab-pane fade"><br>
                <h3>好友列表</h3>
                <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium,
                    totam rem aperiam.</p>
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

<script>
    $('#move_information').moveline({
        color: '#4285f4',
        position: 'inner',
        click: function (ret) {
            ret.ele.addClass('active').siblings().removeClass('active');
        }
    });
</script>
</html>
