$(".btn-delete").on("click", function () {
    var userID = $(this).attr("id").substring(7);
    $.post("./userManagement",{
        function: "0",
        userID: userID
    },function (result) {
        window.location.reload();
        $.simplyToast(result.msg, 'info');
    });
});

$(".btn-change").on("click", function () {
    var userID = $(this).attr("id").substring(7);
    $.post("./userManagement",{
        function: "1",
        userID: userID
    },function (result) {
        window.location.reload();
        $.simplyToast(result.msg, 'info');
    });
});


function addUser() {
    var form1= document.getElementById('form-add');
    if (document.getElementById('username-add').value === "") {
        $.simplyToast("用户名不能为空",'warning');
        return false;
    }
    else if (document.getElementById('password-add').value === "") {
        $.simplyToast("密码不能为空",'warning');
        return false;
    }
    else {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "./userManagement",//url
            data: $(form1).serialize(),
            success: function (result) {

                if (result.type === "true") {
                    $.simplyToast(result.msg, 'success');
                    setTimeout('history.go(0)', 2000);


                } else {
                    $.simplyToast(result.msg, 'info');
                    setTimeout('history.go(0)', 2000);
                    // setTimeout('history.go(0)',2000);
                }
            },
            error: function () {
                $.simplyToast("未知错误", 'warning');
                // alert("异常！");
            }
        });
    }

}

// $(".btn-add-user").on("click", function () {
//     var form1= document.getElementById('form-add');
//     if ($("#username-add").val() == null) {
//         $.simplyToast("用户名不能为空",'warning');
//         return;
//     }
//     if ($("#password-add").val() == null) {
//         $.simplyToast("密码不能为空",'warning');
//     }
//     $.ajax({
//         //几个参数需要注意一下
//         type: "POST",//方法类型
//         dataType: "json",//预期服务器返回的数据类型
//         url: "./userManagement" ,//url
//         data: $(form1).serialize(),
//         success: function (result) {
//
//             if (result.type==="true") {
//                 $.simplyToast(result.msg,'success');
//                 window.location.reload();
//
//
//             }else {
//                 $.simplyToast(result.msg,'info');
//                 setTimeout('history.go(0)',2000);
//                 // setTimeout('history.go(0)',2000);
//             }
//         },
//         error : function() {
//             $.simplyToast("未知错误",'warning');
//             // alert("异常！");
//         }
//     });
// });