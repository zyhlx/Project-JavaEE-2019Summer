<%--
  Created by IntelliJ IDEA.
  User: christine
  Date: 2019/7/15
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="common/nav.jsp"%>
<section>
    <div class="row" id="upper-part">

        <div class="col-4" id="description-container">
            <h3><b>Shoes</b>,1888
            </h3>
            <p>van Gogh</p>
            <p>Van Gogh painted several still lifes of shoes or boots during his Paris period. This picture, painted later, in Arles, evinces a unique return to the earlier motif. However, here Van Gogh has placed the shoes within a specific spatial context: namely, the red-tile floor of the Yellow House. Not only may we identify the setting, but perhaps the owner of the shoes as well. It has been suggested that this "still life of old peasants' shoes" may have been those of Patience Escalier, whose portrait Van Gogh executed around the same time, late summer 1888.</p>

        </div>
        <div class="col-7 bg-light text-dark" id="picture-container">
            <img src="博物馆图片资源/其他/001080.jpg" alt="" id="picture" >
            <div id="icons">
                <button type="button" class="btn btn-light text-left">
                    <i class="fa fa-heart-o" id="icon-favour"></i>
                </button>
                <button type="button" class="btn btn-light text-left" >
                    <i class="fa fa-folder-open"></i>
                </button>

            </div>

        </div>

    </div>

    <HR>

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

</section>

</body>

<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
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

<%--<div class="container">--%>
    <%--<h2>堆叠表单</h2>--%>
    <%--<form>--%>
        <%--<div class="form-group">--%>
            <%--<label for="email">Email:</label>--%>
            <%--<input type="email" class="form-control" id="email" placeholder="Enter email">--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<label for="pwd">Password:</label>--%>
            <%--<input type="password" class="form-control" id="pwd" placeholder="Enter password">--%>
        <%--</div>--%>
        <%--<div class="form-check">--%>
            <%--<label class="form-check-label">--%>
                <%--<input class="form-check-input" type="checkbox"> Remember me--%>
            <%--</label>--%>
        <%--</div>--%>
        <%--<button type="submit" class="btn btn-primary">Submit</button>--%>
    <%--</form>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>
