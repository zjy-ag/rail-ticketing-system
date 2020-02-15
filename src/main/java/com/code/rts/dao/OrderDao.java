package com.code.rts.dao;

import com.code.rts.entity.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.aspectj.weaver.ast.Or;

import java.util.List;


public interface OrderDao {
    /**
     * 查询所有订单
     * @return
     */
    Page<OrderReturn> getAllOrders();

    /**
     * 删除订单
     * @param id
     * @return
     */
    Integer deleteOrder(Integer id);
    /**
     *插入订单信息
     * @param order
     * @return
     */
    @Insert("INSERT INTO `order` (car_info_id, person_id, change_times, status) VALUES (#{carInfoId}, #{personId}, #{changeTimes}, #{status})\n" +
            "    ")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void buyTicket(Order order);

    /**
     * 下订单（状态：未支付）
     * @param order
     * @return
     */
    @Insert("INSERT INTO `order` (car_info_id, person_id, change_times, status) VALUES (#{carInfoId}, #{personId}, #{changeTimes}, #{status})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int saveOrderPaying(Order order);

    /**
     * @param order
     * @return
     */
    @Update("update `order` set status = #{status} where id = #{id}")
    int updateOrderStatus(Order order);

    /**
     * 退票，把status改为3
     * @param orderId
     * @return
     */
    @Update("update `order` set status = 3 where id = #{orderId}")
    int updateOrder(int orderId);

    /**
     * 支付后，把status改成2
     * @param orderId
     * @return
     */
    @Update("update `order` set status = 2 where id = #{orderId}")
    int saveOrderPayed(int orderId);


    /**
     * 通过用户查询订单（前台使用）
     * @param userName
     * @return
     */
    Page<OrderReturn> getOrder(String userName);


    @Update("update `order` set status = 2 where person_id=#{personId} and car_info_id = (select id from trips where car_num = #{carNum}" +
            " and start_time = #{startTime} and reach_time = #{reachTime})")
    int updateOrder1(@Param("personId") int personId, @Param("carNum") String carNum,
                                     @Param("startTime") String startTime, @Param("reachTime") String reachTime);

//    @Select("select * from `order` where person_id = #{personId}")
//    public List<Order> getOrder(Order order);

    /**
     * 查询目标订单
     * @param orderId
     * @return
    * */
    @Select("select * from `order` where id = #{orderId}")
    Order getAimOrder(int orderId);

    /**
     * 查询订单所有人信息
     */
    @Select("select * from `person` where `id` = #{personId}")
    User getUserinfo(int id);

    /**
     * 改签订单信息变更
     * @return
     */
    @Update("update `order` set car_info_id = #{tripsId},change_times = change_times +1,status = 1 where id = #{orderId}")
    int changeOrder(@Param("orderId") int orderId, @Param("tripsId") int tripsId);


}
