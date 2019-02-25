package com.stock.service.impl;

import com.stock.dao.CustomerMapper;
import com.stock.dao.StockRecordMapper;
import com.stock.domain.Customer;
import com.stock.domain.StockRecord;
import com.stock.service.CustomerService;
import com.stock.service.StockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<Customer> queryList(String key) {
        return customerMapper.queryList(key);
    }

    @Override
    public int checkName(Customer param) {
        return customerMapper.checkName(param);
    }
}
