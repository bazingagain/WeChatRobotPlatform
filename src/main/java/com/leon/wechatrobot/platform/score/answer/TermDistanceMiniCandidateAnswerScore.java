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
import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class TermDistanceMiniCandidateAnswerScore implements CandidateAnswerScore {

//    private static final Logger LOG = LoggerFactory.getLogger(TermDistanceMiniCandidateAnswerScore.class);

    @Autowired
    private QuestionService questionService;

    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection) {
//        LOG.debug("*************************");
//        LOG.debug("最小词距评分开始");
        //1、对问题进行分词
        List<Term> questionWords = questionService.segmentQuestion(question.getQuestion(), true);
        //2、对证据进行分词
        List<Term> evidenceWords = questionService.segmentQuestion(evidence.getTitle() + "," + evidence.getSnippet(), true);
        for (CandidateAnswer candidateAnswer : candidateAnswerCollection.getAllCandidateAnswer()) {
            //3、计算候选答案的词距
            int distance = 0;
//            LOG.debug("计算候选答案 " + candidateAnswer.getAnswer() + " 的最小词距");
            //3.1 计算candidateAnswer的分布
            List<Integer> candidateAnswerOffes = new ArrayList<>();
            for (int i=0; i<evidenceWords.size(); i++) {
                Term evidenceWord = evidenceWords.get(i);
                if (evidenceWord.word.equals(candidateAnswer.getAnswer())) {
                    candidateAnswerOffes.add(i);
                }
            }
            for (Term questionTerm : questionWords) {
                //3.2 计算questionTerm的分布
                List<Integer> questionTermOffes = new ArrayList<>();
                for (int i=0; i<evidenceWords.size(); i++) {
                    Term evidenceWord = evidenceWords.get(i);
                    if (evidenceWord.word.equals(questionTerm.word)) {
                        questionTermOffes.add(i);
                    }
                }
                //3.3 计算candidateAnswer和questionTerm的最小词距
                int miniDistance = Integer.MAX_VALUE;
                for (int candidateAnswerOffe : candidateAnswerOffes) {
                    for (int questionTermOffe : questionTermOffes) {
                        int abs = Math.abs(candidateAnswerOffe - questionTermOffe);
                        if (miniDistance > abs) {
                            miniDistance = abs;
                        }
                    }
                }
                if (miniDistance != Integer.MAX_VALUE) {
                    distance += miniDistance;
                }
            }
            double score = candidateAnswer.getScore() / distance;
            score *= scoreWeight.getTermDistanceMiniCandidateAnswerScoreWeight();
//            LOG.debug("最小词距:" + distance + " ,分值：" + score);
            candidateAnswer.addScore(score);
        }
//        LOG.debug("最小词距评分结束");
//        LOG.debug("*************************");
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}
