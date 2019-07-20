<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2019/7/16
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Museum</title>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
    <%--<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>--%>
    <script src="common/pagination/common/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link href="common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">

    <script src="https://cdn.staticfile.org/angular.js/1.4.6/angular.min.js"></script>

    <link rel="stylesheet" href="css/search/search.css">

    <%--<link href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">--%>
    <%--<link href="http://cdn.staticfile.org/highlight.js/7.3/styles/github.min.css" rel="stylesheet" type="text/css" media="all">--%>

    <style type="text/css">

        main {
            padding-top: 50px;
        }

         img{
             height: 120px;
             width: 120px;
         }
        .btn-add{
            margin-top: 1em;
            margin-left: 10%;
        }
        .user-result{
            margin-top: 2em;
            margin-left: 7%;
            margin-right: 7%;
            padding: 1.5em;
            width: 80%;
        }
        .info-title{
            font-weight: bold;
        }
        .user-name{
            font-size: 20px;
        }

    </style>

</head>
<body>
<%@ include file="common/nav.jsp" %>
<main>

    <header class="z-container page-title--wrapper">


        <h1 class="page-title--search">
            Search
            <span class="page-title__delimiter">/</span>
            <span>Name</span></h1>


        <div class="page-title__input">
            <div>
                <form name="keyword-filter" onsubmit="update()">
                    <fieldset class="filter filter--keyword">
                        <div class="filter__input">
                            <div class="filter__input-wrapper">
                                <input placeholder="Search" type="text" id="search_ipt">
                                <button class="filter__clear-text" type="button" onclick="clear()">
                                    <svg class="icon" viewBox="0 0 16 16">
                                        <path d="M15.997 14.543l-1.454 1.454L8 9.454l-6.543 6.543-1.454-1.454L6.546 8 .003 1.457 1.457.003 8 6.546 14.543.003l1.454 1.454L9.454 8l6.543 6.543z"></path>
                                    </svg>
                                </button>
                            </div>
                            <button class="filter__submit" type="button" onclick="load()" id="search">
                                <svg class="icon" viewBox="0 0 14 14">
                                    <path d="M13.992 7.505l-6.487 6.487-1.497-1.498L10.502 8H0V6h10.503L6.008 1.505 7.505.008l6.487 6.487-.505.505.505.505z"
                                          id="path-1" class="cls-2" fill-rule="evenodd"></path>
                                </svg>
                            </button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </header>

    <section class="main-wrapper wrapper">
        <div class="main-row">
            <div class="content-full">

                <div class="search-results__results-container rich-text z-container">

                    <div class="z-container" id="list-content">


                    </div>
                </div>


            </div>
        </div>
    </section>


</main>
</body>
<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>
<script src="js/nav/registe.js"></script>
<script src="js/nav/login.js"></script>
<script src="js/nav/moveline.js"></script>
<script src="js/nav/nav.js"></script>
<%--<script rel="script" type="text/javascript" src="js/search/search.js"></script>--%>
<script type="text/javascript" src="common/dist/jq-paginator.js"></script>

<script>
    function load() {
        var input_text = $("#search_ipt").val();
        $.ajax({
            //需要提交的服务器地址
            url: "./friendSearch",
            //请求的方式
            type: "post",
            //传递给服务器的参数
            data: {"input_text": input_text},
            //回调函数
            success: function (data) {
                var myData = $.parseJSON(data).users;
                alert(myData);
                //每次清空数据,防止每次点击都叠加
                $("#list-content").html('');
                //追加数据  data.list表示需要遍历的对象  list必须是实体类pageInfo中的list属性名
                $.each(myData, function (i, users) {
                    //一个dom就是一个用户对象
                    // 不是好友
                    if (users.isFriend == 0) {
                        $("#list-content").append("<div class=\"row border user-result\">\n" +
                            "                    <div class=\"col-8\">\n" +
                            "                    <p class=\"userName\">" + users.username + "</p>\n" +
                            "</div>" +
                            "                    <div class=\"col-2\">\n" +
                            "                    <p><button type=\"button\" class=\"btn btn-addFriend\">添加为好友</button></p>\n" +
                            "                    </div>\n" +
                            "                    </div>"
                        );
                    } else {
                        var favourString = "";
                        $.each(users.favours, function (i, favour) {
                            favourString.append(" <a href=\"./detailDisplay?paintingID=" + favour.paintingID + "\">" + favour.title + "</a>");
                        });

                        $("#list-content").append(" <div class=\"row border user-result\">\n" +
                            "        <div class=\"col-2\">\n" +
                            "            <p class=\"type\">" + users.type + "</p>\n" +
                            "            <img src=\"images/user/" + users.type + ".jpg\" alt=\"\">\n" +
                            "        </div>\n" +
                            "        <div class=\"col-8 panel panel-default\">\n" +
                            "            <div class=\"panel-heading user-name\">" + "wcn" + "</div>\n" +
                            "            <table class=\"table\">\n" +
                            "                <tr><td class=\"info-title\">邮箱</td><td>" + users.email + "</td></tr>\n" +
                            "                <tr><td class=\"info-title\">个性签名</td><td>" + users.signature + "</td></tr>\n" +
                            "                <tr><td class=\"info-title\">最近收藏</td><td>" + favourString + "</td>\n" +
                            "                </tr>\n" +
                            "            </table>\n" +
                            "        </div>\n" +
                            "    </div>");
                    }


                });
            }
        });
    }
</script>
</html>
