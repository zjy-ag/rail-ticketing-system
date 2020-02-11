package com.code.rts.controller;


import com.code.rts.Result.Result;
import com.code.rts.entity.Trips;
import com.code.rts.service.TripsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
