package com.stock.service.impl;

import com.stock.dao.PurchaseMapper;
import com.stock.dao.StockRecordMapper;
import com.stock.domain.Purchase;
import com.stock.domain.StockRecord;
import com.stock.service.PurchaseService;
import com.stock.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl extends BaseService<Purchase> implements PurchaseService {

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> queryList(Purchase param) {
        return mapper.queryList(param);
    }
}
