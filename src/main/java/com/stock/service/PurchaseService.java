package com.stock.service;

import com.stock.domain.Purchase;

import java.util.List;
import java.util.Map;

public interface PurchaseService extends IService<Purchase> {

    List<Purchase> queryList(Map<String, String> param);

    List<Purchase> queryBillList(Map<String, String> param);

}
