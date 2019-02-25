package com.stock.dao;

import com.stock.domain.Customer;
import com.stock.util.MyMapper;

import java.util.List;

public interface CustomerMapper extends MyMapper<Customer> {

    List<Customer> queryList(String key);

    int checkName(Customer param);
}