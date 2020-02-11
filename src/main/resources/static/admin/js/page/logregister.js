$("#submitLogForm").submit(function(){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/v1/savelogsource",
        data: $("#submitLogForm").serialize(),
        success: function (response) {
            if (response.code == 200 && response.data.message == 'success') {
                $('#loadText').text('提交成功');
                alert("提交成功");
                window.location.href = "submit_task.html";
            }else{
                alert("提交失败，失败原因：" +  response.data.message);
            }
        },
        error:function(){
            alert("提交失败");
        },
        complete:function(){
            //当请求完成之后调用这个函数，无论成功失败，传入XMLHttpRequest对象，以及一个包含成功或者错误代码的字符串
        }
    });
    return false;
});