package com.stock.service;

import com.stock.domain.StaffEvent;
import com.stock.domain.StaffInfo;
import com.stock.domain.val.StaffInfoVal;

import java.util.List;
import java.util.Map;

public interface StaffInfoService extends IService<StaffInfo> {

    List<StaffInfoVal> queryList(String key);

    List<StaffEvent> queryEventList(Map<String, Object> map);
}
