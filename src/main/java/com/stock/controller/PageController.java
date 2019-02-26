package com.stock.controller;

import com.stock.domain.base.SelectVal;
import com.stock.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "pages/{page}")
    public String pages(@PathVariable String page){
        return "pages/" + page;
    }

    @RequestMapping(value = "pages/customer/{page}")
    public String pagesCustomer(@PathVariable String page, Model model) {
        if ("workshopList.html".equals(page)) {
            List<SelectVal> customers = commonService.getCustomerList();
            model.addAttribute("customers",customers);
        } else if ("info_bill.html".equals(page)) {
            List<SelectVal> stocks = commonService.getStockList();
            model.addAttribute("stocks",stocks);
        }
        return "pages/customer/" + page;
    }

    @RequestMapping(value = "pages/orderInfo/{page}")
    public String pagesOrder(@PathVariable String page, Model model){
        if ("list.html".equals(page)) {
            List<SelectVal> stocks = commonService.getStockList();
            List<SelectVal> customers = commonService.getCustomerList();
            model.addAttribute("stocks",stocks);
            model.addAttribute("customers",customers);
        }
        return "pages/orderInfo/" + page;
    }

    @RequestMapping(value = "pages/purchase/{page}")
    public String pagesPurchase(@PathVariable String page, Model model){
        return "pages/purchase/" + page;
    }

    @RequestMapping(value = "pages/stock/{page}")
    public String pagesStock(@PathVariable String page){
        return "pages/stock/" + page;
    }

    @RequestMapping(value = "pages/views/{page}")
    public String pagesViews(@PathVariable String page){
        return "pages/views/" + page;
    }

    @RequestMapping(value = "pages/views/{content}/{page}")
    public String pagesViews(@PathVariable String content, @PathVariable String page){
        return "pages/views/" +content +"/" + page;
    }
}
