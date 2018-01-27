package com.leon.wechatrobot.platform.score.evidence;

import com.hankcs.hanlp.seg.common.Term;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class TermMatchEvidenceScore implements EvidenceScore {

    @Autowired
    private QuestionService questionService;

    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public void score(Question question, Evidence evidence) {
//        LOG.debug("*************************");
//        LOG.debug("Evidence TermMatch评分开始");
        //1、对问题进行分词
        List<Term> questionTerms = questionService.segmentQuestion(question.getQuestion(), true);
//        LOG.debug("questionTerms:" + questionTerms);
        //2、对证据进行分词
        List<String> titleTerms = evidence.getTitleWords();
        List<String> snippetTerms = evidence.getSnippetWords();
//        LOG.debug("titleTerms:" + titleTerms);
//        LOG.debug("snippetTerms:" + snippetTerms);
        //3、不管语法关系或词序，直接对问题和证据的词进行匹配
        //对于问题中的词，在evidence中出现一次记一分
        double score = 0;
        for (Term questionTerm : questionTerms) {
            //忽略问题中长度为1的词
            if (questionTerm.length() < 2) {
//                LOG.debug("忽略问题中长度为1的词:" + questionTerm);
                continue;
            }
            int idf = questionTerm.getFrequency(); //从HanLP的词典中获取词频
            if (idf > 0) {
                idf = 1 / idf;
            } else {
                idf = 1;
            }
            for (String titleTerm : titleTerms) {
                if (questionTerm.word.equals(titleTerm)) {
//                    LOG.debug("title match: " + questionTerm + " " + titleTerm);
                    score += idf * 2;
                }
            }
            for (String snippetTerm : snippetTerms) {
                if (questionTerm.word.equals(snippetTerm)) {
//                    LOG.debug("snippet match: " + questionTerm + " " + snippetTerm);
                    score += idf;
                }
            }
        }
        score *= scoreWeight.getTermMatchEvidenceScoreWeight();
//        LOG.debug("Evidence TermMatch评分:" + score);
        evidence.addScore(score);
//        LOG.debug("Evidence TermMatch评分结束");
//        LOG.debug("*************************");

    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}
