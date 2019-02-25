package com.stock.service.impl;

import com.stock.dao.StockRecordMapper;
import com.stock.domain.StockRecord;
import com.stock.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockRecordServiceImpl extends BaseService<StockRecord> implements StockRecordService {

    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public List<StockRecord> queryList(StockRecord param) {
        return stockRecordMapper.queryList(param);
    }
}
