package com.leon.wechatrobot.platform.bean;

/**
 * Created on 30/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class QAPairBean {

    private Integer id;
    private String question;
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}