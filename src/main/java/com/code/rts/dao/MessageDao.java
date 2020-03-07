package com.code.rts.dao;

import com.code.rts.entity.Message;
import com.code.rts.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User表持久类
 */

public interface MessageDao {


    /**
     * 发送分享信息
     * @param message
     * @return
     */
    @Insert("insert into message (from_user, to_user, content, send_time, is_read) values(#{fromUser}, #{toUser}, #{content}, #{sendTime}, #{isRead})")
    int sendMessage(Message message);

    /**
     * 获取收件箱信息
     * @param toUser
     * @return
     */
    @Select("select * from message where to_user = #{toUser} order by is_read asc")
    List<Message> getReceiveMsg(Integer toUser);

    /**
     * 获取单个信息
     * @param id
     * @return
     */
    @Select("select * from message where id = #{id}")
    Message getReceiveMsgDetail(Integer id);

    /**
     * 设置为已读
     * @param id
     * @return
     */
    @Select("update message set is_read = 1 where id = #{id}")
    Integer setMsgStatus(Integer id);

    /**
     * 查询所有消息
     * @return
     */
    Page<Message> getAllMsgs();

    /**
     * 根据id删除消息
     * @param id
     * @return
     */
    Integer deleteMsg(Integer id);

    /**
     * 更新信息
     * @param message
     * @return
     */
    Integer updateMsg(Message message);

}