package com.code.rts.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReturn {
    private Integer id;
    private String orginLocation;
    private String destinationLocation;
    //    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String startTime;
    //    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String reachTime;
    private String carNum;
    private int ticketPrice;
    private int ticketNum;
    private String trueName;
    private String idCardNum;
    private String phoneNum;
    private String status;


}
