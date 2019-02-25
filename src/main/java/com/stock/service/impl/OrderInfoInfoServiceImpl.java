package com.stock.service.impl;

import com.stock.dao.OrderInfoMapper;
import com.stock.domain.OrderInfo;
import com.stock.domain.val.OrderInfoVal;
import com.stock.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderInfoInfoServiceImpl extends BaseService<OrderInfo> implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public List<OrderInfoVal> queryList(Map<String, String> param) {
        return orderInfoMapper.queryList(param);
    }

    @Override
    public int update(OrderInfo param) {
        return orderInfoMapper.update(param);
    }

    @Override
    public List<OrderInfoVal> queryBillList(Map<String, String> param) {
        return orderInfoMapper.queryBillList(param);
    }

}
