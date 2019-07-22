
$('#move_information').moveline({
    color: '#4285f4',
    position: 'inner',
    click: function (ret) {
        ret.ele.addClass('active').siblings().removeClass('active');
    }
});

function rechange() {
    var form1 = document.getElementById('form_changeInformation');
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "./userDetail",//url
        data: $(form1).serialize(),
        success: function (result) {
            console.log(JSON.stringify(result));//打印服务端返回的数据(调试用)
            if (result.type === "true") {
                $.simplyToast(result.msg, 'success');
                setTimeout('history.go(0)', 2000);
            } else {
                $.simplyToast(result.msg, 'info');
                // setTimeout('history.go(0)',2000);
            }
        },
        error: function () {
            $.simplyToast("未知错误", 'warning');
            // alert("异常！");
        }
    });

}
