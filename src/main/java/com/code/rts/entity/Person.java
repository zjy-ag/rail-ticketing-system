package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * person 类
 * 用户对person信息持久化
 *
 */

@Getter
@Setter
public class Person {
    private int id;
    private String trueName;
    private String idCardNum;
    private String phoneNum;
    private int age;
    public Person(int id, String trueName, String idCardNum, String phoneNum, int age) {
        this.id = id;
        this.trueName = trueName;
        this.idCardNum = idCardNum;
        this.phoneNum = phoneNum;
        this.age = age;
    }

    public Person(String trueName, String idCardNum, String phoneNum, int age) {
        this.trueName = trueName;
        this.idCardNum = idCardNum;
        this.phoneNum = phoneNum;
        this.age = age;

    }

    public Person() {

    }

}
