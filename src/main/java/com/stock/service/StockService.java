package com.stock.service;

import com.stock.domain.Stock;

import java.util.List;

public interface StockService extends IService<Stock> {

    List<Stock> queryList(String key);

    int checkName(Stock param);

    void produceStock();
}
