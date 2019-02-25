package com.stock.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stock.domain.OrderInfo;
import com.stock.domain.Stock;
import com.stock.domain.base.BackCode;
import com.stock.domain.base.Page;
import com.stock.domain.base.SelectVal;
import com.stock.domain.base.Val;
import com.stock.domain.val.OrderInfoVal;
import com.stock.service.CommonService;
import com.stock.service.OrderInfoService;
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
@RequestMapping(value = "/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private CommonService commonService;

    @PostMapping(value = "/getList")
    public String getList(@RequestBody Page page){
        Val<List<OrderInfoVal>> val = new Val<>();
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

        List<OrderInfoVal> list = orderInfoService.queryList(param);
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
    public String update(@RequestBody OrderInfo param){
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getId() == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getOrderDate() == null || StringUtils.isEmpty(param.getType()) ||
                param.getNum() == null || param.getPrice() == null) {
            val.setInfo(BackCode.FAIL, "订单参数不能为空,请重新输入");
        } else {
            orderInfoService.update(param);
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
            OrderInfo param = new OrderInfo();
            param.setId(id);
            param.setStatus(FieldUtils.UNABLE);
            orderInfoService.updateNotNull(param);
            val.setInfo(BackCode.SUCCESS, "成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/getWorkshop")
    public String getWorkshop(@RequestBody Integer id){
        Val<List<SelectVal>> val = new Val<>();
        if (id == null) {
            val.setInfo(BackCode.FAIL, "失败");
        }else {
            List<SelectVal> list = commonService.getWorkshopListByCustomerId(id);
            val.setInfo(BackCode.SUCCESS, "成功");
            val.setData(list);
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody OrderInfo param){
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getOrderDate() == null || StringUtils.isEmpty(param.getType()) ||
                param.getNum() == null || param.getPrice() == null ||
                param.getStockId() == null || param.getCustomerId() == null) {
            val.setInfo(BackCode.FAIL, "订单参数不能为空,请重新输入");
        } else {
            param.setCreateTime(new Date());
            param.setStatus(FieldUtils.ABLE);
            orderInfoService.save(param);
            val.setInfo(BackCode.SUCCESS, "添加成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/getBillList")
    public String getBillList(@RequestBody Page page){
        Val<List<OrderInfoVal>> val = new Val<>();
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

        List<OrderInfoVal> list = orderInfoService.queryBillList(param);
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
