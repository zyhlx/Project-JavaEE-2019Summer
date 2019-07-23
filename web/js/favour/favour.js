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