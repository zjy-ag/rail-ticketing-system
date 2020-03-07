package com.code.rts.controller;


import com.alibaba.fastjson.JSONObject;
import com.code.rts.Result.Result;
import com.code.rts.entity.Message;
import com.code.rts.entity.User;
import com.code.rts.service.MessageService;
import com.code.rts.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
public class MessageController {
    @Resource
    private MessageService messageService;


    /**
     * 发送消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/sendMessage",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> sendMessage(@RequestBody Message message){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setSendTime(df.format(day));
        message.setIsRead(0);
        int i = messageService.sendMessage(message);
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
            dataMap.put("message", "发送失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 获取收到的消息
     * @param toUser
     * @return
     */
    @RequestMapping(value = "/getReceiveMsg",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getReceiveMsg(@RequestParam Integer toUser){
        List<Message> messages = messageService.getReceiveMsg(toUser);
        Map<String, Object> modelMap = new HashMap<>();
        if (messages != null){
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "success");
            dataMap.put("entity", messages);
            modelMap.put("data", dataMap);
        }else {
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "获取收件箱信息失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 获取收到的单条消息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getReceiveMsgDetail",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getReceiveMsgDetail(@RequestParam Integer id){
        Message message = messageService.getReceiveMsgDetail(id);
        Map<String, Object> modelMap = new HashMap<>();
        if (message != null){
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "success");
            dataMap.put("entity", message);
            modelMap.put("data", dataMap);
        }else {
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "获取信息详情失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 设置消息为已读
     * @param id
     * @return
     */
    @RequestMapping(value = "/setMsgStatus",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> setMsgStatus(@RequestParam Integer id){
        Integer i = messageService.setMsgStatus(id);
        Map<String, Object> modelMap = new HashMap<>();
        if (i == 1){
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "success");
            dataMap.put("entity", i);
            modelMap.put("data", dataMap);
        }else {
            modelMap.put("code", 200);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("message", "更新为已读失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 得到分页信息
     * @param pn
     * @return
     */
    @GetMapping("/getallmsgs")
    public Map<String, Object> getAllMsgs(@RequestParam(defaultValue="1",required=true,value="pn") Integer pn){

        //每页显示记录数
        Integer pageSize=5;
        //分页查询，注意顺序，startPage()方法放前面
        PageHelper.startPage(pn, pageSize);
        //获取所用用户信息
        List<Message> allMsg = messageService.getAllMsg();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。封装了详细的分页信息,传入连续显示的页数
        PageInfo<User> pageInfo=new PageInfo(allMsg);

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
     * 修改消息信息
     */

    @RequestMapping(value = "/updateMsg",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateMsg(@RequestBody Message message){
        Integer i = messageService.updateMsg(message);
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
            dataMap.put("message", "修改消息失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 根据id删除消息
     */
    @Transactional
    @RequestMapping(value = "/deleteMsg/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteMsg(@PathVariable("id")Integer id){
        Map<String, Object> modelMap = new HashMap<>();

        //保存用户
        try {
            int i = messageService.deleteMsg(id);
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
            dataMap.put("message", "删除失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }



}
