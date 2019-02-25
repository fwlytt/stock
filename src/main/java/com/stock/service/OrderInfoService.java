package com.stock.service;

import com.stock.domain.OrderInfo;
import com.stock.domain.val.OrderInfoVal;

import java.util.List;
import java.util.Map;

public interface OrderInfoService extends IService<OrderInfo> {

    List<OrderInfoVal> queryList(Map<String, String> param);

    int update(OrderInfo param);

    List<OrderInfoVal> queryBillList(Map<String, String> param);
}
