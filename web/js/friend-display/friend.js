$(".btn-delete").on("click", function () {
    var userID = $(this).attr("id").substring(7);
    $.post("./userManagement", {
        function: "0",
        userID: userID
    }, function (result) {
        window.location.reload();
        $.simplyToast(result.msg, 'info');
    });
});

$(".btn-change").on("click", function () {
    var userID = $(this).attr("id").substring(7);
    $.post("./userManagement", {
        function: "1",
        userID: userID
    }, function (result) {
        window.location.reload();
        $.simplyToast(result.msg, 'info');
    });
});