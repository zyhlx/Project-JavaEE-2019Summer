<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Painting" %>
<%@ page import="db.DBUtil" %><%--
  Created by IntelliJ IDEA.
  bean.User: christine
  Date: 2019/7/14
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <link href="common/simply-toast/simply-toast.css" rel="stylesheet" type="text/css">

    <link href="common/modal.css" rel="stylesheet"><!--bootstrap自带问题-->
    <link rel="stylesheet" type="text/css" href="css/nav/header_line.css">
    <link href="common/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <style>
        img{
            max-height: 150px;
            width: auto;
        }
        #content{
            margin-top: 5em;
            margin-left: 7%;
            margin-right: 7%;
            width: 86%;
        }
        td{
            width: 20%;
        }
        .favour-result{
            padding: 1em;
            margin-top: 1.5em;
        }

    </style>
</head>
<body>
<%@ include file="common/nav.jsp"%>

<section id="content">
    <c:forEach items="${favours}" var="favourItem">
        <div class="row border favour-result">
            <div class="col-2">
                <p class="type text-muted">Artwork</p>
                <a href="./detailDisplay?paintingID=${favourItem.painting.paintingID}">
                <img src="博物馆图片资源/其他/${favourItem.painting.imageFileName}" alt="">
                </a>
            </div>
            <div class="col-8">
                <p class="title">${favourItem.painting.title}</p>
                <%--<p>heat:${favourItem.msrp}</p>--%>
                <table>
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
            <div class="col-2">
                <p><button type="button" class="btn btn-delete" id="btn-delete-${favourItem.favourID}"> 删除</button> </p>
                <p><button type="button" class=" btn btn-change" id="btn-change-${favourItem.favourID}">
                <c:if test="${favourItem.open == 0}">
                    设为公开
                </c:if>
                    <c:if test="${favourItem.open != 0}">
                        设为私密
                    </c:if>
                </button></p>
            </div>
        </div>
            </c:forEach>

</section>


</body>

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="js/session.js"></script>
<script>

   $(".btn-change").on("click",function () {
       var favourID = $(this).attr("id").substring(11);
       $.post("./favourManage",{
           function: "1",
           favourID: favourID
       },function (result) {
           window.location.reload();
           $.simplyToast(result.msg, 'info');
       });
   });

   $(".btn-delete").on("click",function () {
       var favourID = $(this).attr("id").substring(11);
       $.post("./favourManage",{
           function: "0",
           favourID: favourID
       },function (result) {
           window.location.reload();
           $.simplyToast(result.msg, 'info');
       });
   })
</script>



<script src="http://www.daiwei.org/global/js/jquery.easing.js"></script>
<script src="http://www.daiwei.org/components/toast/js/toast.js"></script>
<script src="common/simply-toast/simply-toast.js"></script>
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
