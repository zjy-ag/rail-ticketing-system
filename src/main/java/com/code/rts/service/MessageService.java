package com.code.rts.service;

import com.code.rts.Result.Result;
import com.code.rts.dao.MessageDao;
import com.code.rts.dao.UserDao;
import com.code.rts.entity.Message;
import com.code.rts.entity.User;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageService {
    @Resource
    private MessageDao messageDao;

    public int sendMessage(Message message){
        return messageDao.sendMessage(message);
    }

    public List<Message> getReceiveMsg(Integer toUser){
        return messageDao.getReceiveMsg(toUser);
    }

}
