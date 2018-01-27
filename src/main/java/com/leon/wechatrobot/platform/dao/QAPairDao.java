package com.leon.wechatrobot.platform.dao;

import com.leon.wechatrobot.platform.bean.QAPairBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 30/11/2017.
 *
 * @author Xiaolei-Peng
 */
@Repository
public interface QAPairDao {

    QAPairBean getQAPairById(Integer id);

    List<QAPairBean> listQAPairByCategory(String category);

}
