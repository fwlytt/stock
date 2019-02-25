package com.stock.service;

import com.stock.domain.base.SelectVal;

import java.util.List;

public interface CommonService {

    List<SelectVal> getStockList();

    List<SelectVal> getCustomerList();

    List<SelectVal> getWorkshopList();

    List<SelectVal> getWorkshopListByCustomerId(Integer id);
}
