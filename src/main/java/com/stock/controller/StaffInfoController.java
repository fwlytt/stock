package com.stock.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stock.domain.*;
import com.stock.domain.base.BackCode;
import com.stock.domain.base.Page;
import com.stock.domain.base.Val;
import com.stock.domain.val.StaffInfoVal;
import com.stock.service.StaffEventService;
import com.stock.service.StaffInfoService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/staffInfo")
public class StaffInfoController {

    @Autowired
    private StaffInfoService staffInfoService;

    @Autowired
    private StaffEventService staffEventService;

    @PostMapping(value = "/getList")
    public String getList(@RequestBody Page page) {
        Val<List<StaffInfoVal>> val = new Val<>();
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            String field = page.getField();
            if (!"startDate".equals(field) && !"restDays".equals(field)) {
                //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
                field = Utils.camelToUnderline(field);
            }
            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        List<StaffInfoVal> list = staffInfoService.queryList(page.getKey());
        if (list != null && list.size() > 0) {
            PageInfo<StaffInfoVal> info = new PageInfo(list);
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
    public String update(@RequestBody StaffInfo param, HttpServletRequest request) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getId() == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName())) {
            val.setInfo(BackCode.FAIL, "姓名不能为空,请重新输入");
        } else {
            staffInfoService.updateNotNull(param);
            val.setInfo(BackCode.SUCCESS, "成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/insert")
    public String insert(@RequestBody StaffInfo param, HttpServletRequest request) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getName())) {
            val.setInfo(BackCode.FAIL, "姓名不能为空,请重新输入");
        } else {
            param.setCreateTime(new Date());
            param.setStatus(FieldUtils.ABLE);

            staffInfoService.save(param);

            val.setInfo(BackCode.SUCCESS, "添加成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestBody Integer id) {
        Val val = new Val<>();
        if (id == null) {
            val.setInfo(BackCode.FAIL, "数据异常，请刷新后再试");
        } else {
            StaffInfo param = new StaffInfo();
            param.setId(id);
            param.setStatus(FieldUtils.UNABLE);
            staffInfoService.updateNotNull(param);
            val.setInfo(BackCode.SUCCESS, "成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/getEventList")
    public String getEventList(@RequestBody Page page) {
        Val<List<StaffEvent>> val = new Val<>();
        if (page.getId() == null) {
            val.setInfo(BackCode.FAIL, "请返回上级页面");
            return JSON.toJSONString(val);
        }
        if (StringUtils.isEmpty(page.getField())) {
            PageHelper.startPage(page.getPage(), page.getLimit());
        } else {
            String field = page.getField();
            //由于排序字段直接拼接至sql上,所以需要把驼峰转化为下划线
            field = Utils.camelToUnderline(field);

            PageHelper.startPage(page.getPage(), page.getLimit(),
                    field + (page.getOrder() == null?"":" " +page.getOrder()));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("staffId", page.getId());
        map.put("eventYear", page.getTime());
        List<StaffEvent> list = staffInfoService.queryEventList(map);
        if (list != null && list.size() > 0) {
            PageInfo<StaffEvent> info = new PageInfo(list);
            val.setData(list);
            val.setCount(info.getTotal());
            val.setInfo(BackCode.SUCCESS, "成功");
        }else {
            val.setCount(0L);
            val.setInfo(BackCode.FAIL, "暂无数据");
        }
        return JSON.toJSONString(val);
    }


    @PostMapping(value = "/updateEvent")
    public String updateEvent(@RequestBody StaffEvent param, HttpServletRequest request) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (param.getId() == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getType()) || StringUtils.isEmpty(param.getEventDate())) {
            val.setInfo(BackCode.FAIL, "内容和日期不能为空,请重新输入");
        } else {
            StaffEvent record = staffEventService.selectByKey(param.getId());

            param.setStaffId(record.getStaffId());
            param.setCreateTime(record.getCreateTime());

            staffEventService.updateAll(param);
            val.setInfo(BackCode.SUCCESS, "成功");
        }
        return JSON.toJSONString(val);
    }

    @PostMapping(value = "/insertEvent")
    public String insertEvent(@RequestBody StaffEvent param, HttpServletRequest request) {
        Val val = new Val<>();
        if (param == null) {
            val.setInfo(BackCode.FAIL, "数据异常,请刷新后再试");
        }else if (StringUtils.isEmpty(param.getType()) || StringUtils.isEmpty(param.getEventDate())) {
            val.setInfo(BackCode.FAIL, "内容和日期不能为空,请重新输入");
        } else {
            param.setCreateTime(new Date());

            staffEventService.save(param);

            val.setInfo(BackCode.SUCCESS, "添加成功");
        }
        return JSON.toJSONString(val);
    }

}
