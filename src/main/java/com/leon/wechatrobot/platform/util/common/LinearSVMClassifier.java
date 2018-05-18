package com.leon.wechatrobot.platform.util.common;

import com.hankcs.hanlp.classification.classifiers.AbstractClassifier;
import com.hankcs.hanlp.classification.corpus.Document;
import com.hankcs.hanlp.classification.corpus.IDataSet;
import com.hankcs.hanlp.classification.models.AbstractModel;

import java.util.Map;

/**
 * Created on 09/04/2018.
 *
 * @author Xiaolei-Peng
 */
public class LinearSVMClassifier extends AbstractClassifier {

    @Override
    public Map<String, Double> predict(String text) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public double[] categorize(Document document) throws IllegalArgumentException, IllegalStateException {
        return new double[0];
    }

    @Override
    public void train(IDataSet dataSet) throws IllegalArgumentException {

    }

    @Override
    public AbstractModel getModel() {
        return null;
    }
}
