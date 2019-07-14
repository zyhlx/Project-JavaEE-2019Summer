<%--
  Created by IntelliJ IDEA.
  User: christine
  Date: 2019/7/12
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>



    <style>

        #picture{
            height: 500px;
            margin-left: 7%;
        }
        .large-pic{

            max-height: 90%;
        }
        .icon{
            height: 20px;
            width: auto;
        }

    </style>
    <style>
        #detail-panel{
            padding-left: 20%;
            padding-right: 20%;
            text-align: center;
        }
        .detail-title{
            font-weight: bold;
        }
    </style>

    <link href="common/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
</head>
<body>
<%@ include file="nav.jsp"%>
<div class="row">
    <div class="col-2"></div>
    <div class="col-4">
        <h3><b>Shoes</b>,1888
        </h3>
        <p>van Gogh</p>
        <p>Van Gogh painted several still lifes of shoes or boots during his Paris period. This picture, painted later, in Arles, evinces a unique return to the earlier motif. However, here Van Gogh has placed the shoes within a specific spatial context: namely, the red-tile floor of the Yellow House. Not only may we identify the setting, but perhaps the owner of the shoes as well. It has been suggested that this "still life of old peasants' shoes" may have been those of Patience Escalier, whose portrait Van Gogh executed around the same time, late summer 1888.</p>

    </div>
    <div class="col-4 bg-light text-dark" id="picture">
        <img src="博物馆图片资源/其他/001050.jpg" alt="" class="large-pic">
        <div>
            <button type="button" class="btn btn-outline-light">
                <img src="博物馆图片资源/网站/收藏.png" class="icon" id="icon-favour">
            </button>
            <button type="button" class="btn btn-outline-light" >
                <img src="博物馆图片资源/网站/管理.png" class="icon">
            </button>
            <p id="info"></p>
        </div>

    </div>

</div>

<div class="panel panel-default" id="detail-panel">
    <div class="panel-heading">Object Details</div>
    <table class="table">
        <tr>
            <td class="detail-title">Artist:</td>
            <td>Vincent van Gogh (Dutch, Zundert 1853–1890 Auvers-sur-Oise)
            </td>
        </tr>
        <tr>
            <td class="detail-title">Date:</td><td>1888</td></tr>
    </table>
</div>

</body>
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="js/session.js"></script>
<script>

    $("#icon-favour").on("click",function () {
        //如果已登录，收藏
        // $.session.set('user','master');

        var userName ="<%=session.getAttribute("user")%> ";
            // $.session.get('user');
        if(userName!="null") {
            $.post("./favour",{
                userName: userName,
                artworkID: 5
            });
        }
        else {
            $("#info").text( "please log in first!");
        }
    });
</script>



<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast.js"></script>
<script src="js/nav/registe.js"></script>
<script src="js/nav/login.js"></script>
<script src="js/nav/moveline.js"></script>
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

</html>