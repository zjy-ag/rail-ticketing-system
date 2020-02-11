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
    @Insert("insert into user (username, password, person_id) values(#{username}, #{password}, #{personId})")
    int insertUserRegisterInfo(User user);

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






}
