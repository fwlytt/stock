package com.stock.dao;

import com.stock.domain.Purchase;
import com.stock.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface PurchaseMapper extends MyMapper<Purchase> {

    List<Map<String, String>> queryList(Map<String, String> param);

    List<Map<String, String>> queryBillList(Map<String, String> param);
}