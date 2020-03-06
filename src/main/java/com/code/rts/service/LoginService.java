package com.code.rts.service;

import com.code.rts.Result.Result;
import com.code.rts.dao.*;
import com.code.rts.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    private UserDao userDao;

    @Resource
    private AdminDao adminDao;


    public Result loginIn(User userData) {
        Result result = new Result();
        User user = userDao.getUserByUsername(userData.getUsername());

        if (user == null){
            result.setData(null);
            result.setMsg("用户名/密码错误");
            result.setStateCode(404);
            return result;
        }
        if (!user.getPassword().equals(userData.getPassword())){
            result.setMsg("用户名/密码错误");
            result.setStateCode(404);

            return result;
        }
        result.setStateCode(200);
        result.setMsg("success");
        result.setData(user);
        return result;
    }

    public Result loginAdminIn(Admin adminData) {
        Result result = new Result();
        Admin admin = adminDao.getAdminByUsername(adminData.getUsername());

        if (admin == null){
            result.setData(null);
            result.setMsg("用户名/密码错误");
            result.setStateCode(404);
            return result;
        }
        if (!admin.getPassword().equals(admin.getPassword())){
            result.setMsg("用户名/密码错误");
            result.setStateCode(404);

            return result;
        }
        result.setStateCode(200);
        result.setMsg("success");
        result.setData(admin);
        return result;
    }

    @Transactional
    public Result updateUser(User userData, String newPassword) {
        Result result = new Result();
        User user = userDao.getUserByUsername(userData.getUsername());
        if (user != null){
            String oldPassword = user.getPassword();
            if (userData.getPassword().equals(oldPassword)){
                userData.setPassword(newPassword);
                if (userDao.updateUserRegisterInfo(userData) == 1){
                    result.setMsg("密码修改成功");
                    result.setStateCode(200);
                    result.setData(true);
                }else {
                    result.setData(false);
                    result.setStateCode(400);
                    result.setMsg("密码修改失败，请重新操作");
                }
            } else {
                result.setData(false);
                result.setStateCode(200);
                result.setMsg("修改密码失败，旧密码错误");
            }

        } else {
            result.setData(false);
            result.setStateCode(400);
            result.setMsg("密码修改失败，不存在该用户");
        }


        return result;
    }

    @Transactional
    public Result registUser(User userData) {
        Result result = new Result();
        if (userDao.insertUserRegisterInfo(userData) == 1){
            result.setMsg("regist success");
            result.setStateCode(200);
            result.setData(userData.getUsername());
        }else {
            result.setData(false);
            result.setMsg("regist fail, username is exist");
            result.setStateCode(200);
        }

        return result;
    }
}
