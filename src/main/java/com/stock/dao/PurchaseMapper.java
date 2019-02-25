package com.stock.dao;

import com.stock.domain.Purchase;
import com.stock.util.MyMapper;

import java.util.List;

public interface PurchaseMapper extends MyMapper<Purchase> {

    List<Purchase> queryList(Purchase param);
}