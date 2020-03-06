package com.code.rts.controller;


import com.code.rts.Result.Result;
import com.code.rts.entity.Trips;
import com.code.rts.entity.User;
import com.code.rts.service.TripsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询车票
 * */
@RestController
@CrossOrigin
public class TripsController {
    /**
     *
     */
    @Resource
    private TripsService tripsService;

    @PostMapping("/getAimtrips")
    @ResponseBody
    public Result getAimtrips(@RequestBody Trips trips){
        Result result = tripsService.getAimtrips(trips);
        return result;
    }

    @PostMapping("/getalltrips")
    @ResponseBody
    public Result getAlltrips(@RequestBody Trips trips){
        Result result = tripsService.getAlltrips(trips);
        return result;
    }

    /**
     * 得到分页用户
     * @param pn
     * @return
     */
    @GetMapping("/getalltripsforadmin")
    public Map<String, Object> getAllTripsForAdmin(@RequestParam(defaultValue="1",required=true,value="pn") Integer pn){

        //每页显示记录数
        Integer pageSize=5;
        //分页查询，注意顺序，startPage()方法放前面
        PageHelper.startPage(pn, pageSize);
        //获取所用用户信息
        List<Trips> allTrip = tripsService.getAllTripsForAdmin();
        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。封装了详细的分页信息,传入连续显示的页数
        PageInfo<Trips> pageInfo=new PageInfo(allTrip);

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
     * 保存车次信息
     * @param trips
     * @return
     */
    @Transactional
    @RequestMapping(value = "/saveTrip",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveUser(@RequestBody Trips trips){
        int i = tripsService.saveTrip(trips);
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
            dataMap.put("message", "添加车次失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 修改用户信息
     */

    @RequestMapping(value = "/updateTripForAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateTripForAdmin(@RequestBody Trips trips){
        int i = tripsService.updateTripForAdmin(trips);
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
            dataMap.put("message", "更新车次信息失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 根据id删除车次
     */
    @Transactional
    @RequestMapping(value = "/deleteTrip/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteTrip(@PathVariable("id")Integer id){
        Map<String, Object> modelMap = new HashMap<>();

        try {
            int i = tripsService.delTrip(id);
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
