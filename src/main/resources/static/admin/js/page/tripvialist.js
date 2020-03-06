$(function () {
    var totalRecord,currentPage,totalPage;
    //1.显示所有数据
    to_page();
    //2.添加用户
    addTripVia();
    //3.修改用户
    reviseTripVia();
    //4.单个删除用户
    deleteTripVia();

    /**
     * 1.显示所用用户
     * @param pn
     */
    function to_page() {
        var storage=window.localStorage;
        var carNum = storage.getItem("viaCarNum");

        $.ajax({
            url: "http://localhost:8080/getAimTripsVia",
            data:"carNum="+carNum,
            type: "GET",
            dataType: "json",
            contentType:"application/json;charset=UTF-8",
            success: function (result) {
                //alert(result.msg);
                //解析并显示员工数据表
                console.log(result);
                build_trips_via_table(result)
            }
        })
    }

    /**
     * 解析并显示员工数据表
     * @param data
     */
    function build_trips_via_table(result) {
        //清空table表格
        $("#trips_via_table tbody").empty();
        var tripsVia = result.data;

        //遍历元素
        $.each(tripsVia, function (index, item) {
            var checkBox=$("<td><input type='checkbox' class='check_item'/></td>");
            var carNum = $("<td></td>").addClass("hidden").append(item.carNum);
            var id = $("<td></td>").append(item.id);
            var stationName = $("<td></td>").append(item.stationName);
            var reachTime = $("<td></td>").append(item.reachTime);
            var startTime = $("<td></td>").append(item.startTime);
            var orderNum = $("<td></td>").append(item.orderNum);

            //查看途经站信息
            var button1 = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn").append($("<span></span>").addClass("glyphicon glyphicon-pencil").attr("aria-hidden", true)).append("编辑");
            var button2 = $("<button></button>").addClass("tn btn-danger btn-sm delete_btn").append($("<span></span>").addClass("glyphicon glyphicon-trash").attr("aria-hidden", true)).append("删除");
            var td_btn = $("<td></td>").append(button1).append(" ").append(button2);
            $("<tr></tr>").append(checkBox).append(id).append(stationName).append(reachTime).append(startTime).append(orderNum).append(carNum)
                .append(td_btn ).appendTo("#trips_via_table tbody");

        })
    }



    /**
     * 2.实现新增功能
     * @returns {boolean}
     */
    function addTripVia() {

        //为新增按钮添加modal
        $("#trip_via_add_modal_btn").click(function () {
            //清除表单数据
            $("#tripViaAddModal form")[0].reset();
            var storage=window.localStorage;
            var carNum = storage.getItem("viaCarNum");
            //将id的值传递给修改按钮的属性，方便发送Ajax请求
            $("#trip_via_save_btn").attr("save-id",carNum);
            $("#tripViaAddModal").modal({
                backdrop: "static"
            })
        });

        /**
         * 保存车次信息
         */
        $("#trip_via_save_btn").click(function () {
            var carNum = $(this).attr("save-id");
            var stationName = $("#stationName_add_input").val();
            var reachTime =$("#reachTime_add_input").val();
            var startTime = $("#startTime_add_input").val();
            var orderNum =$("#orderNum_add_input").val();

            //2.发送ajax请求保存车次
            $.ajax({
                url: "http://localhost:8080/saveTripVia",
                type: "POST",
                data: JSON.stringify({carNum:carNum,stationName:stationName,reachTime:reachTime, startTime:startTime,orderNum:orderNum}),
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success: function (result) {
                    if (result.code == 200 && result.data.message == "success"){
                        //1.关闭modal框
                        $("#tripViaAddModal").modal('hide');
                        to_page();
                    }else {
                        //1.关闭modal框
                        $("#tripViaAddModal").modal('hide');
                        alert(result.data.message);
                    }
                }
            });
        });
    }

    /**
     * 3.修改用户
     */
    function reviseTripVia() {
        //为编辑按钮绑定弹出modal框事件
        //1.因为在按钮创建之前就绑定了click，所以用普通click方法绑定不上

        $(document).on("click",".edit_btn",function () {
            //清除表单数据
            $("#tripViaReviseModal form")[0].reset();

            var id= $(this).parent().parent().children("td").eq(1).text();
            //将id的值传递给修改按钮的属性，方便发送Ajax请求
            $("#trip_via_revise_btn").attr("edit-id",id);
            var stationName=$(this).parent().parent().children("td").eq(2).text();
            var reachTime=$(this).parent().parent().children("td").eq(3).text();
            var startTime=$(this).parent().parent().children("td").eq(4).text();
            var orderNum=$(this).parent().parent().children("td").eq(5).text();

            $("#stationName_revise_input").val(stationName);
            $("#reachTime_revise_input").val(reachTime);
            $("#startTime_revise_input").val(startTime);
            $("#orderNum_revise_input").val(orderNum);
            $("#tripViaReviseModal").modal({
                backdrop: "static"
            })
        });
        //2.为模态框中的修改按钮绑定事件，更新员工信息
        $("#trip_via_revise_btn").click(function () {

            //2.验证通过后发送ajax请求保存更新的员工数据
            //如果要直接发送PUT之类的请求
            //在WEB.xml配置HttpPutFormContentFilter过滤器即可
            //这里未使用如上所述方法
            //获取编辑后的
            var id = $(this).attr("edit-id");
            var stationName = $("#stationName_revise_input").val();
            var reachTime =$("#reachTime_revise_input").val();
            var startTime = $("#startTime_revise_input").val();
            var orderNum = parseInt($("#orderNum_revise_input").val());
            $.ajax({
                url:"http://localhost:8080/updateTripVia",
                type:"POST",
                data:JSON.stringify({id:id,stationName:stationName,startTime:startTime,reachTime:reachTime,orderNum:orderNum}),
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success:function () {
                    //1.关闭modal框
                    $("#tripViaReviseModal").modal('hide');
                    //2.来到当前页，显示刚才保存的数据
                    to_page();
                }
            })

        })
    }

    /**
     * 4.删除用户
     */
    function deleteTripVia() {
        $(document).on("click",".delete_btn",function () {
            //1.弹出确认删除对话框
            var stationName=$(this).parents("tr").find("td:eq(2)").text();
            var id=$(this).parents("tr").find("td:eq(1)").text();
            if(confirm("确认删除途经站点："+stationName+"吗？")){
                // alert(id);
                //确认，发送ajax请求删除
                $.ajax({
                    url:"http://localhost:8080/deleteTripVia/"+id,
                    type:"DELETE",
                    success:function (result) {
                        if (result.code == 200 && result.data.message == "success"){
                            alert("删除成功");
                        }
                        to_page();
                    }
                })
            }
        })
    }


});
