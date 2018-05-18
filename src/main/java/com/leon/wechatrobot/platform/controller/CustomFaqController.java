package com.leon.wechatrobot.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 04/04/2018.
 *
 * @author Xiaolei-Peng
 */

@RequestMapping("/qa")
@Controller
public class CustomFaqController {

    @RequestMapping("/listCustomFaq")
    @ResponseBody
    public void listCustomFaq(@RequestParam("page") String pageNum) {
        
    }
}
