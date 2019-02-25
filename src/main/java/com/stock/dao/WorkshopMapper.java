package com.stock.dao;

import com.stock.domain.Workshop;
import com.stock.domain.val.WorkshopVal;
import com.stock.util.MyMapper;

import java.util.List;

public interface WorkshopMapper extends MyMapper<Workshop> {

    List<WorkshopVal> queryList(String key);

    int checkName(Workshop param);

    List<Workshop> queryInfoList(Integer customerId);
}