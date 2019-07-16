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
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

    <link href="common/simply-toast.css" rel="stylesheet" type="text/css">
    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">

    <script src="https://cdn.staticfile.org/angular.js/1.4.6/angular.min.js"></script>

    <link rel="stylesheet" href="css/search/search.css">


<style type="text/css" >

    main{
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
            <span class="page-title__delimiter">/</span>
            <span>Title</span></h1>


        <div class="page-title__input">
            <div>
                <form name="keyword-filter" onsubmit="update()">
                <fieldset class="filter filter--keyword">
                    <div class="filter__input">
                        <div class="filter__input-wrapper">
                            <input placeholder="Search" type="text">
                            <button class="filter__clear-text" type="button" onclick="clear()">
                                <svg class="icon" viewBox="0 0 16 16">
                                    <path d="M15.997 14.543l-1.454 1.454L8 9.454l-6.543 6.543-1.454-1.454L6.546 8 .003 1.457 1.457.003 8 6.546 14.543.003l1.454 1.454L9.454 8l6.543 6.543z"></path></svg>
                            </button>
                        </div>
                        <button class="filter__submit" type="submit">
                            <svg class="icon" viewBox="0 0 14 14">
                                <path d="M13.992 7.505l-6.487 6.487-1.497-1.498L10.502 8H0V6h10.503L6.008 1.505 7.505.008l6.487 6.487-.505.505.505.505z" id="path-1" class="cls-2" fill-rule="evenodd"></path></svg>
                        </button>
                    </div>
                </fieldset>
            </form>
            </div>
        </div>
    </header>









</main>
<script rel="script" type="text/javascript" src="js/search/search.js"></script>
</body>
</html>