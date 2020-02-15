package com.code.rts.controller;


import com.alibaba.fastjson.JSONObject;
import com.code.rts.Result.Result;
import com.code.rts.entity.Order;
import com.code.rts.entity.OrderReturn;
import com.code.rts.entity.User;
import com.code.rts.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {

    @Resource
    private OrderService orderService;
    /**
     * 得到分页用户
     * @param pn
     * @return
     */
    @GetMapping("/getallorders")
    public Map<String, Object> getAllUsers(@RequestParam(defaultValue="1",required=true,value="pn") Integer pn){

        //每页显示记录数
        Integer pageSize=5;
        //分页查询，注意顺序，startPage()方法放前面
        PageHelper.startPage(pn, pageSize);
        //获取所用用户信息
        List<OrderReturn> allOrder = orderService.getAllOrders();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。封装了详细的分页信息,传入连续显示的页数
        PageInfo<OrderReturn> pageInfo=new PageInfo(allOrder);

        Map<String, Object> modelMap = new HashMap<>();
        if (pageInfo != null){
            modelMap.put("code", 200);
            modelMap.put("data", pageInfo);
        }else {
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "获取model列表失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     *get all order
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
     * @return
     * */
    @PostMapping("/changeorder")
    public Result changeOrder(@RequestBody JSONObject jsonObject){
        int orderid = jsonObject.getInteger("orderId");
        int tripsid = jsonObject.getInteger("tripsId");
        Result result = orderService.changeOrder(orderid,tripsid);
        return result;
    }

    /**
     * 修改订单信息
     */

    @RequestMapping(value = "/updateorder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody Order order){
        int i = orderService.updateOrderStatus(order);
        Map<String, Object> modelMap = new HashMap<>();
        if (i == 1){
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "success");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }else {
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "获取model列表失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }


    /**
     * 根据id删除订单
     */
    @Transactional
    @RequestMapping(value = "/deleteOrder/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteOrder(@PathVariable("id")Integer id){
        Map<String, Object> modelMap = new HashMap<>();
        //删除用户
        try {
            int i = orderService.deleteOrder(id);
            if (i == 1){
                modelMap.put("code", 200);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("message", "success");
                dataMap.put("entity", null);
                modelMap.put("data", dataMap);
            }else {
                modelMap.put("code", 200);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("message", "删除失败");
                dataMap.put("entity", null);
                modelMap.put("data", dataMap);
            }
        }catch (Exception e){
            modelMap.put("code", 500);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", e.fillInStackTrace());
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }


    /**
     * 下订单（状态：未支付）
     */
    @Transactional
    @RequestMapping(value = "/saveOrderPaying",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteOrder(@RequestBody JSONObject jsonObject){
        int carInfoId = jsonObject.getInteger("car_info_id");
        int personId = jsonObject.getInteger("person_id");
        Order order = new Order();
        order.setCarInfoId(carInfoId);
        order.setPersonId(personId);
        order.setChangeTimes(0);
        order.setStatus(1);
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //此处应该获产生订单后的id号
            int i = orderService.saveOrderPaying(order);
            int orderId = order.getId();
            if (i == 1){
                modelMap.put("code", 200);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("message", "success");
                dataMap.put("entity", orderId);
                modelMap.put("data", dataMap);
            }else {
                modelMap.put("code", 200);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("message", "购买失败");
                dataMap.put("entity", null);
                modelMap.put("data", dataMap);
            }
        }catch (Exception e){
            modelMap.put("code", 500);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", e.fillInStackTrace());
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }
    @Transactional
    @RequestMapping(value = "/saveOrderPayed/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> saveOrderPayed(@PathVariable("id")Integer id){
        Map<String, Object> modelMap = new HashMap<>();
        try {
            int i = orderService.saveOrderPayed(id);

            if (i == 1){
                modelMap.put("code", 200);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("message", "success");
                dataMap.put("entity", null);
                modelMap.put("data", dataMap);
            }else {
                modelMap.put("code", 200);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("message", "操作失败");
                dataMap.put("entity", null);
                modelMap.put("data", dataMap);
            }
        }catch (Exception e){
            modelMap.put("code", 500);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", e.fillInStackTrace());
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }
}
