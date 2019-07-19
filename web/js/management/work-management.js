var hash=window.location.hash;
var hashs=hash.split("#");
var length=hashs.length;
var artworkID=hashs[length-1];

var userName='';
var changed="0";

function toPage() {
    userName=sessionStorage.getItem("userName");
    if(userName==null){
        document.getElementById("background").style.display="none";
        document.getElementById("checkSubmit").innerHTML='<p>请先登录</p>';
    }
    else{
        document.getElementById("background").style.display="inline";
        showReferrer();
        document.getElementById("checkSubmit").innerHTML='';
    }

    hash=window.location.hash;
     hashs=hash.split("#");
     length=hashs.length;
     artworkID=hashs[length-1];
    var xmlhttp;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlhttp = new XMLHttpRequest();
    }
    else {
        // IE6, IE5 浏览器执行代码
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function () {
        //alert(xmlhttp.readyState);
        //alert(xmlhttp.status);
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            //alert('yes');
            document.getElementById("getForm").innerHTML=xmlhttp.responseText;
        }
    };
    var url="getForm.php?artworkID="+artworkID;
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

}

function previewPic() {
    var file=document.getElementById("file").files[0];
    changed = "1";
    if(!/image\/\w+/.test(file.type)){
        $.simplyToast("请输入图片",'warning');
        //alert("看清楚，这个需要图片！");
        return false;
    }

    var reader = new FileReader();
    //将文件以Data URL形式读入页面
    reader.readAsDataURL(file);
    reader.onloadend=function(){
        //var result=document.getElementById("result");
        var showPic=document.getElementById("showFile");
        srcOfPic=reader.result;
        showPic.innerHTML='<img src="'+srcOfPic+'">';

    }
}

var reg1 = /^-?\d+$/; //整数
var reg2=/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/ //正数
var reg3=/^[+]{0,1}(\d+)$/ //正整数


function check() {
    // var year=document.getElementById("year").value;
    // var width=document.getElementById("width").value;
    // var height=document.getElementById("height").value;
    // var price=document.getElementById("price").value;
    if(checkPrice()&&checkHeight()&&checkWidth()&&checkYear()){
        return ture;
    }
    else{
        return false;
    }
}

function checkYear() {
    var year=document.getElementById("year").value;

    //var wrongYear=document.getElementById("wrongYear");

    if(!reg1.test(year)){
        document.getElementById("wrongYear").innerHTML='年份错误！请输入整数';
        //alert('no');
        return false;

    }
    else{
        document.getElementById("wrongYear").innerHTML='';
        //alert('yes')
        return true;
    }
}

function checkWidth() {
    var width=document.getElementById("width").value;
    var wrongWidth=document.getElementById("wrongWidth");
    if(!reg2.test(width)){
        wrongWidth.innerText='宽度错误!请输入正数';
        return false;
    }
    else{
        wrongWidth.innerText='';
        return true;
    }
}

function checkHeight() {
    var height=document.getElementById("height").value;
    var wrongHeight=document.getElementById("wrongHeight");
    if(!reg2.test(height)){
        wrongHeight.innerText='宽度错误!请输入正数';
        return false;
    }
    else{
        wrongHeight.innerText='';
        return true;
    }
}

function checkPrice() {
    var price=document.getElementById("price").value;
    var wrongPrice=document.getElementById("wrongPrice");
    if(!reg3.test(price)){
        wrongPrice.innerText='宽度错误!请输入正数';
        return false;
    }
    else{
        wrongPrice.innerText='';
        return true;
    }
}



function submitTheForm() {
    var takeform = document.getElementById('changeForm');
    // userName=sessionStorage.getItem("userName");
    // form.append('userName',userName);
    // form.append('artworkID',artworkID);
    $.ajax({
        url: "./workManagement",
        type: "POST",
        data: $(takeform).serialize(),
        processData: false,
        contentType: false,
        success: function (data) {
            //document.getElementById("checkSubmit").innerHTML='修改成功！';
            //window.location.href='localhost/pj2/upload.html';
        },
        error: function (e) {
            //console.log(e);
            //window.clearInterval(timer);
        }
    });
}

$("#btn-change").on("click", function() {
    var form = new FormData($("#changeForm")[0]);
    var artworkID = "601";
    form.append("artworkID", artworkID);

    $.ajax({
        url: "./upload",
        type: "POST",
        data: form,
        processData: false,
        contentType: false,
        success: function (data) {
            alert("成功")
            //window.location.href='localhost/pj2/upload.html';
        },
        error: function (e) {
            alert(e);
            window.clearInterval(timer);
        }
    });
});