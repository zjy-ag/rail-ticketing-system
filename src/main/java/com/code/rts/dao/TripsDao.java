package com.code.rts.dao;

import com.code.rts.entity.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TripsDao {
    /**
     *查询目标车票信息
     * @param trips
     * @return
     * */
    @Select("select * from trips where start_time like CONCAT('%',#{startTime},'%') and car_num = #{carNum}")
    Trips getAimtrips(Trips trips);

    /**
     * 查询全部车票信息
     * @param trips
     * @return
     * */
    @Select("select * from trips where orgin_location like CONCAT('%',#{orginLocation},'%') and destination_location like CONCAT('%',#{destinationLocation},'%')")
    List<Trips> getAlltrips(Trips trips);

    /**
     * 分页查询全部车票
     * @return
     */
    Page<Trips> getAllTripsForAdmin();

    /***
     * @param id
     * @return
     */
    @Select("select * from trips where id = #{id}")
    Trips gettrips(int id);

    /**
     * 在后台添加车次
     * @param trips
     * @return
     */
    @Insert("insert into `trips` (orgin_location, destination_location, start_time, reach_time, span_days, car_num, ticket_price, ticket_num) values(#{orginLocation}, #{destinationLocation}, #{startTime}, #{reachTime}, #{spanDays}, #{carNum}, #{ticketPrice}, #{ticketNum})")
    int saveTrip(Trips trips);

    /**
     *订单改签后改变旧车票信息
     * @param oldId
     * @return
    * */
    @Update("update trips set ticket_num = ticket_num+1 where id = #{oldId}")
    int changeOldtrips(int oldId);

    /**
     *订单改签后改变新车票信息
     * @param newId
     * @return
     * */
    @Update("update trips set ticket_num = ticket_num-1 where id = #{newId}")
    int changeNewtrips(int newId);

    /**
     * 通过id和车号查询车次信息
     * @param trips
     * @return
     */
    @Select("select * from trips where id = #{id} and car_num = #{carNum}")
    Trips getTripsInfoByCarInfoIdAndId(Trips trips);

    /**
     * 更新trips表
     * @param trips
     * @return
     */
    @Update(" <script> update trips set <if test='ticketNum != 0'> ticket_num = #{ticketNum}</if>" +
            "where id = #{id} </script>")
    int updateTrips(Trips trips);

    @Update("update trips set ticket_num = ticket_num + 1" +
            " where  car_num = #{carNum}" +
            "and start_time = #{startTime} and reach_time = #{reachTime}")
    int refundTrips(@Param("personId") int personId, @Param("carNum") String carNum,
                                    @Param("startTime") String startTime, @Param("reachTime") String reachTime);

    @Select("select * from trips where car_num = #{carNum} and start_time = #{startTime}")
    Trips getTripsInfoByCarNumAndStartTime(@Param("carNum") String carNum, @Param("startTime") String startTime);

    /**
     * 更新用户信息
     * @param trips
     * @return
     */
    int updateTripForAdmin(Trips trips);

    /**
     * 根据id删除车次
     * @param id
     * @return
     */
    Integer deleteTrip(Integer id);
}
