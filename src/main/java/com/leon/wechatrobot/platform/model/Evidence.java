package com.leon.wechatrobot.platform.model;

import com.hankcs.hanlp.seg.common.Term;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class Evidence {
    private String title;
    private String snippet;
    private String url;
    private int sort;
    private double score = 1.0;
    private CandidateAnswerCollection candidateAnswerCollection;

    @Autowired
    private QuestionService questionService;

    public Evidence(String title, String snippet, String url, int sort) {
        this.title = title;
        this.snippet = snippet;
        this.url = url;
        this.sort = sort;
    }

    public List<String> getTitleWords() {
        List<String> result = new ArrayList<>();
        List<Term> words = questionService.segmentQuestion(title, true);
        for (Term word : words) {
            result.add(word.word);
        }
        return result;
    }

    public List<String> getSnippetWords() {
        List<String> result = new ArrayList<>();
        List<Term> words = questionService.segmentQuestion(snippet, true);
        for (Term word : words) {
            result.add(word.word);
        }
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getScore() {
        return score;
    }

    public void addScore(double score) {
        this.score += score;
    }

    public CandidateAnswerCollection getCandidateAnswerCollection() {
        return candidateAnswerCollection;
    }

    public void setCandidateAnswerCollection(CandidateAnswerCollection candidateAnswerCollection) {
        this.candidateAnswerCollection = candidateAnswerCollection;
    }
}
