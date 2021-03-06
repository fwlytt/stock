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
import java.util.Map;

@Service
public class PurchaseServiceImpl extends BaseService<Purchase> implements PurchaseService {

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Map<String, String>> queryList(Map<String, String> param) {
        return mapper.queryList(param);
    }

    @Override
    public List<Map<String, String>> queryBillList(Map<String, String> param) {
        return mapper.queryBillList(param);
    }
}
