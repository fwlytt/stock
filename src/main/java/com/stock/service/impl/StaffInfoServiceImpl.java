package com.stock.service.impl;

import com.stock.dao.StaffInfoMapper;
import com.stock.dao.StockMapper;
import com.stock.dao.StockRecordMapper;
import com.stock.domain.StaffEvent;
import com.stock.domain.StaffInfo;
import com.stock.domain.Stock;
import com.stock.domain.StockRecord;
import com.stock.domain.val.StaffInfoVal;
import com.stock.service.StaffInfoService;
import com.stock.service.StockService;
import com.stock.util.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StaffInfoServiceImpl extends BaseService<StaffInfo> implements StaffInfoService {

    @Autowired
    private StaffInfoMapper staffInfoMapper;

    @Override
    public List<StaffInfoVal> queryList(String key) {
        return staffInfoMapper.queryList(key);
    }

    @Override
    public List<StaffEvent> queryEventList(Map<String, Object> map) {
        return staffInfoMapper.queryEventList(map);
    }


}
