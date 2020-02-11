package com.code.rts.controller;


import com.alibaba.fastjson.JSONObject;
import com.code.rts.Result.Result;
import com.code.rts.entity.*;
import com.code.rts.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * login function
 *
 */
@RestController
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody JSONObject jsonObject){
        Result result = null;
        String username = jsonObject.getString("username");
        String trueName = jsonObject.getString("trueName");
        String idCardNum = jsonObject.getString("idCardNum");
        String phoneNum = jsonObject.getString("phoneNum");
        Integer age = jsonObject.getInteger("age");
        Person person = new Person(trueName, idCardNum, phoneNum, age);
        result = userService.updateUserInfo(username,person);
        return result;
    }

    @PostMapping("/getpersoninfo")
    public Result getPersonInfo(@RequestBody JSONObject jsonObject){
        return userService.getPersonInfo(jsonObject.getString("username"));
    }

    @GetMapping("/getallusers")
    public Map<String, Object> getAllUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize){
        int totalCount = userService.getUsersCount();
        int totalPage = (int) Math.ceil(1.0*totalCount/pageSize);
        PageHelper.startPage(page,pageSize);
        Page<User> users = userService.getAllUsers();



        Map<String, Object> modelMap = new HashMap<>();
        if (users != null){
            modelMap.put("root", users);
            modelMap.put("page", page);
            modelMap.put("total", totalPage);
            modelMap.put("records", totalCount);
       }else {
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "获取model列表失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }


}
