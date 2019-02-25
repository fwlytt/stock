package com.stock.service.impl;

import com.stock.dao.CommonMapper;
import com.stock.domain.base.SelectVal;
import com.stock.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<SelectVal> getStockList() {
        return commonMapper.getStockList();
    }

    @Override
    public List<SelectVal> getCustomerList() {
        return commonMapper.getCustomerList();
    }

    @Override
    public List<SelectVal> getWorkshopList() {
        return commonMapper.getWorkshopList();
    }

    @Override
    public List<SelectVal> getWorkshopListByCustomerId(Integer id) {
        return commonMapper.getWorkshopListByCustomerId(id);
    }
}
