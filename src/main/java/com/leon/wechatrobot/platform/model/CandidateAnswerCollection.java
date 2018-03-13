package com.leon.wechatrobot.platform.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class CandidateAnswerCollection {
//    private static final Logger LOG = LoggerFactory.getLogger(CandidateAnswerCollection.class);
    private final List<CandidateAnswer> candidateAnswers = new ArrayList<>();

    public boolean isEmpty() {
        return candidateAnswers.isEmpty();
    }

    /**
     * 获取所有候选答案
     *
     * @return
     */
    public List<CandidateAnswer> getAllCandidateAnswer() {
        //按CandidateAnswer的分值排序
        Collections.sort(candidateAnswers);
        Collections.reverse(candidateAnswers);
        return candidateAnswers;
    }

    public void showAll() {
        for (CandidateAnswer candidateAnswer : getAllCandidateAnswer()) {
//            LOG.debug(candidateAnswer.getAnswer() + " " + candidateAnswer.getScore());
            System.out.println(candidateAnswer.getAnswer() + " " + candidateAnswer.getScore());
        }
    }

    public void showTopN(int topN) {
        for (CandidateAnswer candidateAnswer : getTopNCandidateAnswer(topN)) {
//            LOG.debug(candidateAnswer.getAnswer() + " " + candidateAnswer.getScore());
            System.out.println(candidateAnswer.getAnswer() + " " + candidateAnswer.getScore());
        }
    }

    public List<CandidateAnswer> getTopNCandidateAnswer(int topN) {
        //按CandidateAnswer的分值排序，返回topN
        List<CandidateAnswer> result = new ArrayList<>();
        Collections.sort(candidateAnswers);
        Collections.reverse(candidateAnswers);
        int len = candidateAnswers.size();
        if (topN > len) {
            topN = len;
        }
        for (int i = 0; i < candidateAnswers.size(); i++) {
            result.add(candidateAnswers.get(i));
        }

        return result;
    }

    public void addAnswer(CandidateAnswer candidateAnswer) {
        if (!candidateAnswers.contains(candidateAnswer)) {
            candidateAnswers.add(candidateAnswer);
        }
    }

    public void removeAnswer(CandidateAnswer candidateAnswer) {
        candidateAnswers.remove(candidateAnswer);
    }

}
