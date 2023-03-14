package com.hayashisama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: MyHistoryOfProgress
 * @ClassName HelloController
 * @Description: Hello控制层组件
 * @Author: HayashiSama
 * @Create: 2023-03-14 16:36
 * @Version 1.0
 **/

@Controller
public class HelloController {

    @RequestMapping("/")
    public String portal() {
        return "index";
    }
}
