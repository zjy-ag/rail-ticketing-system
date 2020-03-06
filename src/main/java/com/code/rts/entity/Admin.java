package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;

/**
 *
 * 用于存储管理员账号信息
 */
@Getter
@Setter
public class Admin {
    private int id;
    private String username;
    private String password;

}
