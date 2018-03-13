package com.leon.wechatrobot.platform.controller;

import com.leon.wechatrobot.platform.service.AnswerService;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created on 11/02/2018.
 *
 * @author Xiaolei-Peng
 */

@Controller
public class AskController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    public void ask() {
        String question = null;
        String category = questionService.classify(question);
        String answer = answerService.getBestAnswerFromLocal(question, category);
    }
}
