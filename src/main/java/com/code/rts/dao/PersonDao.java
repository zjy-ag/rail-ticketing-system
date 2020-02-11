package com.code.rts.dao;

import com.code.rts.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface PersonDao {


    /**
     * 查询全部车票信息
     * @param trips
     * @return
     * */
    @Select("select * from trips where orgin_location=#{orginLocation} and destination_location=#{destinationLocation} and start_time like CONCAT('%',#{startTime},'%')")
    List<Trips> getAlltrips(Trips trips);

    /**
     * 修改用户个人信息
     * @param person
     * @return
     */
    int updateUserInfo(Person person);

    /**
     * 通过id和车号查询车次信息
     * @param trips
     * @return
     */
    @Select("select * from trips where id = #{id} and car_num = #{carNum}")
    Trips getTripsInfoByCarInfoIdAndId(Trips trips);


    /**
     * 添加用户个人信息
     * @param person
     * @return
     */
    int insertUserInfo(Person person);
    /**
     * 通过id获取个人信息
     * @return
     */

    @Select("select * from person where id = #{Id}")
    Person getPersonInfo(@Param("Id") int Id);

    /**
     * 通过username查询个人信息
     * @param username
     * @return
     */
//    @Select("select * from person where (select person_id from user where username = #{username}")
    Person getPersonInfo1(String username);


}
