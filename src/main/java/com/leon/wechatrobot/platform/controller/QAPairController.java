package com.leon.wechatrobot.platform.controller;

import com.leon.wechatrobot.platform.bean.QAPairBean;
import com.leon.wechatrobot.platform.service.LocalDataService;
import com.leon.wechatrobot.platform.service.QAPairBeanService;
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
@RequestMapping("/qapair")
@Controller
public class QAPairController {
    
    @Autowired
    private LocalDataService localDataService;
    
    @RequestMapping("/list")
    @ResponseBody
    public void list(@RequestParam("page") int page) {
        localDataService.listAllQAPair(page, 10);
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(QAPairBean qapairBean) {
        localDataService.insertSelective(qapairBean);
    }

    @RequestMapping("/update")
    @ResponseBody
    public void update(QAPairBean qapairBean) {
        localDataService.updateByPrimaryKeySelective(qapairBean);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(Integer id) {
        localDataService.deleteByPrimaryKey(id);
    }


}
