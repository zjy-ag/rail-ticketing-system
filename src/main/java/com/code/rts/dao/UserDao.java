package com.code.rts.dao;

import com.code.rts.entity.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

/**
 * User表持久类
 */

public interface UserDao {

    /**
     * 通过username查询User信息
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    /**
     * 注册用户信息
     * @param user
     * @return
     */
    @Insert("insert into user (username, password) values(#{username}, #{password})")
    int insertUserRegisterInfo(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 更新用户账户信息
     * @param user
     * @return
     */
    int updateUserRegisterInfo(User user);


    /**
     * 查询所有用户
     * @return
     */
    Page<User> getAllUsers();

    /**
     * 查询所有用户的个数
     * @return
     */
    Integer getUsersCount();


    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    Integer deleteUser(Integer id);

    /**
     * 在后台添加用户
     * @param user
     * @return
     */
    @Insert("insert into user (username, password, true_name, id_card_num, phone_num, age, sex) values(#{username}, #{password}, #{trueName}, #{idCardNum}, #{phoneNum}, #{age}, #{sex})")
    int saveUser(User user);

}