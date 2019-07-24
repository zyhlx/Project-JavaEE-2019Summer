var videoCheck = 1;
var picCheck = 1;


function previewVideo() {
    var file=document.getElementById("video").files[0];
    changed = "1";
    if(!/video\/\w+/.test(file.type)){
        $.simplyToast("请输入视频",'warning');
        videoCheck = 0;
        //alert("看清楚，这个需要图片！");
        return false;
    }
    videoCheck = 1;
    var reader = new FileReader();
    //将文件以Data URL形式读入页面
    reader.readAsDataURL(file);
    reader.onloadend=function(){
        //var result=document.getElementById("result");
        var showVideo=document.getElementById("showVideo");
        var srcOfVideo = reader.result;
        var display = " <video width=\"320\" height=\"240\" controls=\"controls\">" +
            "<source src=\"" + srcOfVideo + "\" type=\"video/mp4\" />" +
            "<source src=\"" + srcOfVideo +"\" type=\"video/ogg\" />" +
            "<source src=\"" + srcOfVideo +"\" type=\"video/webm\" />" +
            "<object data=\"" + srcOfVideo + "\"  width=\"320\" height=\"240\">" +
            "<embed src=\"" + srcOfVideo + "\" width=\"320\" height=\"240\" />" +
            "</object>" +
            "</video>";
        showVideo.innerHTML = display;

    }
}

function previewPic() {
    var file=document.getElementById("file").files[0];
    changed = "1";
    if(!/image\/\w+/.test(file.type)){
        $.simplyToast("请输入图片",'warning');
        //alert("看清楚，这个需要图片！");
        picCheck = 0;
        return false;
    }
    picCheck = 1;
    var reader = new FileReader();
    //将文件以Data URL形式读入页面
    reader.readAsDataURL(file);
    reader.onloadend=function(){
        //var result=document.getElementById("result");
        var showPic = document.getElementById("showFile");
        var srcOfPic = reader.result;
        showPic.innerHTML='<img src="'+srcOfPic+'">';

    }
}
$("#btn-change").on("click", function() {
    var file=document.getElementById("video").files[0];
    var pic=document.getElementById("file").files[0];
    if ($("#title").val() === "" || $("#description").val() === "" || $("#place").val() === "" || $("#year").val() === "") {
        $.simplyToast("请输入完整信息！");
        return;
    }
    else if (videoCheck === 0 && file != null) {
        $.simplyToast("请输入正确的视频！");
        return;
    }
    else if (picCheck === 0 && pic !=null) {
        $.simplyToast("请输入正确的照片！");
        return;
    }
    var form = new FormData(document.getElementById("form-change"));
    $.ajax({
        url: "./upload.work",
        type: "POST",
        data: form,
        dataType: "json",
        processData: false,
        contentType: false,
        success: function (data) {
            if (data.type === "true") {
                $.simplyToast("上传成功", 'info');
            } else {
                $.simplyToast("上传失败，可能由于重复的名称/图片", 'warning');
            }

            //window.location.href='localhost/pj2/upload.html';
        },
        error: function (e) {
            alert(e);
            window.clearInterval(timer);
        }
    });


});

$("#btn-add").on("click", function () {
    location.href = "./work.display?artworkID=0";
});

$(".btn-delete").on("click", function () {
    //如果已登录，收藏
    var artworkID = $(this).attr("id").substring(4);
    $.post("./delete.work", {
        artworkID: artworkID
    }, function (result) {
        $.simplyToast(result.msg, 'info');
    });

});
