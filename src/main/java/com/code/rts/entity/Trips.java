package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用于查询车票信息
* */
@Getter
@Setter
public class Trips {
    private int id;
    private String orginLocation;
    private String destinationLocation;
    @DateTimeFormat(pattern="hh:mm:ss")
    private String startTime;
    @DateTimeFormat(pattern="hh:mm:ss")
    private String reachTime;
    private String spanDays;
    private String carNum;
    private Double ticketPrice;
    private int ticketNum;

}
