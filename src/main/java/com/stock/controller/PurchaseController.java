package com.stock.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stock.domain.OrderInfo;
import com.stock.domain.Purchase;
import com.stock.domain.base.BackCode;
import com.stock.domain.base.Page;
import com.stock.domain.base.SelectVal;
import com.stock.domain.base.Val;
import com.stock.domain.val.OrderInfoVal;
import com.stock.service.PurchaseService;
import com.stock.util.FieldUtils;
import com.stock.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping(value = "/getList")
    public String getList(@RequestBody Page page){
        Val<List<Purchase>> val = new Val<>();
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            String field = page.getField();
            if (!"totalPrice".equals(page.getField())){
                //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
                field = Utils.camelToUnderline(page.getField());
            }

            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        //搜索用
        Map<String, String> param = new HashMap<>();
        param.put("key",page.getKey());
        if (page.getMap() != null) {
            param.putAll(page.getMap());
        }
        if (!StringUtils.isEmpty(page.getTime())) {
            param.putAll(Utils.getDateByMonth(page.getTime()));
        }

        List<Purchase> list = purchaseService.queryList(param);
        if (list != null && list.size() > 0) {
            PageInfo<OrderInfoVal> info = new PageInfo(list);
            val.setData(list);
            val.setCount(info.getTotal());
            val.setInfo(BackCode.SUCCESS, "成功");
        }else {
            val.setCount(0L);
            val.setInfo(BackCode.FAIL, "暂无数据");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody Purchase param){
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getId() == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getChannel()) || StringUtils.isEmpty(param.getPurchaseName()) ||
                param.getNum() == null || param.getPrice() == null || param.getPurchaseTime() == null) {
            val.setInfo(BackCode.FAIL, "进货数据不能为空,请重新输入");
        } else {
            Purchase purchase = purchaseService.selectByKey(param.getId());
            param.setCreateTime(purchase.getCreateTime());
            purchaseService.updateAll(param);
            val.setInfo(BackCode.SUCCESS, "修改成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestBody Integer id) {
        Val val = new Val();
        if (id == null) {
            val.setInfo(BackCode.FAIL, "数据异常，请刷新后再试");
        } else {
            Purchase param = new Purchase();
            param.setId(id);
            param.setStatus(FieldUtils.UNABLE);
            purchaseService.updateNotNull(param);
            val.setInfo(BackCode.SUCCESS, "成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody Purchase param){
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getPurchaseTime() == null || StringUtils.isEmpty(param.getPurchaseName()) ||
                param.getNum() == null || param.getPrice() == null ||
                StringUtils.isEmpty(param.getChannel())) {
            val.setInfo(BackCode.FAIL, "进货参数不能为空,请重新输入");
        } else {
            param.setCreateTime(new Date());
            param.setStatus(FieldUtils.ABLE);
            purchaseService.save(param);
            val.setInfo(BackCode.SUCCESS, "添加成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/getBillList")
    public String getBillList(@RequestBody Page page){
        Val<List<Purchase>> val = new Val<>();
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            String field = page.getField();
            if (!"totalPrice".equals(page.getField())){
                //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
                field = Utils.camelToUnderline(page.getField());
            }
            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        //搜索用
        Map<String, String> param = new HashMap<>();
        param.put("key",page.getKey());
        if (page.getMap() != null) {
            param.putAll(page.getMap());
        }
        if (!StringUtils.isEmpty(page.getTime())) {
            param.putAll(Utils.getDateByMonth(page.getTime()));
        }

        List<Purchase> list = purchaseService.queryList(param);
        if (list != null && list.size() > 0) {
            PageInfo<OrderInfoVal> info = new PageInfo(list);
            val.setData(list);
            val.setCount(info.getTotal());
            val.setInfo(BackCode.SUCCESS, "成功");
        }else {
            val.setCount(0L);
            val.setInfo(BackCode.FAIL, "暂无数据");
        }
        return JSON.toJSONString(val);
    }
}
