package com.leon.wechatrobot.platform.model;

import com.hankcs.hanlp.classification.features.IFeatureWeighter;
import com.hankcs.hanlp.classification.models.AbstractModel;

/**
 * Created on 09/04/2018.
 *
 * @author Xiaolei-Peng
 */
public class LinearSVMModel extends AbstractModel {
    /**
     * 训练样本数
     */
    public int n = 0;
    /**
     * 类别数
     */
    public int c = 0;
    /**
     * 特征数
     */
    public int d = 0;
    /**
     * 特征权重计算工具
     */
    public IFeatureWeighter featureWeighter;
    /**
     * SVM分类模型
     */
    public Model svmModel;

}
