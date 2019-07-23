
function writeLetter() {
    if ($('#receiver').val().length > 0 && $('#content').val().length > 0) {
        var a = $('#userHiddenID');
        $.ajax({
            url: "/letter",
            type: "post",
            dataType: "json",//预期服务器返回的数据类型
            data: $('#write-form').serialize(),
            success: function (data) {
                if (data.msg == "发送成功") {
                    $.simplyToast("发送成功", 'success');
                    $(location).attr('href', '/letter?userID='+a.val());
                } else if (data.msg == "查无此人") {
                    $.simplyToast("查无此人", 'warning')
                }
            },
            error: function (e) {
                $.simplyToast('未知错误', 'danger')
            }
        });
    } else {
        $.simplyToast("请先填写完整", 'info')
    }

}

function resee(a) {
    $.ajax({
        url: "letter?isOne=yes&isW=y&letterID=" + a,
        type: "get",

        success: function (data) {
            var data1 = $.parseJSON(data).letter;
            var time = $.parseJSON(data).sendTime;
            document.getElementById('sender').value = data1.senderName;
            document.getElementById('rece').value = data1.receiveName;
            document.getElementById('cont').value = data1.contents;
            document.getElementById('tim').value =time;
        },
        error: function (e) {
            $.simplyToast('未知错误', 'danger')
        }
    });
}



function receiveLetter(a) {
    $.ajax({
        url:  "letter?isOne=yes&letterID=" + a,
        type: "get",
        // data: $('#receive-form').serialize(),
        success: function (data) {
            var data1 = $.parseJSON(data).letter;
            var time = $.parseJSON(data).sendTime;
            document.getElementById('sender').value = data1.senderName;
            document.getElementById('rece').value = data1.receiveName;
            document.getElementById('cont').value = data1.contents;
            document.getElementById('tim').value = time;
            var letterstatus = "status"+a;
            document.getElementById(letterstatus).innerText='接收状态：已阅;';
        },
        error: function (e) {
            $.simplyToast('未知错误', 'danger')
        }
    });
}

function acceptRequest(patronID, clientID) {
    $.ajax({
        url:  "./friendManage?function=2&patronID="+patronID+"&clientID="+clientID,
        type: "post",
        // data: $('#receive-form').serialize(),
        success: function (data) {
            $.simplyToast(data.msg,'info')
        },
        error: function (e) {
            $.simplyToast('未知错误', 'danger')
        }
    });
}
