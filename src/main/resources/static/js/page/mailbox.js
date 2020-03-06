to_page();
/**
     * 1.显示所用订单
     * @param
     */
    function to_page() {
        var username = window.localStorage.getItem("username");
        $.ajax({
            url: "http://localhost:8080/getReceiveMsg",
            data: "toUser=" + username,
            type: "GET",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                //alert(result.msg);
                //解析并显示员工数据表
                build_orders_table(result)
            }
        })
    }

    /**
     * 解析并显示员工数据表
     * @param data
     */
    function build_orders_table(result) {
        //清空table表格
        //console.log(result);
        $("#mailbox-tb tbody").empty();
        var orders = result.data.entity;
        $("#mail-num").html(orders.length);
        $("#mail-num2").html(orders.length);
        //遍历元素
        $.each(orders, function (index, item) {
            var content = '<td class="check-mail">\n' +
                '                                <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" class="i-checks" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div>\n' +
                '                            </td>\n' +
                '                            <td class="mail-ontact"><a href="javascript:void(0)" onclick="detail(' + item.id + ')">'+item.fromUser+'</a><span class="label label-warning pull-right">好友分享</span>\n' +
                '                            </td>\n' +
                '                            <td class="mail-subject"><a href="javascript:void(0)" onclick="detail(' + item.id + ')">'+item.content+'</a>\n' +
                '                            </td>\n' +
                '                            <td class=""><i class="fa fa-paperclip"></i>\n' +
                '                            </td>\n' +
                '                            <td class="text-right mail-date">'+item.sendTime+'</td>\n';

            if (item.isRead == 1){
                $("<tr></tr>").addClass("read").append(content).appendTo("#mailbox-tb tbody");
            }else {
                $("<tr></tr>").addClass("unread").append(content).appendTo("#mailbox-tb tbody");
            }

        })
    }


function detail(id) {
    window.localStorage.setItem("detail-id", id);
    window.location.href = "maildetail.html";
}

