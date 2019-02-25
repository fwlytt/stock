package com.stock.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stock.domain.Customer;
import com.stock.domain.Workshop;
import com.stock.domain.val.WorkshopVal;
import com.stock.domain.base.BackCode;
import com.stock.domain.base.Page;
import com.stock.domain.base.Val;
import com.stock.service.CustomerService;
import com.stock.service.WorkshopService;
import com.stock.util.FieldUtils;
import com.stock.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private WorkshopService workshopService;

    @PostMapping(value = "/getList")
    public String getList(@RequestBody Page page) {
        Val<List<Customer>> val = new Val<>();
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
            String field = Utils.camelToUnderline(page.getField());
            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        List<Customer> list = customerService.queryList(page.getKey());
        if (list != null && list.size() > 0) {
            PageInfo<Customer> info = new PageInfo(list);
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
    public String checkName(@RequestBody Customer param, HttpServletRequest request) {
        Val val = new Val<>();
        int count = customerService.checkName(param);
        if (count == 0) {
            val.setInfo(BackCode.SUCCESS, "成功");
        } else {
            val.setInfo(BackCode.FAIL, "该客户已存在");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/update")
    public String update(@RequestBody Customer param) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getId() == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName())) {
            val.setInfo(BackCode.FAIL, "客户名不能为空,请重新输入");
        } else {
            Customer customer = customerService.selectByKey(param.getId());
            param.setCreateTime(customer.getCreateTime());
            param.setStatus(customer.getStatus());
            customerService.updateAll(param);
            val.setInfo(BackCode.SUCCESS, "修改成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody Customer param, HttpServletRequest request) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName())) {
            val.setInfo(BackCode.FAIL, "客户名不能为空,请重新输入");
        } else {
            param.setCreateTime(new Date());
            param.setStatus(FieldUtils.ABLE);
            customerService.save(param);
            val.setInfo(BackCode.SUCCESS, "添加成功");
        }
        return JSON.toJSONString(val);
    }


    /* *************************************  加工坊 ************************************* */

    @PostMapping(value = "/workshop/getList")
    public String workshopGetList(@RequestBody Page page) {
        Val<List<WorkshopVal>> val = new Val<>();
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
            String field = Utils.camelToUnderline(page.getField());
            if ("customer_name".equals(field)) {
                field = "customer_id";
            }
            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        List<WorkshopVal> list = workshopService.queryList(page.getKey());
        if (list != null && list.size() > 0) {
            PageInfo<Workshop> info = new PageInfo(list);
            val.setData(list);
            val.setCount(info.getTotal());
            val.setInfo(BackCode.SUCCESS, "成功");
        }else {
            val.setCount(0L);
            val.setInfo(BackCode.FAIL, "暂无数据");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/workshop/checkName")
    public String workshopCheckName(@RequestBody Workshop param) {
        Val val = new Val<>();
        int count = workshopService.checkName(param);
        if (count == 0) {
            val.setInfo(BackCode.SUCCESS, "成功");
        } else {
            val.setInfo(BackCode.FAIL, "该加工坊已存在");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/workshop/update")
    public String workshopUpdate(@RequestBody Workshop param) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getId() == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName())) {
            val.setInfo(BackCode.FAIL, "加工坊名不能为空,请重新输入");
        } else {
            Workshop workshop = workshopService.selectByKey(param.getId());
            param.setCustomerId(workshop.getCustomerId());
            param.setCreateTime(workshop.getCreateTime());
            param.setStatus(workshop.getStatus());
            workshopService.updateAll(param);
            val.setInfo(BackCode.SUCCESS, "修改成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/workshop/insert")
    public String workshopInsert(@RequestBody Workshop param) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName()) || param.getCustomerId() == null) {
            val.setInfo(BackCode.FAIL, "加工坊名和所属客户不能为空,请重新输入");
        } else {
            param.setCreateTime(new Date());
            param.setStatus(FieldUtils.ABLE);
            workshopService.save(param);
            val.setInfo(BackCode.SUCCESS, "添加成功");
        }
        return JSON.toJSONString(val);
    }

    /* *************************************  详情页 ************************************* */

    @PostMapping(value = "/getInfoWorkshopList")
    public String getInfoWorkshopList(@RequestBody Page page) {
        Val<List<Workshop>> val = new Val<>();
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
            String field = Utils.camelToUnderline(page.getField());

            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        List<Workshop> list = workshopService.queryInfoList(page.getId());
        if (list != null && list.size() > 0) {
            PageInfo<Workshop> info = new PageInfo(list);
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
