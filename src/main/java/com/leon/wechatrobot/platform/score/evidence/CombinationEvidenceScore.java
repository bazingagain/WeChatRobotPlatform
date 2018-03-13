package com.leon.wechatrobot.platform.service.impl.evidenceScore;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;
import com.leon.wechatrobot.platform.service.EvidenceScoreService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 22/02/2018.
 *
 * @author Xiaolei-Peng
 */
public class CombinationEvidenceScore implements EvidenceScoreService {

    private final List<EvidenceScoreService> evidenceScores = new ArrayList<>();
    private ScoreWeight scoreWeight = new ScoreWeight();

    public void addEvidenceScore(EvidenceScoreService evidenceScore) {
        evidenceScores.add(evidenceScore);
    }

    public void removeEvidenceScore(EvidenceScoreService evidenceScore) {
        evidenceScores.remove(evidenceScore);
    }

    public void clear() {
        evidenceScores.clear();
    }

    @Override
    public void score(Question question, Evidence evidence) {
        for (EvidenceScoreService evidenceScore : evidenceScores) {
            evidenceScore.score(question, evidence);
        }
    }

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }
}
