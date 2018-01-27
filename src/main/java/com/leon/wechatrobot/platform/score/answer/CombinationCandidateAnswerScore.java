package com.leon.wechatrobot.platform.score.answer;

import com.leon.wechatrobot.platform.model.CandidateAnswerCollection;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class CombinationCandidateAnswerScore implements CandidateAnswerScore {

//    private static final Logger LOG = LoggerFactory.getLogger(CombinationCandidateAnswerScore.class);
    private final List<CandidateAnswerScore> candidateAnswerScores = new ArrayList<>();

    //TODO  ??
    private ScoreWeight scoreWeight = new ScoreWeight();


    @Override
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection) {
        for (CandidateAnswerScore candidateAnswerScore : candidateAnswerScores) {
            candidateAnswerScore.score(question, evidence, candidateAnswerCollection);
        }
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    public void addCandidateAnswerScore(CandidateAnswerScore candidateAnswerScore) {
        candidateAnswerScores.add(candidateAnswerScore);
    }

    public void removeCandidateAnswerScore(CandidateAnswerScore candidateAnswerScore) {
        candidateAnswerScores.remove(candidateAnswerScore);
    }

    public void clear() {
        candidateAnswerScores.clear();
    }

}
