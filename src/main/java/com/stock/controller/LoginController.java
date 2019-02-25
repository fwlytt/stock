package com.stock.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.stock.domain.User;
import com.stock.domain.UserInfo;
import com.stock.domain.base.BackCode;
import com.stock.domain.base.Val;
import com.stock.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录操作
     **/
    @PostMapping(value = "/doLogin")
    @ResponseBody
    public String doLogin(@RequestBody UserInfo param, HttpServletRequest request) {
        Val val = new Val();
        if (!StringUtils.isEmpty(param.getId()) && !StringUtils.isEmpty(param.getPassword())) {
            UserInfo user = loginService.selectByKey(param.getId());
            if (user == null) {
                val.setInfo(BackCode.FAIL, "该用户不存在");
            } else if (user.getPassword().equals(param.getPassword())) {
                val.setCode(BackCode.SUCCESS);
                request.getSession().setAttribute("user", param);
                request.getSession().setMaxInactiveInterval(3600 * 6);
                return JSON.toJSONString(val);
            } else {
                val.setInfo(BackCode.FAIL, "用户密码错误");
            }
        } else {
            val.setInfo(BackCode.FAIL, " 请输入账号密码");
        }
        return JSON.toJSONString(val);
    }

    @RequestMapping(value = "/home")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }
}
