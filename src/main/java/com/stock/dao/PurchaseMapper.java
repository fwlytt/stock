package com.stock.dao;

import com.stock.domain.Purchase;
import com.stock.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface PurchaseMapper extends MyMapper<Purchase> {

    List<Purchase> queryList(Map<String, String> param);

    List<Purchase> queryBillList(Map<String, String> param);
}