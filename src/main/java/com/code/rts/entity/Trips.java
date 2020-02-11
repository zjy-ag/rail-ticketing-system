package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * 用于查询车票信息
* */
@Getter
@Setter
public class Trips {
    private int id;
    private String orginLocation;
    private String destinationLocation;
//    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String startTime;
//    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String reachTime;
    private String carNum;
    private int ticketPrice;
    private int ticketNum;

}