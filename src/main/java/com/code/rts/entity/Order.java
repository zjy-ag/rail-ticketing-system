package com.code.rts.entity;


import lombok.Getter;
import lombok.Setter;

/**
 *查询订单信息
 * */

@Getter
@Setter
public class Order {
    private int id;
    private int carInfoId;
    private int personId;
    private int changeTimes;
    //0是预定未付款， 1是已经支付， 2是退票
    private int status;
    private String stautsMsg;

}
