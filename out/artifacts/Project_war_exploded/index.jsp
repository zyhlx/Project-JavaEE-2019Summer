<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/7/10
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <link href="common/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/header_line.css">

</head>
<body>
<%@ include file="nav.jsp"%>

<div class="container">
    <h3>折叠导航栏</h3>
    <p>通常，小屏幕上我们都会折叠导航栏，通过点击来显示导航选项。</p>
    <p>提示: 如果你删除 .navbar-expand-md 类，导航链接会一直隐藏，且切换按钮会一直显示。</p>
</div>




<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast.js"></script>
<script src="common/registe.js"></script>
<script src="common/login.js"></script>
<script src="js/moveline.js"></script>
<script>
    $('#move').moveline({
        color: '#1a73e8',
        position: 'inner',
        click: function (ret) {
            ret.ele.addClass('active').siblings().removeClass('active');
        }
    });
</script>
<script>
    $(".mobile-nav-taggle").click(function () {
        var mobileMenu = $("#mobile-menu");
        if (mobileMenu.hasClass("show-nav")) {
            setTimeout(function () {
                mobileMenu.addClass("hide-nav").removeClass("show-nav");
            }, 100)
        }
        else {
            setTimeout(function () {
                mobileMenu.addClass("show-nav").removeClass("hide-nav");
            }, 100)
        }
    });
</script>


</body>
</html>
