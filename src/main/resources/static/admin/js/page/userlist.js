$(document).ready(function () {
    $.jgrid.defaults.styleUI = 'Bootstrap';
    // Configuration for jqGrid
    $("#table_userlist").jqGrid({
        url: 'http://localhost:8080/getallusers',
        mtype: 'get',
        datatype: "json",
        height: $(window).height() -225,
        autowidth: true,
        shrinkToFit: true,
        rowNum: 10,
        rowList: [10, 20, 30],
        colNames: ['ID', '用户名', '真实姓名', '身份证号码', '电话号码', '年龄'],
        colModel: [
            {
                name: 'personId',
                index: 'personId',
                editable: true,
                width: 90,
                sorttype: "int",
                key: true
            },
            {
                name: 'username',
                index: 'username',
                editable: true,
                width: 90,
            },
            {
                name: 'person.trueName',
                index: 'person.trueName',
                editable: true,
                width: 90,
            },
            {
                name: 'person.idCardNum',
                index: 'person.idCardNum',
                editable: true,
                width: 90,
            },
            {
                name: 'person.phoneNum',
                index: 'person.phoneNum',
                editable: true,
                width: 90,
            },
            {
                name: 'person.age',
                index: 'person.age',
                editable: true,
                width: 90,
            }
        ],
        pager: "#pager_userlist",
        viewrecords: true,
        caption: "",
        add: true,
        edit: true,
        addtext: 'Add',
        edittext: 'Edit',
        hidegrid: false,
        jsonReader : {
            root: "root",
            page: "page",
            total: "total",
            records: "records"
        },
        prmNames : {
            page:"page",
            rows:"pageSize",
            order: "order"
        }
    });

    // Add selection
    $("#table_userlist").setSelection(4, true);


    // Setupbuttons
    $("#table_userlist").jqGrid('navGrid', '#pager_userlist', {
        edit: true,
        add: true,
        del: true,
        search: true
    }, {
        height: 200,
        reloadAfterSubmit: true
    });

    // Add responsive to jqGrid
    $(window).bind('resize', function () {
        var width = $('.jqGrid_wrapper').width();
        $('#table_userlist').setGridWidth(width);
    });


});

