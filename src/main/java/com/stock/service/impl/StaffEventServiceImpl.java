package com.stock.service.impl;

import com.stock.dao.StaffEventMapper;
import com.stock.dao.StaffInfoMapper;
import com.stock.domain.StaffEvent;
import com.stock.domain.StaffInfo;
import com.stock.domain.val.StaffInfoVal;
import com.stock.service.StaffEventService;
import com.stock.service.StaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffEventServiceImpl extends BaseService<StaffEvent> implements StaffEventService {

    @Autowired
    private StaffEventMapper staffEventMapper;


}
