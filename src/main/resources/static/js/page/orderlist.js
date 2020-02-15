    var totalRecord,currentPage,totalPage;
    //1.显示所有数据
    to_page(1);

    /**
     * 1.显示所用订单
     * @param pn
     */
    function to_page(pn) {
        var username = window.localStorage.getItem("username");
        $.ajax({
            url: "http://localhost:8080/getorder",
            data: "username=" + username + "&pn=" + pn,
            type: "GET",
            dataType: "json",
            contentType: "application/json;charset=UTF-8",
            success: function (result) {
                //alert(result.msg);
                //解析并显示员工数据表
                build_orders_table(result)

                //2.解析并显示分页信息
                build_page_info(result);

                //3.解析并显示分页条数据
                build_page_nav(result);
            }
        })
    }

    /**
     * 解析并显示员工数据表
     * @param data
     */
    function build_orders_table(result) {
        //清空table表格
        $("#orders_table tbody").empty();
        var orders = result.data.list;
        //遍历元素
        $.each(orders, function (index, item) {
            //var id = $("<td></td>").append(item.id);
            var carNum = $("<td></td>").append(item.carNum);
            var orginLocation = $("<td></td>").append(item.orginLocation);
            var destinationLocation = $("<td></td>").append(item.destinationLocation);
            var startTime = $("<td></td>").append(item.startTime);
            var reachTime = $("<td></td>").append(item.reachTime);
            var status = "<button class='btn-danger'>订单无效</button>";
            if (item.status == 1){
                status = "<button class='btn-warning' onclick='gotopay("+ item.id + ")'>等待支付</button>";
            }else if (item.status == 2){
                status = "<button class='btn-success'>已支付</button>";
            }else if (item.status == 3){
                status = "<button class='btn-default'>已退票</button>";
            }
            var status = $("<td></td>").append(status);
            var ticketPrice = $("<td></td>").append(item.ticketPrice);
            var button1 = $("<button></button>").addClass("btn btn-sm btn-danger").attr("onclick", "refund(" +item.id + ")").append("退票");
            var td_btn = $("<td></td>").append(button1);
            $("<tr></tr>").append(carNum).append(orginLocation).append(destinationLocation).append(startTime).append(reachTime).append(ticketPrice).append(status)
                .append(td_btn).appendTo("#orders_table tbody");

        })
    }

    /**
     * 解析显示分页信息
     * @param data
     */
    function build_page_info(result) {
        $("#page_info_area").empty();
        $("#page_info_area").append("当前" + result.data.pageNum + "页,总共" + result.data.pages +
            "页，总共" + result.data.total + "条记录");
        totalRecord = result.data.total;
        currentPage=result.data.pageNum;
        totalPage = result.data.pages;
    }

    /**
     * 解析显示分页导航条
     * @param data
     */
    function build_page_nav(result) {
        //console.log(result);
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href", "#"));
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href", "#"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
        //如果没有前一页，前一页和首页就不能点
        if (result.data.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            //首页
            firstPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(result.data.pageNum - 1);
            });
        }
        if (result.data.hasNextPage == false) {
            nextPageLi.addClass("disabled");
            lastPageLi.addClass("disabled");
        } else {
            //构建点击事件
            nextPageLi.click(function () {
                to_page(result.data.pageNum + 1);
            });
            lastPageLi.click(function () {
                to_page(result.data.lastPage);
            })
        }
        //添加首页和前一页
        ul.append(firstPageLi).append(prePageLi);
        //遍历添加页码
        $.each(result.data.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item).attr("href", "#"));
            //如果是当前选中页面，添加active标识
            if (result.data.pageNum == item) {
                numLi.addClass("active");
            }
            //给每个页码添加点击就跳转
            numLi.click(function () {
                to_page(item);
            });
            ul.append(numLi);
        });
        //添加下一页和末页
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }



    /**
     * 退票
     * @param flight_id
     */
    function refund(id) {
        var choice = confirm("尊敬的顾客，您确定要退票吗");
        if (choice == true) {
            var id = id;
            var status = 3;
            $.ajax({
                url:"http://localhost:8080/updateorder",
                type:"POST",
                data:JSON.stringify({id:id,status:status}),
                dataType:"json",
                contentType:"application/json;charset=UTF-8",
                success:function () {
                    //1.关闭modal框
                    $("#orderReviseModal").modal('hide');
                    //2.来到当前页，显示刚才保存的数据
                    to_page(currentPage);

                }
            })
        }
    }

/**
 * 订单页转支付页面
 * @param id
 */
function gotopay(id) {
    window.localStorage.setItem("curOrderId", id)
    if (confirm("现在去支付？")){
        window.location.href = "pay.html";
    };
}

