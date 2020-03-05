package com.code.rts.controller;


import com.code.rts.Result.Result;
import com.code.rts.entity.Trips;
import com.code.rts.entity.TripsVia;
import com.code.rts.service.TripsService;
import com.code.rts.service.TripsViaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车次途经站控制器
 * */
@RestController
@CrossOrigin
public class TripsViaController {
    /**
     *
     */
    @Resource
    private TripsViaService tripsViaService;

    @GetMapping("/getAimTripsVia")
    @ResponseBody
    public Result getAimTripsVia(@RequestParam(value="carNum") String carNum){
        Result result = new Result();
        List<TripsVia> tripsVia = tripsViaService.getAimTripsVia(carNum);
        if (tripsVia != null){
            result.setStateCode(200);
            result.setMsg("success");
            result.setData(tripsVia);
            return result;
        }else {
            result.setStateCode(200);
            result.setMsg("数据为空");
            result.setData(false);
            return result;
        }
    }

    /**
     * 保存车次途经站点信息
     * @param tripsVia
     * @return
     */
    @Transactional
    @PostMapping(value = "/saveTripVia")
    @ResponseBody
    public Map<String, Object> saveTripVia(@RequestBody TripsVia tripsVia){
        int i = tripsViaService.saveTripVia(tripsVia);
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
            dataMap.put("message", "添加车次途经站失败");
            dataMap.put("entity", null);
            modelMap.put("data", dataMap);
        }
        return modelMap;
    }

    /**
     * 修改用户信息
     */

    @RequestMapping(value = "/updateTripVia",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateTripVia(@RequestBody TripsVia tripsVia){
        int i = tripsViaService.updateTripVia(tripsVia);
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
     * 根据id删除途经站点
     */
    @Transactional
    @RequestMapping(value = "/deleteTripVia/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteTripVia(@PathVariable("id")Integer id){
        Map<String, Object> modelMap = new HashMap<>();

        try {
            int i = tripsViaService.delTripVia(id);
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
