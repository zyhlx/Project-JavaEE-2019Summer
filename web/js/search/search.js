charset="utf-8";

function clear() {

}


load(1);

function load(pageNum) {
    if (pageNum === "") {
        pageNum = 1;
    }
    var input_text = $('#search_ipt').val();
    var method = "";
    $("[name='facets']:checked").each(function(){
        method+=$(this).val()+"&";
    });
    $.ajax({
        //需要提交的服务器地址
        url: "./page",
        //请求的方式
        type: "post",
        //传递给服务器的参数
        data: {"pageNum": pageNum - 1,"method":method,"input_text":input_text},
        //回调函数
        success: function (data) {
            var data = $.parseJSON(data).page;
            //每次清空数据,防止每次点击都叠加
            $("#list-content").html('');
            //追加数据  data.list表示需要遍历的对象  list必须是实体类pageInfo中的list属性名
            $.each(data.list, function (i, news) {
                //一个dom就是一个用户对象
                $("#list-content").append("  <div class=\"result-card__container\">\n" +
                    "                                <a href=\"detailDisplay?paintingID=" + news.paintingID + "\" class=\"result-card__link ng-scope\">\n" +
                    "                                    <div class=\"result-card z-container__cssgrid\">\n" +
                    "                                        <div class=\"result-card__left-container\">\n" +
                    "                                            <div class=\"result-card__image-wrapper\">\n" +
                    "                                                <h2 class=\"result-card__category alt ng-binding ng-scope\">\n" +
                    "                                                    MuseumCollects</h2>\n" +
                    "                                                <img class=\"result-card__image\"\n" +
                    "                                                     src=\"/博物馆图片资源/其他/" + news.imageFileName + ".jpg\"\n" +
                    "\n" +
                    "                                                     onerror=\"this.src='/images/assert/icon/ico-no-image.svg'\">\n" +
                    "                                            </div>\n" +
                    "                                        </div>\n" +
                    "                                        <div class=\"result-card__main-content\">\n" +
                    "                                            <div class=\"result-card__content-wrapper\">\n" +
                    "                                                <h2 class=\"alt ng-binding\">" + news.title + "</h2>\n" +
                    "                                                <div class=\"result-card__body text-leave\">\n热度："+news.msrp +"<br>馆藏："+news.gallery+"<br>"+ news.description +
                    "                                                </div>" +
                    "                                            </div>\n" +
                    "                                        </div>\n" +
                    "                                    </div>\n" +
                    "                                </a>\n" +
                    "                            </div>");
            });
            $("#current_page").html(pageNum);
            $("#total_page").html(Math.ceil(data.total/data.pageSize));

            $('.M-box3').jqPaginator({
                totalCounts: data.total+1,
                visiblePages: 10,
                pageSize: data.pageSize,
                currentPage: pageNum,
                onPageChange: function (num, type) {
                    // var input_text = $('#search_ipt').val();
                    // var method = "";
                    // $("[name='facets'][checked]").each(function(){
                    //     method+=$(this).val()+"&";
                    // });
                    $.ajax({
                        //需要提交的服务器地址
                        url: "./page",
                        //请求的方式
                        type: "post",
                        //传递给服务器的参数
                        data: {"pageNum": num - 1,"method":method,"input_text":input_text},
                        //回调函数
                        success: function (data) {
                            var data = $.parseJSON(data).page;
                            //每次清空数据,防止每次点击都叠加
                            $("#list-content").html('');
                            //追加数据  data.list表示需要遍历的对象  list必须是实体类pageInfo中的list属性名
                            $("#current_page").html(num);
                            $("#total_page").html(Math.ceil(data.total/data.pageSize));
                            $.each(data.list, function (i, news) {
                                //一个dom就是一个用户对象
                                $("#list-content").append("  <div class=\"result-card__container\">\n" +
                                    "                                <a href=\"detailDisplay?paintingID=" + news.paintingID + "\" class=\"result-card__link ng-scope\">\n" +
                                    "                                    <div class=\"result-card z-container__cssgrid\">\n" +
                                    "                                        <div class=\"result-card__left-container\">\n" +
                                    "                                            <div class=\"result-card__image-wrapper\">\n" +
                                    "                                                <h2 class=\"result-card__category alt ng-binding ng-scope\">\n" +
                                    "                                                    MuseumCollects</h2>\n" +
                                    "                                                <img class=\"result-card__image\"\n" +
                                    "                                                     src=\"/博物馆图片资源/其他/" + news.imageFileName + ".jpg\"\n" +
                                    "\n" +
                                    "                                                     onerror=\"this.src='/images/assert/icon/ico-no-image.svg'\">\n" +
                                    "                                            </div>\n" +
                                    "                                        </div>\n" +
                                    "                                        <div class=\"result-card__main-content\">\n" +
                                    "                                            <div class=\"result-card__content-wrapper\">\n" +
                                    "                                                <h2 class=\"alt ng-binding\">" + news.title + "</h2>\n" +
                                    "                                                <div class=\"result-card__body text-leave\">\n热度："+news.msrp +"<br>馆藏："+news.gallery+"<br>"+ news.description +
                                    "                                                </div>" +
                                    "                                            </div>\n" +
                                    "                                        </div>\n" +
                                    "                                    </div>\n" +
                                    "                                </a>\n" +
                                    "                            </div>");
                            });

                        }
                    })
                }
            });

        }
    });
}