package com.code.rts.service;

import com.code.rts.Result.Result;
import com.code.rts.dao.*;
import com.code.rts.entity.*;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private PersonDao personDao;

//    @Transactional
//    @Override
//    public Result insertUserInfo(String username, Person person) {
//        Result result = new Result();
//        int i = personDao.insertUserInfo(person);
//        result.setMsg("update fail, please update again");
//        result.setStateCode(404);
//        result.setData(false);
//        if (i < 1){
//
//        } else {
//            User user = new User();
//            user.setUsername(username);
//            user.setPersonId(person.getId());
//            int j = userDao.updateUserRegisterInfo(user);
//            if (j == 1){
//                result.setMsg("update success");
//                result.setStateCode(200);
//                result.setData(true);
//            }
//        }
//        return result;
//    }

    public Result insertUserInfo(String username, Person person) {
        return null;
    }

    @Transactional
    public Result updateUserInfo(String username, Person person) {
        User user = userDao.getUserByUsername(username);
        Result result = new Result();
        result.setMsg("update fail, please update again");
        result.setStateCode(404);
        result.setData(false);
        if (user != null ) {
            if( user.getPersonId() > 0) {
                person.setId(user.getPersonId());
                int i = personDao.updateUserInfo(person);
                if (i == 1) {
                    result.setMsg("update success");
                    result.setStateCode(200);
                    result.setData(true);
                }
            } else {
               int i =  personDao.insertUserInfo(person);
                if (i == 1){
                    user.setPersonId(person.getId());
                    int j = userDao.updateUserRegisterInfo(user);
                    if (j == 1){
                        result.setMsg("update success");
                        result.setStateCode(200);
                        result.setData(true);
                    }
                }
            }
        }
        return result;
    }

    public Result getPersonInfo(String username) {
        Result result = new Result();
        Person person = personDao.getPersonInfo1(username);
        if (person == null) {
            result.setStateCode(400);
            result.setMsg("未填写个人信息，请完善个人信息");
            result.setData(null);
        } else {
            result.setStateCode(200);
//            result.setStateCode();
            result.setMsg("查询成功，已填写个人信息");
            result.setData(person);
        }
        return result;
    }

    public Page<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public int getUsersCount() {
        return userDao.getUsersCount();
    }
}
