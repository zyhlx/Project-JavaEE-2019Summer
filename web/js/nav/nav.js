document.write("<script type='text/javascript' src='moveline.js'></script>");
document.write("<script type='text/javascript' src='login.js'></script>");
document.write("<script type='text/javascript' src='registe.js'></script>");
function downShadow() {
    //获取#middle的位置
    var startPos = $("main").offset().top
    $.event.add(window, "scroll", function () {
        var p = $(window).scrollTop();
        $("#fixed-nar").css('box-shadow', ((p) > startPos) ? '0 0px 5px #888888' : '0 0 0 #888');
    });
}
$(document).ready(downShadow());


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


$('#move').moveline({
    color: '#1a73e8',
    position: 'inner',
    click: function (ret) {
        ret.ele.addClass('active').siblings().removeClass('active');
    }
});