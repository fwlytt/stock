package com.stock.dao;

import com.stock.domain.Stock;
import com.stock.util.MyMapper;

import java.util.List;

public interface StockMapper extends MyMapper<Stock> {

    List<Stock> queryList(String key);

    int checkName(Stock param);

    List<Stock> queryProduce();

    int updateNowDay();

}