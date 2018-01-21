package com.leon.wechatrobot.platform.model;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class CandicateAnswer {
    //答案上下文
    private String answerContext;
    private double score;

    public CandicateAnswer(String answerContext, double score) {
        this.answerContext = answerContext;
        this.score = score;
    }

    public String getAnswerContext() {
        return answerContext;
    }

    public void setAnswerContext(String answerContext) {
        this.answerContext = answerContext;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
