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


    @Transactional
    public boolean updateUser(User user) {
        int i = userDao.updateUser(user);
        if (i == 1){
            return true;
        }else {
            return false;
        }

    }

    public Result getPersonInfo(String username) {
        Result result = new Result();
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            result.setStateCode(400);
            result.setMsg("未填写个人信息，请前往个人信息页面完善个人信息");
            result.setData(null);
        } else {
            result.setStateCode(200);
//            result.setStateCode();
            result.setMsg("查询成功，已填写个人信息");
            result.setData(user);
        }
        return result;
    }

    public Page<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public int getUsersCount() {
        return userDao.getUsersCount();
    }

    public Integer deleteUser(Integer id) {
        return  userDao.deleteUser(id);
    }

    /**
     * 校验用户名
     * @param username
     * @return
     */
    public User checkUserName(String username) {

        return userDao.getUserByUsername(username);
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    public int saveUser(User user) {
        int i = userDao.saveUser(user);
        return i;
    }

}
