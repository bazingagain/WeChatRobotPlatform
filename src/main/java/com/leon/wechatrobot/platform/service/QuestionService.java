package com.leon.wechatrobot.platform.service;

import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface QuestionService {

    String classify(String question);
    List<String> getKeyword(String question, int n);
    List<String> extendKeyword(String keyword);
    List<String> getAllQuestionCategory();
    List<Term> segmentQuestion(String question, boolean useStopWord);
}
