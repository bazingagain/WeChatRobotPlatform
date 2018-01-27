package com.leon.wechatrobot.platform.score.answer;

import com.hankcs.hanlp.seg.common.Term;
import com.leon.wechatrobot.platform.model.CandidateAnswer;
import com.leon.wechatrobot.platform.model.CandidateAnswerCollection;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class TermFrequencyCandidateAnswerScore implements CandidateAnswerScore {

    @Autowired
    private QuestionService questionService;

//    private static final Logger LOG = LoggerFactory.getLogger(TermFrequencyCandidateAnswerScore.class);

    private static final int TITLE_WEIGHT = 2;
    private ScoreWeight scoreWeight = new ScoreWeight();
    private Question question;

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    @Override
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection) {
//        LOG.debug("*************************");
//        LOG.debug("词频评分开始");
        this.question = question;
        Map<String, Integer> map = getWordFrequency(evidence.getTitle(), evidence.getSnippet());
        for (CandidateAnswer candidateAnswer : candidateAnswerCollection.getAllCandidateAnswer()) {
            Integer wordFrequency = map.get(candidateAnswer.getAnswer());
            if (wordFrequency == null) {
//                LOG.debug("没有找到候选答案【" + candidateAnswer.getAnswer() + "】的词频信息");
                continue;
            }
            double score = wordFrequency * scoreWeight.getTermFrequencyCandidateAnswerScoreWeight();
//            LOG.debug(candidateAnswer.getAnswer() + " 分值：" + score);
            candidateAnswer.addScore(score);
        }
//        LOG.debug("词频评分结束");
//        LOG.debug("*************************");
    }

    /**
     * 统计文本中出现的候选答案及其词频
     *
     * @return
     */
    private Map<String, Integer> getWordFrequency(String title, String snippet) {
        List<String> titleNames = new ArrayList<>();
        List<String> snippetNames = new ArrayList<>();

        //处理title
        List<Term> words = questionService.segmentQuestion(title, true);
        for (Term word : words) {
            if (word.nature.startsWith(question.getQuestionType().getPos())) {
                titleNames.add(word.word);
            }
        }
        //处理snippet
        words = questionService.segmentQuestion(snippet, true);
        for (Term word : words) {
            if (word.nature.startsWith(question.getQuestionType().getPos())) {
                snippetNames.add(word.word);
            }
        }
        //统计词频
        //title中出现一次算两次
        //snippet中出现一次算一次
        Map<String, Integer> map = new HashMap<>();
        for (String name : titleNames) {
            Integer count = map.get(name);
            if (count == null) {
                count = TITLE_WEIGHT;
            } else {
                count += TITLE_WEIGHT;
            }
            map.put(name, count);
        }
        for (String name : snippetNames) {
            Integer count = map.get(name);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            map.put(name, count);
        }

        return map;
    }

}
