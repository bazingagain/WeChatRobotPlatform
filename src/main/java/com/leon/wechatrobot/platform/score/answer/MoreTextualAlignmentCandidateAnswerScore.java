package com.leon.wechatrobot.platform.score.answer;

import com.hankcs.hanlp.seg.common.Term;
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
public class MoreTextualAlignmentCandidateAnswerScore extends TextualAlignmentCandidateAnswerScore {

//    private static final Logger LOG = LoggerFactory.getLogger(MoreTextualAlignmentCandidateAnswerScore.class);

    @Autowired
    private QuestionService questionService;

    @Override
    public void score(Question question, Evidence evidence, CandidateAnswerCollection candidateAnswerCollection) {
//        LOG.debug("*************************");
//        LOG.debug("宽松文本对齐评分开始");
        super.score(question, evidence, candidateAnswerCollection);
//        LOG.debug("宽松文本对齐评分结束");
//        LOG.debug("*************************");
    }

    @Override
    protected List<Term> getQuestionTerms(Question question) {
        List<Term> list = questionService.segmentQuestion(question.getQuestion(), true);
        List<Term> result = new ArrayList<>();
        for (Term item : list) {
            if (item.length() > 1) {
                result.add(item);
            }
        }
        return result;
    }
}
