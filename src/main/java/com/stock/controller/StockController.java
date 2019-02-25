package com.stock.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stock.domain.Stock;
import com.stock.domain.StockRecord;
import com.stock.domain.base.BackCode;
import com.stock.domain.base.Page;
import com.stock.domain.base.Val;
import com.stock.service.StockRecordService;
import com.stock.service.StockService;
import com.stock.util.FieldUtils;
import com.stock.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRecordService stockRecordService;

    @PostMapping(value = "/getList")
    public String getList(@RequestBody Page page) {
        Val<List<Stock>> val = new Val<>();
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
            String field = Utils.camelToUnderline(page.getField());
            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        //PageHelper.startPage(page, limit);
        List<Stock> list = stockService.queryList(page.getKey());
        if (list != null && list.size() > 0) {
            PageInfo<Stock> info = new PageInfo(list);
            val.setData(list);
            val.setCount(info.getTotal());
            val.setInfo(BackCode.SUCCESS, "成功");
        }else {
            val.setCount(0L);
            val.setInfo(BackCode.FAIL, "暂无数据");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/checkName")
    public String checkName(@RequestBody Stock param, HttpServletRequest request) {
        Val val = new Val<>();
        int count = stockService.checkName(param);
        if (count == 0) {
            val.setInfo(BackCode.SUCCESS, "成功");
        } else {
            val.setInfo(BackCode.FAIL, "该型号已存在");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody Stock param, HttpServletRequest request) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getId() == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName()) || param.getNum() == null) {
            val.setInfo(BackCode.FAIL, "型号和数量不能为空,请重新输入");
        } else {
            //获取当前箱数,如果箱数改动,则保存至库存详情
            Stock stock = stockService.selectByKey(param.getId());
            int num = param.getNum() - stock.getNum();
            if (num != 0) {
                StockRecord record = new StockRecord();
                record.setStockId(stock.getId());
                record.setNum(num);
                record.setType(num> 0 ? FieldUtils.IN_STOCK : FieldUtils.OUT_STOCK);
                record.setCreateTime(new Date());

                stockRecordService.save(record);
            }
            param.setCreateTime(stock.getCreateTime());
            param.setStatus(stock.getStatus());
            if (param.getProduceDay() != null && param.getNowDay() == null) {
                param.setNowDay(1);
            }
            stockService.updateAll(param);
            val.setInfo(BackCode.SUCCESS, "修改成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody Stock param, HttpServletRequest request) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName()) || param.getNum() == null) {
            val.setInfo(BackCode.FAIL, "型号和数量不能为空,请重新输入");
        } else {
            param.setCreateTime(new Date());
            param.setStatus(FieldUtils.ABLE);
            if (param.getProduceDay() != null && param.getNowDay() == null) {
                param.setNowDay(1);
            }
            stockService.save(param);

            //保存库存详情
            StockRecord record = new StockRecord();
            record.setStockId(param.getId());
            record.setNum(param.getNum());
            record.setType(param.getNum() > 0 ? FieldUtils.IN_STOCK : FieldUtils.OUT_STOCK);
            record.setCreateTime(new Date());
            stockRecordService.save(record);

            val.setInfo(BackCode.SUCCESS, "添加成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/getRecordList")
    public String getRecordList(@RequestBody Page page, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Val<List<StockRecord>> val = new Val<>();
        if (page.getId() == null) {
            val.setInfo(BackCode.FAIL, "请返回上级页面");
            return JSON.toJSONString(val);
        }
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
            String field = Utils.camelToUnderline(page.getField());
            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }


        StockRecord stockRecord = new StockRecord();
        stockRecord.setStockId(page.getId());
        List<StockRecord> list = stockRecordService.queryList(stockRecord);
        if (list != null && list.size() > 0) {
            PageInfo<StockRecord> info = new PageInfo(list);
            val.setData(list);
            val.setCount(info.getTotal());
            val.setInfo(BackCode.SUCCESS, "成功");
        }else {
            val.setCount(0L);
            val.setInfo(BackCode.SUCCESS, "暂无数据");
        }
        return JSON.toJSONString(val);
    }
}
