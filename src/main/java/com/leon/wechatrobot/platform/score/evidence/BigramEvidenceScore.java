package com.leon.wechatrobot.platform.score.evidence;

import com.hankcs.hanlp.seg.common.Term;
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
public class BigramEvidenceScore implements EvidenceScore {

//    private static final Logger LOG = LoggerFactory.getLogger(BigramEvidenceScore.class);

    @Autowired
    private QuestionService questionService;

    private ScoreWeight scoreWeight = new ScoreWeight();

    @Override
    public void setScoreWeight(ScoreWeight scoreWeight) {
        this.scoreWeight = scoreWeight;
    }

    @Override
    public void score(Question question, Evidence evidence) {
//        LOG.debug("*************************");
//        LOG.debug("Evidence 二元模型评分开始");
        //1、对问题进行分词
        List<Term> questionTerms = questionService.segmentQuestion(question.getQuestion(), true);
        //2、利用二元模型构造出问题的所有二元表达式
        List<String> patterns = new ArrayList<>();
        for (int i = 0; i < questionTerms.size() - 1; i++) {
            String pattern = questionTerms.get(i).word + questionTerms.get(i + 1).word;
//            LOG.debug("二元模型表达式：" + pattern);
            patterns.add(pattern);
        }
        //3、在evidence中寻找模式，命中1个加2分
        String text = evidence.getTitle() + evidence.getSnippet();
        double score = 0;
        for (String pattern : patterns) {
            //计算二元表达式在证据中出现的次数，出现1次加2分
            int count = this.countsForBigram(text, pattern);
            if (count > 0) {
//                LOG.debug("模式: " + pattern + " 在文本中出现 " + count + "次");
                score = score + count * 2;
            }
        }
        score *= scoreWeight.getBigramEvidenceScoreWeight();
//        LOG.debug("Evidence 二元模型评分:" + score);
        evidence.addScore(score);
//        LOG.debug("Evidence 二元模型评分结束");
//        LOG.debug("*************************");
    }

    private int countsForBigram(String text, String pattern) {
        int count = 0;
        int index = -1;
        while (true) {
            index = text.indexOf(pattern, index + 1);
            if (index > -1) {
//                LOG.debug("模式: " + pattern + " 出现在文本中的位置：" + index);
                count++;
            } else {
                break;
            }
        }
        return count;

    }

}
