package com.stock.dao;

import com.stock.domain.StaffEvent;
import com.stock.domain.StaffInfo;
import com.stock.domain.Stock;
import com.stock.domain.val.StaffInfoVal;
import com.stock.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface StaffInfoMapper extends MyMapper<StaffInfo> {

    List<StaffInfoVal> queryList(String key);

    List<StaffEvent> queryEventList(Map<String, Object> map);

}