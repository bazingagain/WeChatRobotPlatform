package com.leon.wechatrobot.platform.service.impl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.leon.wechatrobot.platform.service.QuestionService;
import com.leon.wechatrobot.platform.util.common.TrainUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class QuestionServiceImpl implements QuestionService{

    @Override
    public String classify(String question) {
        try {
            //TODO 将分类器改为单例
            IClassifier classifier = TrainUtil.trainOrLoadClassifierModel();
            return classifier.classify(question);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getKeyword(String question, int n) {
        return HanLP.extractKeyword(question, n);
    }

    @Override
    public List<String> extendKeyword(String keyword) {
        //TODO  通过同义词词林
        return null;
    }

    @Override
    public List<String> getAllQuestionCategory() {
        return null;
    }
}
