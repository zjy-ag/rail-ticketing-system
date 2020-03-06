package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;

/**
 * 途经站信息
* */
@Getter
@Setter
public class TripsVia {
    private Integer id;
    private String carNum;
    private String stationName;
    //    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private  String startTime;
    //    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String reachTime;
    private Integer orderNum;

}
