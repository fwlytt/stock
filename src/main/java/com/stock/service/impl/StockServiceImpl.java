package com.stock.service.impl;

import com.stock.dao.StockMapper;
import com.stock.dao.StockRecordMapper;
import com.stock.domain.Stock;
import com.stock.domain.StockRecord;
import com.stock.service.StockRecordService;
import com.stock.service.StockService;
import com.stock.util.FieldUtils;
import com.stock.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockServiceImpl extends BaseService<Stock> implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockRecordMapper stockRecordMapper;

    @Override
    public List<Stock> queryList(String key) {
        return stockMapper.queryList(key);
    }

    @Override
    public int checkName(Stock param) {
        return stockMapper.checkName(param);
    }

    @Override
    public void produceStock() {
        List<Stock> list = stockMapper.queryProduce();
        stockMapper.updateNowDay();
        if(list != null && list.size() > 0) {
            StockRecord stockRecord = new StockRecord();
            stockRecord.setType(FieldUtils.IN_STOCK);
            stockRecord.setCreateTime(new Date());
            for (Stock stock : list) {
                stock.setNum(stock.getNum() + stock.getProduceNum());
                stock.setNowDay(1);
                this.updateNotNull(stock);

                stockRecord.setId(null);
                stockRecord.setStockId(stock.getId());
                stockRecord.setNum(stock.getProduceNum());
                stockRecordMapper.insert(stockRecord);
            }
        }
    }
}
