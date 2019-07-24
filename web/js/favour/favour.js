// $(".btn-change").on("click",function () {
//     var favourID = $(this).attr("id").substring(11);
//     $.post("./change.favour",{
//         favourID: favourID
//     },function (result) {
//         $.simplyToast(result.msg, 'info');
//         setTimeout('history.go(0)',2000);
//     });
// });
//
// $(".btn-delete").on("click",function () {
//     var favourID = $(this).attr("id").substring(11);
//     $.post("./delete.favour",{
//         favourID: favourID
//     },function (result) {
//         $.simplyToast(result.msg, 'info');
//         setTimeout('history.go(0)',2000);
//     });
// });