package com.code.rts.service;


import com.code.rts.Result.Result;
import com.code.rts.dao.OrderDao;
import com.code.rts.dao.TripsDao;
import com.code.rts.dao.UserDao;
import com.code.rts.entity.Order;
import com.code.rts.entity.Trips;
import com.code.rts.entity.User;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TripsService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private UserDao userDao;

    @Resource
    private TripsDao tripsDao;

    public Result getAlltrips(Trips trips) {
        Result result = new Result();
        List<Trips> tripsdata = tripsDao.getAlltrips(trips);
        if(tripsdata != null){
            result.setMsg("Query all succeed");
            result.setData(tripsdata);
            result.setStateCode(200);
        }
        else{
            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
    }

    public Page<Trips> getAllTripsForAdmin() {
        return tripsDao.getAllTripsForAdmin();
    }


    public Result getAimtrips(Trips trips) {
        Result result = new Result();
        Trips tripsdata = tripsDao.getAimtrips(trips);
        if(tripsdata != null){
            result.setMsg("Query all succeed");
            result.setData(trips);
            result.setStateCode(200);
        }
        else{
            result.setMsg("Query failes,no tickets");
            result.setStateCode(404);
        }
        return result;
    }

    @Transactional
    public Result buyTicket(String username, int carInfoId, String carNum) {
        Result result = new Result();

        //获取用户个人信息的id
        User customer = userDao.getUserByUsername(username);
        if (customer.getTrueName() == null || customer.getIdCardNum() == null || customer.getPhoneNum() == null){
            result.setStateCode(400);
            result.setMsg("购票前请完善用户个人信息");
            result.setData(false);
            return result;
        }
        Trips trips = new Trips();
        trips.setCarNum(carNum);
        trips.setId(carInfoId);
        //获取车票详细信息
        Trips tripsInfoData = tripsDao.getTripsInfoByCarInfoIdAndId(trips);
        //判断车票是否卖光了
        Order order = new Order();
        order.setCarInfoId(carInfoId);
        order.setPersonId(customer.getId());
        order.setChangeTimes(0);

        order.setStatus(0);
        if (tripsInfoData.getTicketNum() >= 1){

             orderDao.buyTicket(order);
             trips.setTicketNum(tripsInfoData.getTicketNum() - 1);
             trips.setCarNum(null);
              int i = tripsDao.updateTrips(trips);
            Map<String, Object> detailData = new HashMap<>();
            if (order.getId() > 0 && i == 1){
                //还有车票，购买成功
                  result.setMsg("购票成功");
                  result.setStateCode(200);
                  detailData.put("customer", customer);
                  detailData.put("changeTimes",3 - order.getChangeTimes());
                  detailData.put("order", order);
                  result.setData(detailData);
            }
            return result;
        }
        else {
            //车票卖光了，购买失败
            result.setMsg(" 购买失败，车票已经卖光");
            result.setStateCode(400);
            result.setData(false);
            return result;
        }
    }

    @Transactional
    public Result ticketRetund(int personId , String carNum, String startTime, String reachTime){
        Result result = new Result();
        //票数+1
        int i = tripsDao.refundTrips(personId, carNum, startTime, reachTime);
        //把订单状态改为退票
        int j = orderDao.updateOrder1(personId, carNum, startTime, reachTime);
        if (i > 0 && j > 0){
            result.setData(true);
            result.setMsg("退票成功");
            result.setStateCode(200);
        }else {
            result.setData(false);
            result.setMsg("退票失败");
            result.setStateCode(400);
        }
        return result;
    }

    @Transactional
    public Result payMoney(int orderId) {
        Result result = new Result();
        if(orderDao.updateOrder(orderId) == 1){
            result.setStateCode(200);
            result.setMsg("支付成功");
            result.setData(true);
        }else {
            result.setData(false);
            result.setMsg("支付失败，请重新支付");
            result.setStateCode(400);
        }
        return result;
    }


    /**
     * 保存车次
     * @param trips
     * @return
     */
    public int saveTrip(Trips trips) {
        return tripsDao.saveTrip(trips);
    }

    public int updateTripForAdmin(Trips trips){
        return tripsDao.updateTripForAdmin(trips);
    }

    public int delTrip(Integer id){
        return tripsDao.deleteTrip(id);
    }

}
