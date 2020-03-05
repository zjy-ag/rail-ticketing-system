package com.code.rts.service;


import com.code.rts.dao.TripsViaDao;
import com.code.rts.entity.TripsVia;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TripsViaService {
    @Resource
    private TripsViaDao tripsViaDao;

    public List<TripsVia> getAimTripsVia(String carNum) {
        return tripsViaDao.getAimTripsVia(carNum);
    }

    public int updateTripVia(TripsVia tripsVia){
        return tripsViaDao.updateTripsVia(tripsVia);
    }

    public int saveTripVia(TripsVia tripsVia){
        return tripsViaDao.saveOneTripVia(tripsVia);
    }

    public int delTripVia(Integer id){
        return tripsViaDao.deleteTripVia(id);
    }
}
