package com.leon.wechatrobot.platform.score.evidence;

import com.hankcs.hanlp.seg.common.Term;
import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.model.Question;
import com.leon.wechatrobot.platform.score.ScoreWeight;
import com.leon.wechatrobot.platform.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class SkipBigramEvidenceScore implements EvidenceScore {
//    private static final Logger LOG = LoggerFactory.getLogger(SkipBigramEvidenceScore.class);

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
//        LOG.debug("Evidence 跳跃二元模型评分开始");
        //1、对问题进行分词
        List<Term> questionTerms = questionService.segmentQuestion(question.getQuestion(), true);
        //2、利用跳跃二元模型构造出问题的所有跳跃二元表达式
        List<String> patterns = new ArrayList<>();
        for (int i = 0; i < questionTerms.size() - 2; i++) {
            String pattern = questionTerms.get(i).word + "." + questionTerms.get(i + 2).word;
//            LOG.debug("跳跃二元模型表达式：" + pattern);
            patterns.add(pattern);
        }
        //3、在evidence中寻找模式，命中1个加2分
        String text = evidence.getTitle() + evidence.getSnippet();
        double score = 0;
        for (String pattern : patterns) {
            //计算跳跃二元表达式在证据中出现的次数，出现1次加2分
            int count = this.countsForSkipBigram(text, pattern);
            if (count > 0) {
//                LOG.debug("模式: " + pattern + " 在文本中出现 " + count + "次");
                score = score + count * 2;
            }
        }
        score *= scoreWeight.getSkipBigramEvidenceScoreWeight();
//        LOG.debug("Evidence 跳跃二元模型评分:" + score);
        evidence.addScore(score);
//        LOG.debug("Evidence 跳跃二元模型评分结束");
//        LOG.debug("*************************");
    }

    private int countsForSkipBigram(String text, String pattern) {
        int count = 0;
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(text);
        while (matcher.find()) {
//            LOG.debug("正则表达式匹配：" + matcher.group());
            count++;
        }
        return count;
    }

}
