package com.leon.wechatrobot.platform.controller;

import com.leon.wechatrobot.platform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 04/04/2018.
 *
 * @author Xiaolei-Peng
 */

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/list")
    @ResponseBody
    public void list(@RequestParam("page") int page) {

    }
}
