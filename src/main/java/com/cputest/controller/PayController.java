package com.cputest.controller;


import com.cputest.annotation.Controller;
import com.cputest.annotation.RequestMapping;

@Controller
public class PayController {


    @RequestMapping("/pay")
    public String pay()  {
        return "pay";
    }
}
