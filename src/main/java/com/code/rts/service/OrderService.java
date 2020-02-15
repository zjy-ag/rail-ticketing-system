package com.code.rts.service;

import com.code.rts.Result.Result;
import com.code.rts.dao.*;
import com.code.rts.entity.*;
import com.github.pagehelper.Page;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private TripsDao tripsDao;

    /**
     * 得到订单状态
     * @return
     */
    public Page<OrderReturn> getAllOrders(){
        return orderDao.getAllOrders();
    }

    /**
     * 得到订单
     * @param username
     * @return
     */
    public Page<OrderReturn> getOrder(String username){
        return orderDao.getOrder(username);
    }
//    public Result getOrder(String username) {
//        Result result = new Result();
//        User user = new User();
//        List<OrderReturn> orderReturnList  = new ArrayList<OrderReturn>();
//        Trips trips = new Trips();
//
//        //获取订单列表
//        List<Order> orderdata= orderDao.getOrder(username);
//
//        if(orderdata!=null){
//
//            for(Order i:orderdata){
//                OrderReturn orderReturn = new OrderReturn();
//                user = orderDao.getUserinfo(i.getPersonId());
//                trips = tripsDao.gettrips(i.getCarInfoId());
//                orderReturn.setTrueName(user.getTrueName());
//                orderReturn.setIdCardNum(user.getIdCardNum());
//                orderReturn.setPhoneNum(user.getPhoneNum());
//                orderReturn.setCarNum(trips.getCarNum());
//                orderReturn.setDestinationLocation(trips.getDestinationLocation());
//                orderReturn.setOrginLocation(trips.getOrginLocation());
//                orderReturn.setTicketPrice(trips.getTicketPrice());
//                orderReturn.setTicketNum(trips.getTicketNum());
//                orderReturn.setStartTime(trips.getStartTime());
//                orderReturn.setReachTime(trips.getReachTime());
//                if (i.getStatus() == 1){
//                    orderReturn.setStatus("已支付");
//                }else if (i.getStatus() == 2){
//                    orderReturn.setStatus("等待支付");
//                }else if (i.getStatus() == 3){
//                    orderReturn.setStatus("已退票");
//                }
////                orderReturn.setStartTime(orderReturn.getStartTime());
////                orderReturn.setStartTime(trips.getStartTime());
//                orderReturnList.add(orderReturn);
//
//            }
//            result.setStateCode(200);
//            result.setMsg("Query succeed");
//            result.setData(orderReturnList);
//        }
//        else{
////            result.setStateCode();
//            result.setStateCode(404);
//            result.setData(false);
//            result.setMsg("Query failed,no order");
//        }
//        return result;
//    }

    public Result changeOrder(int orderId, int tripsId) {
        Result result = null;
        Order order = orderDao.getAimOrder(orderId);
        Trips trips =  tripsDao.gettrips(tripsId);
        if(trips.getTicketNum()>0){
            tripsDao.changeOldtrips(order.getCarInfoId());
            tripsDao.changeNewtrips(tripsId);
            orderDao.changeOrder(orderId,tripsId);
            result.setStateCode(200);
            result.setMsg("change order succeed");
        }
        else{
            result.setStateCode(404);
            result.setMsg("change order failed");
        }
        return result;
    }

    public int updateOrderStatus(Order order){
        return orderDao.updateOrderStatus(order);
    }

    public Integer deleteOrder(Integer id){
        return orderDao.deleteOrder(id);
    }

    public int saveOrderPaying(Order order){
        return orderDao.saveOrderPaying(order);
    }

    public int saveOrderPayed(int orderId){
        return orderDao.saveOrderPayed(orderId);
    }
}
