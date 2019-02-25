package com.stock.dao;

import com.stock.domain.OrderInfo;
import com.stock.domain.val.OrderInfoVal;
import com.stock.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface OrderInfoMapper extends MyMapper<OrderInfo> {

    List<OrderInfoVal> queryList(Map<String, String> param);

    int update(OrderInfo param);

    List<OrderInfoVal> queryBillList(Map<String, String> param);
}