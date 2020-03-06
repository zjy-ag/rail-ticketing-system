package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用于存储好友关系信息
 */
@Getter
@Setter
public class Friends {
    private Integer id;
    private Integer userA;
    private Integer userB;
    private Integer relaA;
    private Integer relaB;
}
