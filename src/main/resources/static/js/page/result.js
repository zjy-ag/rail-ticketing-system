

var queryResult = localStorage.getItem("queryResult")
queryResult = JSON.parse(queryResult);
if (queryResult != ''){
    var showContent = '';
    $("#search_failure").hide();
    for (var i in queryResult){
        var spanDays = queryResult[i].spanDays;
        if (spanDays != 0){
            spanDays = "+" + spanDays;
        }else {
            spanDays = "";
        }
        showContent += '<div class="one_line">\n' +
            '    <div class="col-sm-12">\n' +
            '        <div class="col-sm-3">\n' +
            '            <p class="flight_name">' + queryResult[i].carNum + '</p>\n' +
            '        </div>\n' +
            '        <div class="col-sm-3">\n' +
            '            <p style="padding: 10px">\n' +
            '                <span style="font-size: xx-large">' + queryResult[i].startTime.substring(0, 5) + '</span>\n' +
            '                <br>\n' +
            '                ' + queryResult[i].orginLocation + '\n' +
            '            </p>\n' +
            '        </div>\n' +
            '        <div class="col-sm-3">\n' +
            '            <p style="padding: 10px">\n' +
            '                <span style="font-size: xx-large">' + queryResult[i].reachTime.substring(0, 5) + '</span><span id="spanDays-show" style="color: #f05050">' + spanDays + '</span>\n' +
            '                <br>\n' +
            '                ' + queryResult[i].destinationLocation + '\n' +
            '            </p>\n' +
            '        </div>\n' +
            '        <div class="col-sm-3">\n' +
            '            <p style="padding: 22px 0;">\n' +
            '                                <span style="position:absolute;margin-top: 10px;margin-left: -50px;\n' +
            '                                    font-size:smaller;color: gray;">剩余票数</span>\n' +
            '                <span style="position:absolute;margin-top: 26px;margin-left: -47px;\n' +
            '                                    font-size:smaller;color: gray">' + queryResult[i].ticketNum + '</span>\n' +
            '                <sup>￥</sup>\n' +
            '                <span style="color: #ff6600;font-size: xx-large;">' + queryResult[i].ticketPrice + '</span>\n' +
            '                <sub></sub>&nbsp;\n' +
            '                <button class="btn btn-sm btn-primary via-btn">途经站</button>' +
            '                <button onclick="booking(queryResult[i])"\n' +
            '                   class="btn btn-sm btn-warning">订票</button>\n' +
            '            </p>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>';

    }
    $("#searchResult").text('');
    $("#searchResult").append(showContent);
}else {
    $('#searchResult').hide();
}


$(document).on("click",".via-btn",function () {
    //得到该车次号
    var carNum = $(this).parent().parent().parent().find("p").eq(0).text();
    //alert(carNum);
    $.ajax({
        url:"http://localhost:8080/getAimTripsVia?carNum=" + carNum,
        type:"GET",
        success:function (result) {
            //1.解析返回数据
            var tripsvia = result.data;
            console.log(tripsvia);
            //未查询到数据
            if (tripsvia.length == 0){
                alert("查询数据为空");
            }else{
                //清空table表格
                $("#trips_via_table tbody").empty();
                //遍历加载元素
                $.each(tripsvia, function (index, item) {
                    //alert(index);
                    var stationName = $("<td></td>").append(item.stationName);
                    var reachTimeText = item.reachTime;
                    reachTimeText = reachTimeText.substring(0, reachTimeText.lastIndexOf(':'));
                    if (reachTimeText == "00:00"){
                        reachTimeText = "-----";
                    }
                    var reachTime = $("<td></td>").append(reachTimeText);
                    var startTimeText = item.startTime;
                    startTimeText = startTimeText.substring(0, startTimeText.lastIndexOf(':'));
                    var startTime = $("<td></td>").append(startTimeText);
                    //var orderNum = $("<td></td>").append(item.orderNum);

                    $("<tr></tr>").append(stationName).append(reachTime).append(startTime)
                        .appendTo("#trips_via_table tbody");
                })
                $("#tripViaShowModal").modal({
                    backdrop: "static"
                })
            }
        },
        error:function () {
            alert("请求数据错误！")
        }
    })



});

