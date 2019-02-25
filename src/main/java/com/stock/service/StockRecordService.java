package com.stock.service;

import com.stock.domain.StockRecord;

import java.util.List;

public interface StockRecordService extends IService<StockRecord> {

    List<StockRecord> queryList(StockRecord param);

}
