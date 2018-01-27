package com.leon.wechatrobot.platform.score.answer;

import com.leon.wechatrobot.platform.model.CandidateAnswerCollection;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface CandidateAnswerScore {
    /**
     * 对候选答案进行评分 候选答案的分值存储在CandidateAnswer的实例里面
     *
     * @param question 问题
     * @param evidence 证据
     * @param candidateAnswerCollection 候选答案集合
     */
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection);

    /**
     * 评分组件权重
     *
     * @param scoreWeight
     */
    public void setScoreWeight(ScoreWeight scoreWeight);

}
