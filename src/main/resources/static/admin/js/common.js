var username = window.localStorage.getItem("adminname");
if (username == null){
    window.location.href = "login.html";
}
