package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * 用户注册信息pojo
 * 用于存储用户账号信息
 */
@Getter
@Setter
public class User {
    private int id;
    private String username;
    private String password;
    private String trueName;
    private String idCardNum;
    private String phoneNum;
    private int age;
    private String sex;
}
