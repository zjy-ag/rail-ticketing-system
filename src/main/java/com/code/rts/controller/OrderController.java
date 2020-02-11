package com.code.rts.controller;


import com.alibaba.fastjson.JSONObject;
import com.code.rts.Result.Result;
import com.code.rts.service.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     *get all order
     * @author 41688
     * @return
     * */
    @PostMapping("/getorder")
    public Result getOrder(@RequestBody JSONObject jsonObject){
        String username = jsonObject.getString("username");

        Result result = orderService.getOrder(username);
        return result;
    }

    /**
     * change order
     * @author 41688
     * @return
     * */
    @PostMapping("/changeorder")
    public Result changeOrder(@RequestBody JSONObject jsonObject){
        int orderid = jsonObject.getInteger("orderId");
        int tripsid = jsonObject.getInteger("tripsId");
        Result result = orderService.changeOrder(orderid,tripsid);
        return result;
    }
}
