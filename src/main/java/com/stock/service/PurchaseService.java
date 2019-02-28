package com.stock.service;

import com.stock.domain.Purchase;

import java.util.List;
import java.util.Map;

public interface PurchaseService extends IService<Purchase> {

    List<Map<String, String>> queryList(Map<String, String> param);

    List<Map<String, String>> queryBillList(Map<String, String> param);

}
