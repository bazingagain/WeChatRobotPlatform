package com.leon.wechatrobot.platform.util.common;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;

/**
 * Created on 16/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class Config {
    public static WordVectorModel wordVectorModel = null;
    public static DocVectorModel docVectorModel = null;
    public static IClassifier classifier = null;

    static {
        try {
            wordVectorModel = TrainUtil.trainOrLoadWord2VecModel();
            docVectorModel = new DocVectorModel(wordVectorModel);
//            classifier = TrainUtil.trainOrLoadClassifierModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
