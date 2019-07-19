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


    <script src="https://cdn.staticfile.org/angular.js/1.4.6/angular.min.js"></script>

    <link rel="stylesheet" href="css/search/search.css">
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <%--<link href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="all">--%>
    <%--<link href="http://cdn.staticfile.org/highlight.js/7.3/styles/github.min.css" rel="stylesheet" type="text/css" media="all">--%>

    <style type="text/css">

        main {
            padding-top: 50px;
        }


    </style>


</head>
<body>
<%@ include file="common/nav.jsp" %>
<main>

    <header class="z-container page-title--wrapper">


        <h1 class="page-title--search">
            Search
            <%--<span class="page-title__delimiter">/</span>--%>
            <%--<span>Title</span>--%>
        </h1>


        <div class="page-title__input">
            <div>
                <form name="keyword-filter">
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
                            <button class="filter__submit" type="button" onclick="load(1)" id="search">
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
                <div class="filter__container z-container">
                    <div>
                        <div class="filter__group">
                            <%--<div class="filter">--%>
                                <%--<input type="checkbox" id="all_results" checked--%>
                                       <%--class="visuallyhidden filter__checkbox " name="facets" value="All Results">--%>
                                <%--<label onclick="document.getElementById('all_results').click()"--%>
                                       <%--class="filter__label filter__checkedbox">--%>
                                    <%--All Results--%>
                                <%--</label>--%>
                                <%--&lt;%&ndash;<span class="filter--radio-facet-count ng-binding ng-scope">510803</span>&ndash;%&gt;--%>
                            <%--</div>--%>
                            <div class="filter">
                                <input type="checkbox" id="title_search" checked="checked"
                                       class="visuallyhidden filter__checkbox " name="facets" value="Title">
                                <label onclick="document.getElementById('title_search').click()"
                                       class="filter__label filter__checkedbox">
                                    Title
                                </label>
                                <%--<span class="filter--radio-facet-count ng-binding ng-scope">510803</span>--%>
                            </div>

                            <div class="filter">
                                <input type="checkbox" id="gallery_search" checked=""
                                       class="visuallyhidden filter__checkbox " name="facets" value="Gallery">
                                <label onclick="document.getElementById('gallery_search').click()"
                                       class="filter__label filter__checkedbox">
                                    Gallery
                                </label>
                                <%--<span class="filter--radio-facet-count ng-binding ng-scope">510803</span>--%>
                            </div>

                            <div class="filter">
                                <input type="checkbox" id="description_search" checked=""
                                       class="visuallyhidden filter__checkbox " name="facets" value="Description">
                                <label onclick="document.getElementById('description_search').click()"
                                       class="filter__label filter__checkedbox">
                                    Description
                                </label>
                                <%--<span class="filter--radio-facet-count ng-binding ng-scope">510803</span>--%>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="mx-auto">
                    <ul class="pagination M-box3"></ul>
                </div>
                <div class="search-results__results-container rich-text z-container">
                    <div class="z-container" id="list-content">

                        <%--<c:forEach items="${search_results}" var="painting" begin="0" end="0"> <c:out value="${painting.imageFileName}"/> <c:out value="${painting.title}"/> <c:out
                                                        value="${painting.decription}"/>--%>
                        <%--<div class="result-card__container">--%>
                        <%--<a href="#" class="result-card__link ng-scope">--%>
                        <%--<div class="result-card z-container__cssgrid">--%>
                        <%--<div class="result-card__left-container">--%>
                        <%--<div class="result-card__image-wrapper">--%>
                        <%--<h2 class="result-card__category alt ng-binding ng-scope">--%>
                        <%--MuseumCollects</h2>--%>
                        <%--<img class="result-card__image"--%>
                        <%--src="/博物馆图片资源/其他/001020.jpg"--%>

                        <%--onerror="this.src='/images/assert/icon/ico-no-image.svg'">--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="result-card__main-content">--%>
                        <%--<div class="result-card__content-wrapper">--%>
                        <%--<h2 class="alt ng-binding">Portrait of Gertrude Stein</h2>--%>
                        <%--<div class="result-card__body text-leave">--%>
                        <%--The terracotta shades and heavy monumentality of the figures in <em>Two Nudes</em> derive from Picasso's interest at the time in the ancient Iberian sculpture of his native Spain. Like many artists in the first decades of the twentieth century, Picasso found ancient and non-western art to be fruitful alternatives both to the prescribed forms of academic painting and the visual culture of industrial modernity. These two women are nearly mirror images, but the face of the figure on the left bears a strong resemblance to that of the figure on the far left in <em>Les Demoiselles d'Avignon</em>. Like the woman in Demoiselles, with whom she shares her chiseled nose and dark, hollow eyes, this woman holds open what appears to be a curtain and gazes outward, as though beckoning viewers in.--%>
                        <%--</div><!-- end ngIf: searchResult.teaser -->--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                        <%--</a>--%>
                        <%--</div>--%>
                        <%--</c:forEach>--%>
                    </div>
                </div>

                <%--<div id="list-content">--%>
                <%--</div>--%>

                <div class="mx-auto">
                    <ul class="pagination M-box3"></ul>


                </div>
                <div class="mx-auto row">
                    <input type="text" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')" oninput="if(value>64)value=64"
                           class="jump-ipt" id="jump-ipt">
                    <a href="javascript:" class="jump-btn" onclick="load(parseInt(document.getElementById('jump-ipt').value),'','')">跳转</a>
                    <span id="current_page"></span>/<span id="total_page"></span>
                </div>
            </div>
        </div>
    </section>


</main>
<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>
<script src="js/nav/registe.js"></script>
<script src="js/nav/login.js"></script>
<script src="js/nav/moveline.js"></script>
<script src="js/nav/nav.js"></script>
<script rel="script" type="text/javascript" src="js/search/search.js"></script>
<script type="text/javascript" src="common/dist/jq-paginator.js"></script>
<script>
    load(1);

    function load(pageNum) {
        if (pageNum === "") {
            pageNum = 1;
        }
        var input_text = $('#search_ipt').val();
        var method = "";
        $("[name='facets']:checked").each(function(){
            method+=$(this).val()+"&";
        });
        $.ajax({
            //需要提交的服务器地址
            url: "./page",
            //请求的方式
            type: "post",
            //传递给服务器的参数
            data: {"pageNum": pageNum - 1,"method":method,"input_text":input_text},
            //回调函数
            success: function (data) {
                var data = $.parseJSON(data).page;
                //每次清空数据,防止每次点击都叠加
                $("#list-content").html('');
                //追加数据  data.list表示需要遍历的对象  list必须是实体类pageInfo中的list属性名
                $.each(data.list, function (i, news) {
                    //一个dom就是一个用户对象
                    $("#list-content").append("  <div class=\"result-card__container\">\n" +
                        "                                <a href=\"detail.jsp?paintingID=" + news.paintingID + "\" class=\"result-card__link ng-scope\">\n" +
                        "                                    <div class=\"result-card z-container__cssgrid\">\n" +
                        "                                        <div class=\"result-card__left-container\">\n" +
                        "                                            <div class=\"result-card__image-wrapper\">\n" +
                        "                                                <h2 class=\"result-card__category alt ng-binding ng-scope\">\n" +
                        "                                                    MuseumCollects</h2>\n" +
                        "                                                <img class=\"result-card__image\"\n" +
                        "                                                     src=\"/博物馆图片资源/其他/" + news.imageFileName + ".jpg\"\n" +
                        "\n" +
                        "                                                     onerror=\"this.src='/images/assert/icon/ico-no-image.svg'\">\n" +
                        "                                            </div>\n" +
                        "                                        </div>\n" +
                        "                                        <div class=\"result-card__main-content\">\n" +
                        "                                            <div class=\"result-card__content-wrapper\">\n" +
                        "                                                <h2 class=\"alt ng-binding\">" + news.title + "</h2>\n" +
                        "                                                <div class=\"result-card__body text-leave\">\n热度："+news.msrp +"<br>"+ news.description +
                        "                                                </div>" +
                        "                                            </div>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                </a>\n" +
                        "                            </div>");
                });
                $("#current_page").html(pageNum);
                $("#total_page").html(Math.ceil(data.total/data.pageSize));

                $('.M-box3').jqPaginator({
                    totalCounts: data.total+1,
                    visiblePages: 10,
                    pageSize: data.pageSize,
                    currentPage: pageNum,
                    onPageChange: function (num, type) {
                        // var input_text = $('#search_ipt').val();
                        // var method = "";
                        // $("[name='facets'][checked]").each(function(){
                        //     method+=$(this).val()+"&";
                        // });
                        $.ajax({
                            //需要提交的服务器地址
                            url: "./page",
                            //请求的方式
                            type: "post",
                            //传递给服务器的参数
                            data: {"pageNum": num - 1,"method":method,"input_text":input_text},
                            //回调函数
                            success: function (data) {
                                var data = $.parseJSON(data).page;
                                //每次清空数据,防止每次点击都叠加
                                $("#list-content").html('');
                                //追加数据  data.list表示需要遍历的对象  list必须是实体类pageInfo中的list属性名
                                $("#current_page").html(num);
                                $("#total_page").html(Math.ceil(data.total/data.pageSize));
                                $.each(data.list, function (i, news) {
                                    //一个dom就是一个用户对象
                                    $("#list-content").append("  <div class=\"result-card__container\">\n" +
                                        "                                <a href=\"./detailDisplay?paintingID=" + news.paintingID + "\" class=\"result-card__link ng-scope\">\n" +
                                        "                                    <div class=\"result-card z-container__cssgrid\">\n" +
                                        "                                        <div class=\"result-card__left-container\">\n" +
                                        "                                            <div class=\"result-card__image-wrapper\">\n" +
                                        "                                                <h2 class=\"result-card__category alt ng-binding ng-scope\">\n" +
                                        "                                                    MuseumCollects</h2>\n" +
                                        "                                                <img class=\"result-card__image\"\n" +
                                        "                                                     src=\"/博物馆图片资源/其他/" + news.imageFileName + ".jpg\"\n" +
                                        "\n" +
                                        "                                                     onerror=\"this.src='/images/assert/icon/ico-no-image.svg'\">\n" +
                                        "                                            </div>\n" +
                                        "                                        </div>\n" +
                                        "                                        <div class=\"result-card__main-content\">\n" +
                                        "                                            <div class=\"result-card__content-wrapper\">\n" +
                                        "                                                <h2 class=\"alt ng-binding\">" + news.title + "</h2>\n" +
                                        "                                                <div class=\"result-card__body text-leave\">\n热度："+news.msrp +"<br>馆藏："+news.gallery+"<br>"+ news.description +
                                        "                                                </div>" +
                                        "                                            </div>\n" +
                                        "                                        </div>\n" +
                                        "                                    </div>\n" +
                                        "                                </a>\n" +
                                        "                            </div>");
                                });

                            }
                        })
                    }
                });

            }
        });
    }
</script>
</body>
</html>