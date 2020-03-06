package com.code.rts.controller;

import com.alibaba.fastjson.JSONObject;
import com.code.rts.Result.Result;
import com.code.rts.entity.Admin;
import com.code.rts.entity.User;
import com.code.rts.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * login
     * @param jsonObject
     * @return
     */
    @PostMapping("/login")
    @GetMapping("/login")
    @ResponseBody
    public Result login(@RequestBody JSONObject jsonObject){
        User user = new User();
        String password = jsonObject.getString("password");
        String username = jsonObject.getString("username");
        user.setPassword(password);
        user.setUsername(username);
        Result result = loginService.loginIn(user);
        return result;
    }

    /**
     * login
     * @param jsonObject
     * @return
     */
    @PostMapping("/loginAdmin")
    @GetMapping("/loginAdmin")
    @ResponseBody
    public Result loginAdmin(@RequestBody JSONObject jsonObject){
        Admin admin = new Admin();
        String password = jsonObject.getString("password");
        String username = jsonObject.getString("username");
        admin.setPassword(password);
        admin.setUsername(username);
        Result result = loginService.loginAdminIn(admin);
        return result;
    }

    /**
     * update user's password
     * @param
     * @return
     */
    @PostMapping("/updatePassword")
    public Result updateUser(@RequestBody JSONObject jsonObject){
        User userData = new User();
        userData.setUsername(jsonObject.getString("username"));
        userData.setPassword(jsonObject.getString("passwordOld"));
        String newPassword = jsonObject.getString("passwordNew");
        Result result  = loginService.updateUser(userData, newPassword);
        return result;
    }

    /**
     * user regist
     * @param userData
     * @return
     */
    @PostMapping("/regist")
    public Result registUser(@RequestBody User userData){
        return loginService.registUser(userData);
    }
}
