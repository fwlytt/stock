package com.stock.dao;

import com.stock.domain.base.SelectVal;

import java.util.List;

public interface CommonMapper {

    List<SelectVal> getStockList();

    List<SelectVal> getCustomerList();

    List<SelectVal> getWorkshopList();

    List<SelectVal> getWorkshopListByCustomerId(Integer id);

}