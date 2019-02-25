package com.stock.service;

import com.stock.domain.Workshop;
import com.stock.domain.val.WorkshopVal;

import java.util.List;

public interface WorkshopService extends IService<Workshop> {

    List<WorkshopVal> queryList(String key);

    int checkName(Workshop param);

    List<Workshop> queryInfoList(Integer customerId);
}
