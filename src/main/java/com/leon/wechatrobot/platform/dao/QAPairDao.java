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
    int deleteByPrimaryKey(Integer id);

    int insert(QAPairBean record);

    int insertSelective(QAPairBean record);

    QAPairBean getByPrimaryKey(Integer id);

    List<QAPairBean> listByPrimaryKeys(List<Integer> ids);

    int updateByPrimaryKeySelective(QAPairBean record);

    int updateByPrimaryKey(QAPairBean record);
}
