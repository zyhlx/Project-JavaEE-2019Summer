$(".btn-favour").on("click", function () {
    //如果已登录，收藏
    var userName = $("#userID").val();
    var artworkID = $(this).attr("id").substring(11);
    if (userName !== "null") {
        $.post("./add.favour", {
            userName: userName,
            artworkID: artworkID
        }, function (result) {
            $.simplyToast(result.msg, 'info');
        });
    }
    else {
        $.simplyToast("请先登录", 'warning');
    }
});

$(".btn-manage").on("click", function () {
    var artworkID = $(this).attr("id").substring(11);
    location.href = "./work.display?artworkID="+artworkID;
});
