package com.leon.wechatrobot.platform.model;

import com.hankcs.hanlp.seg.common.Term;
import com.sun.tools.javac.util.List;

import java.util.ArrayList;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class Question {
    private String question;
    private List<Term> wordList;
    private QuestionType questionType;
    private final java.util.List<Evidence> evidences = new ArrayList<>();

    public List<Term> getWordList() {
        return wordList;
    }

    public void setWordList(List<Term> wordList) {
        this.wordList = wordList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public java.util.List<Evidence> getEvidences() {
        return this.evidences;
    }

    public void addEvidences(java.util.List<Evidence> evidences) {
        this.evidences.addAll(evidences);
    }

    public void addEvidence(Evidence evidence) {
        this.evidences.add(evidence);
    }

    public void removeEvidence(Evidence evidence) {
        this.evidences.remove(evidence);
    }
}
