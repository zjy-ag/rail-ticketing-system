function loginSubmit(){
    var username = $("#username").val();
    var password = $("#password").val();
    //判断
    if(username.length === 0){
        alert("你输入的用户名为空，请重新输入")
        return;
    }
    if(password.length == 0){
        alert("你输入的密码为空，请重新输入")
        return;
    }
    var json= {"username": username ,"password": password};
    //ajax提交数据
    $.ajax({
        type:"post",
        url: 'http://localhost:8080/loginAdmin',
        data:JSON.stringify(json),
        contentType:'application/json;charset=utf-8',
        dataType:'json',
        success: function(data){
            if (data.stateCode === 200){
                localStorage.setItem("adminname",data.data.username);//此处存的为json String格式的username
                localStorage.setItem("adminid",data.data.id);//此处存的为json String格式的userid
                window.location.href = 'index.html';
            }
            else{
                //异常处理页面
                console.log("loginSubmit函数提交的json信息处理后异常，alert显示错误信息。");//控制台输出信息
                alert("错误信息：" + data.msg);
            }
        }
    })
    return false;
}