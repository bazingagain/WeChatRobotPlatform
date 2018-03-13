package com.leon.wechatrobot.platform.service.impl.answerScore;

import com.hankcs.hanlp.seg.common.Term;
import com.leon.wechatrobot.platform.model.CandidateAnswer;
import com.leon.wechatrobot.platform.model.CandidateAnswerCollection;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;
import com.leon.wechatrobot.platform.service.CandidateAnswerScoreService;
import com.leon.wechatrobot.platform.service.QuestionService;
import com.leon.wechatrobot.platform.util.common.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class HotCandidateAnswerScore implements CandidateAnswerScoreService {

    @Autowired
    private QuestionService questionService;

//    private static final Logger LOG = LoggerFactory.getLogger(HotCandidateAnswerScore.class);
    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection) {
        {
//            LOG.debug("*************************");
//            LOG.debug("热词评分开始");
            CandidateAnswer bestCandidateAnswer = null;
            int miniDistance = Integer.MAX_VALUE;
            //1、对证据进行分词
            List<Term> evidenceWords = questionService.segmentQuestion(evidence.getTitle() + "," + evidence.getSnippet(), true);
            //2、找出热词
            Map.Entry<String, Integer> hot = this.getHot(question);
            if (hot == null) {
//                LOG.debug("热词评分失败，未能找出热词");
                return;
            }
//            LOG.debug("热词：" + hot.getKey() + " " + hot.getValue());
            //3、找出热词的位置数组
            List<Integer> hotTermOffes = new ArrayList<>();
            for (int i=0; i<evidenceWords.size(); i++) {
                Term evidenceWord = evidenceWords.get(i);
                if (evidenceWord.word.equals(hot.getKey())) {
                    hotTermOffes.add(i);
                }
            }
            for (CandidateAnswer candidateAnswer : candidateAnswerCollection.getAllCandidateAnswer()) {
                //4、找出候选答案的位置数组
                List<Integer> candidateAnswerOffes = new ArrayList<>();
                for (int i=0; i<evidenceWords.size(); i++) {
                    Term evidenceWord = evidenceWords.get(i);
                    if (evidenceWord.word.equals(candidateAnswer.getAnswer())) {
                        candidateAnswerOffes.add(i);
                    }
                }
                //5、计算热词和候选答案的最近距离
                for (int candidateAnswerOffe : candidateAnswerOffes) {
                    for (int hotTermOffe : hotTermOffes) {
                        int abs = Math.abs(candidateAnswerOffe - hotTermOffe);
                        if (miniDistance > abs) {
                            miniDistance = abs;
                            bestCandidateAnswer = candidateAnswer;
                        }
                    }
                }
            }
            if (bestCandidateAnswer != null && miniDistance > 0) {
//                LOG.debug("miniDistance:" + miniDistance);
                double score = bestCandidateAnswer.getScore();
                score *= scoreWeight.getHotCandidateAnswerScoreWeight();
//                LOG.debug("候选答案 " + bestCandidateAnswer.getAnswer() + " 分值：" + score);
                bestCandidateAnswer.addScore(score);
            } else {
//                LOG.debug("没有最佳候选答案");
            }
//            LOG.debug("热词评分结束");
//            LOG.debug("*************************");
        }
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    private Map.Entry<String, Integer> getHot(Question question) {
        List<Term> questionWords = questionService.segmentQuestion(question.getQuestion(), true);
        Map<String, Integer> map = new HashMap<>();
        List<Term> words = questionService.segmentQuestion(this.getText(question.getEvidences()), true);
        for (Term word : words) {
            Integer count = map.get(word.word);
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            map.put(word.word, count);
        }
        Map<String, Integer> questionMap = new HashMap<>();
        for (Term questionWord : questionWords) {
            Integer count = map.get(questionWord.word);
            if (questionWord.length() > 1 && count != null) {
                questionMap.put(questionWord.word, count);
//                LOG.debug("问题热词统计: " + questionWord + " " + map.get(questionWord));
            }
        }
        List<Map.Entry<String, Integer>> list = SortUtils.sortByIntegerValue(questionMap);
        Collections.reverse(list);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    private String getText(List<Evidence> evidences) {
        StringBuilder text = new StringBuilder();
        for (Evidence evidence : evidences) {
            text.append(evidence.getTitle()).append(evidence.getSnippet());
        }
        return text.toString();
    }


}
