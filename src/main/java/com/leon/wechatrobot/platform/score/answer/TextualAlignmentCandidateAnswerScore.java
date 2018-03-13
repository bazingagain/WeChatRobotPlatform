package com.leon.wechatrobot.platform.service.impl.answerScore;

import com.hankcs.hanlp.seg.common.Term;
import com.leon.wechatrobot.platform.model.CandidateAnswer;
import com.leon.wechatrobot.platform.model.CandidateAnswerCollection;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;
import com.leon.wechatrobot.platform.service.CandidateAnswerScoreService;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class TextualAlignmentCandidateAnswerScore implements CandidateAnswerScoreService {

    @Autowired
    private QuestionService questionService;

//    private static final Logger LOG = LoggerFactory.getLogger(TextualAlignmentCandidateAnswerScore.class);
    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    protected List<Term> getQuestionTerms(Question question) {
        return questionService.segmentQuestion(question.getQuestion(), true);
    }


    @Override
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection) {
//        LOG.debug("*************************");
//        LOG.debug("文本对齐评分开始");
        //1、对问题进行分词
        List<Term> questionTerms = getQuestionTerms(question);
        int questionTermsSize = questionTerms.size();
        //2、获取证据文本
        String evidenceText = evidence.getTitle() + evidence.getSnippet();
        //将每一个候选答案都放到问题的每一个位置，查找在证据中是否有匹配文本
        for (CandidateAnswer candidateAnswer : candidateAnswerCollection.getAllCandidateAnswer()) {
            //3、计算候选答案的文本对齐
//            LOG.debug("计算候选答案 " + candidateAnswer.getAnswer() + " 的文本对齐");
            for (int i = 0; i < questionTermsSize; i++) {
                StringBuilder textualAlignment = new StringBuilder();
                for (int j = 0; j < questionTermsSize; j++) {
                    if (i == j) {
                        textualAlignment.append(candidateAnswer.getAnswer());
                    } else {
                        textualAlignment.append(questionTerms.get(j));
                    }
                }
                String textualAlignmentPattern = textualAlignment.toString();
                if (question.getQuestion().trim().equals(textualAlignmentPattern.trim())) {
//                    LOG.debug("文本对齐模式和原问题相同，忽略：" + textualAlignmentPattern);
                    continue;
                }
                //4、演化为多个模式，支持模糊匹配
                List<Term> textualAlignmentPatternTerms = questionService.segmentQuestion(textualAlignmentPattern, true);
                List<String> patterns = new ArrayList<>();
                patterns.add(textualAlignmentPattern);
                StringBuilder str = new StringBuilder();
                int len = textualAlignmentPatternTerms.size();
                for (int t = 0; t < len; t++) {
                    str.append(textualAlignmentPatternTerms.get(t).word);
                    if (t < len - 1) {
                        str.append(".{0,5}");
                    }
                }
                patterns.add(str.toString());
                //5、判断文本是否对齐
                int count = 0;
                int length = 0;
                for (String pattern : patterns) {
                    //LOG.debug("模式："+pattern);
                    Pattern p = Pattern.compile(pattern);
                    Matcher matcher = p.matcher(evidenceText);
                    while (matcher.find()) {
                        String text = matcher.group();
//                        LOG.debug("对齐的文本：" + text);
//                        LOG.debug("对齐的模式：" + pattern);
                        count++;
                        length += text.length();
                    }
                }
                //6、打分
                if (count > 0) {
                    double avgLen = (double) length / count;
                    //问题长度questionLen为正因子
                    //匹配长度avgLen为负因子
                    int questionLen = question.getQuestion().length();
                    double score = questionLen / avgLen;
                    score *= scoreWeight.getTextualAlignmentCandidateAnswerScoreWeight();
                    candidateAnswer.addScore(score);
//                    LOG.debug("文本对齐 " + count + " 次,分值：" + score);
                }
            }
        }
//        LOG.debug("文本对齐评分结束");
//        LOG.debug("*************************");
    }

}
