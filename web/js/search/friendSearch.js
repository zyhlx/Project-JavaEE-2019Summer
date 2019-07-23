function load_friend() {
    var input_text = $("#search_input").val();
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
            //每次清空数据,防止每次点击都叠加
            $("#list-content").html('');
            //追加数据  data.list表示需要遍历的对象  list必须是实体类pageInfo中的list属性名
            $.each(myData, function (i, users) {
                //一个dom就是一个用户对象
                // 不是好友
                if (users.isFriend == 0) {
                    $("#list-content").append("<div class=\"row border user-result\">\n" +
                        "                    <div class=\"col-10\">\n" +
                        "                <a href='./userDetail?userID=" + users.userID + "'>   <p class=\"userName\">" + users.username + "</p></a>\n" +
                        "</div>" +
                        "                    <div class=\"col-2\">\n" +
                        "                    <p><button type=\"button\" class=\"btn btn-addFriend\" id=\"btn-add-" + users.userID + "\"'>添加为好友</button></p>\n" +
                        "                    </div>\n" +
                        "                    </div>"
                    );
                } else {
                    var favourString = "";
                    var myFavour = users.favours;
                    $.each(myFavour, function (i,favours) {
                        favourString = favourString + " <a href=\"./detailDisplay?paintingID=" + favours.painting.paintingID + "\">" + favours.painting.title + "</a>";
                    });


                    $("#list-content").append(" <div class=\"row border user-result\">\n" +
                        "        <div class=\"col-2\">\n" +
                        "            <p class=\"type\">" + users.type + "</p>\n" +
                        "            <img src=\"images/user/" + users.type + ".jpg\" alt=\"\">\n" +
                        "        </div>\n" +
                        "        <div class=\"col-8 panel panel-default\">\n" +
                        "            <div class=\"panel-heading user-name\">" + users.username + "</div>\n" +
                        "            <table class=\"table\">\n" +
                        "                <tr><td class=\"info-title\">邮箱</td><td>" + users.email + "</td></tr>\n" +
                        "                <tr><td class=\"info-title\">个性签名</td><td>" + users.signature + "</td></tr>\n" +
                        "                <tr><td class=\"info-title\">最近收藏</td><td>" + favourString + "</td>\n" +
                        "                </tr>\n" +
                        "            </table>\n" +
                        "        </div>\n" +
                        "<div class=\"col-2\">" +
                        "<p><button type=\"button\" class=\"btn btn-delete\" id='btn-delete-" + users.userID + "'>删除好友</button> </p>"+
                        "    </div>" +
                        "</div>");
                }


            });
        }
    });
}

$("body").on('click',".btn-addFriend",function (ev) {
    var clientID = $(this).attr("id").substring(8);
    $.post("./friendManage", {
        function: "1",
        clientID: clientID
    }, function (result) {
        $.simplyToast(result.msg, 'info');
    });
});



$("body").on('click','.btn-delete',function() {
    var clientID = $(this).attr("id").substring(11);
    $.post("./friendManage",{
        function: "0",
        clientID:clientID
    },function (result) {
        $.simplyToast(result.msg, 'info');
    });
});


