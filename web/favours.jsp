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
            margin-left: 15%;
            margin-right: 15%;
            width: 70%;
        }
        td{
            width: 20%;
        }
        .favour-result{
            margin-top: 1.5em;
        }

    </style>
</head>
<body>
<%@ include file="common/nav.jsp"%>

<section id="content">
    <%
        session.setAttribute("user","master");
        String userName = (String)session.getAttribute("user");
        int userID = 0;
        // 查找userID
        if (userName != null) {
            userID = dao.factory.DAOFactory.getIUserDAOInstance().getUserID(userName);
        }
        System.out.println(userID);
        // 查找 artworkID
        List<Integer> artworkIDs = null;
        if (userID != 0) {
            String searchForArtworkID = "SELECT * FROM  favours where userID=" + "'" + userID + "'";
            artworkIDs = db.DBUtil.getID(searchForArtworkID,"artworkID");
        }
        // 打印图片
        for (int id: artworkIDs) {
            String searchForPainting =  "SELECT * FROM  paintings where paintingID=" + "'" + id + "'";
            Painting temp = dao.factory.DAOFactory.getIPaintingDAOInstance().getPaintings(searchForPainting).get(0);
            out.print("<div class=\"row border favour-result\">\n" +
                    "        <div class=\"col-4\">\n" +
                    "            <p class=\"type text-muted\">Artwork</p>\n" +
            "<img src=\"博物馆图片资源/其他/" + temp.getImageFileName()  + "\"" + " alt=\"\">" +
            "</div>\n" +
                    "        <div class=\"col-8\">\n" +
                    "            <p class=\"title\">" + temp.getTitle() + "</p>" +
            "<p>heat</p>\n" +
                    "            <table>\n" +
                    "                <tr>\n" +
                    "                    <td>Favoured date</td>\n" +
                    "                    <td>Medium</td>\n" +
                    "                    <td>On view</td>\n" +
                    "                </tr>\n" +
                    "                <tr>\n" +
                    "                    <td>1888</td>\n" +
                    "                    <td>Oil on canvas</td>\n" +
                    "                </tr>\n" +
                    "            </table>\n" +
                    "\n" +
                    "        </div>\n" +
                    "    </div>");
        }

    %>

</section>


</body>

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="js/session.js"></script>
<script>

    $("#icon-favour").on("click",function () {
        //如果已登录，收藏
        //$.session.set('user','master');
        var userName = "<%=session.getAttribute("user")%>";
        if(userName!=null) {
            $.post("./favour",{
                userName: userName,
                artworkID: 5
            }, function (result) {
                $.simplyToast(result, 'info');
            });
        }
        else {
            $("#info").text( "please log in first!");
        }
    });
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
