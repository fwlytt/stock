package com.stock.service;

import com.stock.domain.Purchase;

import java.util.List;

public interface PurchaseService extends IService<Purchase> {

    List<Purchase> queryList(Purchase param);

}
