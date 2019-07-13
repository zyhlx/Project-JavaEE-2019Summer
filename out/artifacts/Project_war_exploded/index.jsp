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


    <link rel="stylesheet" type="text/css" href="css/header_line.css">

</head>
<body>
<nav class="navbar nav-pills navbar-light navbar-fix" id="fixed-nar">

    <div class="col-sm-4 navbar-header row" id="nav-header-form">

        <button class="btn mobile-nav-taggle btn-collase" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>

        <a class="navbar-brand nav-item logo" href="#">Art Museum</a>
    </div>
    <div class="col-sm-7 row justify-content-end">
        <ul class="nav nav-pills list-inline-item col-sm-10 justify-content-end " id="move">
            <li class="nav-item list-inline-item">
                <a href="#" class="nav-link">Home</a>
            </li>
            <li>
                <a href="#" class="nav-link">
                    <svg width="24px" height="24px" viewBox="0 0 48 48">
                        <path
                                d="M31 28h-1.59l-.55-.55C30.82 25.18 32 22.23 32 19c0-7.18-5.82-13-13-13S6 11.82 6 19s5.82 13 13 13c3.23 0 6.18-1.18 8.45-3.13l.55.55V31l10 9.98L40.98 38 31 28zm-12 0c-4.97 0-9-4.03-9-9s4.03-9 9-9 9 4.03 9 9-4.03 9-9 9z"></path>
                    </svg>
                </a>
            </li>
        </ul>

    </div>

    <div class="col-sm-1 row" id="user-button-div">

        <button class="btn" type="button" >
            <img src="images/nav/user.PNG">
        </button>

    </div>


</nav>

<nav class="navbar nav-pills navbar-light flex-column" id="mobile-menu" aria-hidden="true">
    <ul class="navbar-nav">
        <li class="nav-item p-2">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item p-2">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item p-2">
            <a class="nav-link" href="#">Link</a>
        </li>
    </ul>
</nav>


<div class="container">
    <h3>折叠导航栏</h3>
    <p>通常，小屏幕上我们都会折叠导航栏，通过点击来显示导航选项。</p>
    <p>提示: 如果你删除 .navbar-expand-md 类，导航链接会一直隐藏，且切换按钮会一直显示。</p>
</div>

<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
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
