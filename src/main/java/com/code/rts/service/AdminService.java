package com.code.rts.service;

import com.code.rts.Result.Result;
import com.code.rts.dao.AdminDao;
import com.code.rts.entity.Admin;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    private AdminDao adminDao;

    public Page<Admin> getAllAdmins() {
        return adminDao.getAllAdmins();
    }

    public int getAdminsCount() {
        return adminDao.getAdminsCount();
    }

    public Integer deleteAdmin(Integer id) {
        return  adminDao.deleteAdmin(id);
    }

    /**
     * 校验用户名
     * @param username
     * @return
     */
    public Admin checkAdminName(String username) {

        return adminDao.getAdminByUsername(username);
    }

    /**
     * 保存用户
     * @param admin
     * @return
     */
    public int saveAdmin(Admin admin) {
        int i = adminDao.saveAdmin(admin);
        return i;
    }

}
