package com.code.rts.dao;

import com.code.rts.entity.TripsVia;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TripsViaDao {
    /**
     *查询目标车次的途径站信息
     * @param carNum
     * @return
     * */
    @Select("select * from trips_via where car_num = #{carNum} order by `order_num` asc")
    List<TripsVia> getAimTripsVia(String carNum);

    /***
     * 查找单个途径站信息
     * @param id
     * @return
     */
    @Select("select * from trips_via where id = #{id}")
    TripsVia getOneTripsVia(int id);

    /**
     * 添加单个途径站
     * @param tripsVia
     * @return
     */
    @Insert("insert into `trips_via` (station_name, start_time, reach_time, car_num, order_num) values(#{stationName}, #{startTime}, #{reachTime}, #{carNum}, #{orderNum})")
    int saveOneTripVia(TripsVia tripsVia);

    /**
     * 添加多个途径站
     * @param tripsVias
     * @return
     */
    @Insert("insert into `trips_via` (station_name, start_time, reach_time, car_num, order_num) values(#{tripsVias.stationName}, #{tripsVias.startTime}, #{tripsVias.reachTime}, #{tripsVias.carNum}, #{tripsVias.order_num})")
    int saveMulTripVia(List<TripsVia> tripsVias);

    /**
     * 更新单个途经站信息
     * @param tripsVia
     * @return
     */
    int updateTripsVia(TripsVia tripsVia);

    /**
     * 根据id删除站点
     * @param id
     * @return
     */
    Integer deleteTripVia(Integer id);
}
