package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用于存储分享信息
 */
@Getter
@Setter
public class Message {
    private Integer id;
    private Integer fromUser;
    private Integer toUser;
    private String content;
    //@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String sendTime;
    private Integer isRead;
}
