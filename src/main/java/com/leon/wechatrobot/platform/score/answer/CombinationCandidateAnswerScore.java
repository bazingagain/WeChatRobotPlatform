package com.leon.wechatrobot.platform.service.impl.answerScore;

import com.leon.wechatrobot.platform.model.CandidateAnswerCollection;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;
import com.leon.wechatrobot.platform.service.CandidateAnswerScoreService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class CombinationCandidateAnswerScore implements CandidateAnswerScoreService {

//    private static final Logger LOG = LoggerFactory.getLogger(CombinationCandidateAnswerScore.class);
    private final List<CandidateAnswerScoreService> candidateAnswerScores = new ArrayList<>();

    //TODO  ??
    private ScoreWeight scoreWeight = new ScoreWeight();


    @Override
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection) {
        for (CandidateAnswerScoreService candidateAnswerScore : candidateAnswerScores) {
            candidateAnswerScore.score(question, evidence, candidateAnswerCollection);
        }
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    public void addCandidateAnswerScore(CandidateAnswerScoreService candidateAnswerScore) {
        candidateAnswerScores.add(candidateAnswerScore);
    }

    public void removeCandidateAnswerScore(CandidateAnswerScoreService candidateAnswerScore) {
        candidateAnswerScores.remove(candidateAnswerScore);
    }

    public void clear() {
        candidateAnswerScores.clear();
    }

}
