window.onload = function() {
    getMsgDetail();
}
/**
     * 1.显示
     * @param
     */
    function getMsgDetail() {
        var id = window.localStorage.getItem("detail-id");
        $.ajax({
            url: "http://localhost:8080/getReceiveMsgDetail",
            data: "id=" + id,
            type: "GET",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                //alert(result.msg);
                //解析并显示员工数据表
                build_page(result);
                setMsgStatus(id);
            }
        })
    }

    /**
     * 解析并显示数据表
     * @param data
     */
    function build_page(result) {
        var detail = result.data.entity;

        $("#fromUser").html(detail.fromUser);
        $("#fromUser2").html(detail.fromUser);
        $("#sendTime").html(detail.sendTime);
        $("#content").html(detail.content);
    }


    function setMsgStatus(id) {
        $.ajax({
            url: "http://localhost:8080/setMsgStatus",
            data: "id=" + id,
            type: "GET",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function () {

            }
        })
    }


