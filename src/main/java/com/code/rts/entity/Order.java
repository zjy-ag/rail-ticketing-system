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

    public Order(int id, int carInfoId, int personId, int changeTimes, int status) {
        this.id = id;
        this.carInfoId = carInfoId;
        this.personId = personId;
        this.changeTimes = changeTimes;
        this.status = status;
    }
    public Order(int carInfoId, int personId, int changeTimes) {
        this.carInfoId = carInfoId;
        this.personId = personId;
        this.changeTimes = changeTimes;
    }
}
