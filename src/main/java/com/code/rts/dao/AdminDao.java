package com.code.rts.dao;

import com.code.rts.entity.Admin;
import com.code.rts.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
 * User表持久类
 */

public interface AdminDao {

    /**
     * 通过username查询Admin信息
     * @param username
     * @return
     */
    @Select("select * from admin where username = #{username}")
    Admin getAdminByUsername(String username);

    /**
     * 注册管理员信息
     * @param admin
     * @return
     */
    @Insert("insert into admin (username, password) values(#{username}, #{password})")
    int insertAdminRegisterInfo(Admin admin);

    /**
     * 更新管理员账户信息
     * @param admin
     * @return
     */
    int updateAdminRegisterInfo(Admin admin);


    /**
     * 查询所有管理员
     * @return
     */
    Page<Admin> getAllAdmins();

    /**
     * 查询所有管理员的个数
     * @return
     */
    Integer getAdminsCount();


    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    Integer deleteAdmin(Integer id);

    /**
     * 在后台添加管理员
     * @param admin
     * @return
     */
    @Insert("insert into admin (username, password) values(#{username}, #{password})")
    int saveAdmin(Admin admin);

}