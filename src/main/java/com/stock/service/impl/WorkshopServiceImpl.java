package com.stock.service.impl;

import com.stock.dao.WorkshopMapper;
import com.stock.domain.Workshop;
import com.stock.domain.val.WorkshopVal;
import com.stock.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopServiceImpl extends BaseService<Workshop> implements WorkshopService {

    @Autowired
    private WorkshopMapper workshopMapper;

    @Override
    public List<WorkshopVal> queryList(String key) {
        return workshopMapper.queryList(key);
    }

    @Override
    public int checkName(Workshop param) {
        return workshopMapper.checkName(param);
    }

    @Override
    public List<Workshop> queryInfoList(Integer customerId) {
        return workshopMapper.queryInfoList(customerId);
    }
}
