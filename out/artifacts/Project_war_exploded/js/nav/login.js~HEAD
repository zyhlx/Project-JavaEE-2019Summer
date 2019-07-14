

function login() {
    var n = document.getElementById('username').value;
    var p = document.getElementById('password').value;
    var isSuccessful = true;

    // var isOk = false;


    if (n.length <= 0) {
        $.simplyToast("请输入用户名",'warning');
        isSuccessful = false;
    } else if (p.length <= 0) {
        $.simplyToast("请输入密码",'warning');
        isSuccessful = false;
    }
    if(isSuccessful){
        var form1= document.getElementById('form_login');
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "./login" ,//url
            data: $(form1).serialize(),
            success: function (result) {
                console.log(JSON.stringify(result));//打印服务端返回的数据(调试用)
                if (result.type==="true") {
                    $.simplyToast(result.msg,'success');
                    setTimeout('history.go(0)',2000);
                }else {
                    $.simplyToast(result.msg,'info');
                    // setTimeout('history.go(0)',2000);
                }
            },
            error : function() {
                $.simplyToast(result.msg,'warning');
                // alert("异常！");
            }
        });
    }
}

//
// function logout() {
//     var xmlhttp;
//     if (window.XMLHttpRequest) {
//         //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
//         xmlhttp = new XMLHttpRequest();
//     }
//     else {
//         // IE6, IE5 浏览器执行代码
//         xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
//     }
//     xmlhttp.open("GET", "logout.php?action=logout", true);
//     xmlhttp.send();
//
//
// }
