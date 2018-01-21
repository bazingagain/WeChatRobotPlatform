package com.leon.wechatrobot.platform.service;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface AnswerService {
    String getAnswer(String question);
    String getBestAnswerFromLocal(String question, String category);
    String getBestAnswerFromWeb(String question, String category);
    String getCandicateAnswerFromWeb(String question);
}
