package com.stock.service;

import com.stock.domain.Customer;

import java.util.List;

public interface CustomerService extends IService<Customer> {

    List<Customer> queryList(String key);

    int checkName(Customer param);

}
