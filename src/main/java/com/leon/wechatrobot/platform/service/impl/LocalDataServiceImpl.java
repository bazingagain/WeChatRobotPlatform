package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.bean.QAPairBean;
import com.leon.wechatrobot.platform.dao.QAPairDao;
import com.leon.wechatrobot.platform.service.LocalDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class LocalDataServiceImpl implements LocalDataService{

    @Autowired
    private QAPairDao qaPairDao;

    @Override
    public QAPairBean getQAPair(Integer id) {
        return qaPairDao.getByPrimaryKey(id);
    }

    @Override
    public List<QAPairBean> listQAPair(String category) {
        //TODO
        return null;
    }
}
