package com.stock.dao;

import com.stock.domain.StockRecord;
import com.stock.util.MyMapper;

import java.util.List;

public interface StockRecordMapper extends MyMapper<StockRecord> {

    List<StockRecord> queryList(StockRecord param);
}