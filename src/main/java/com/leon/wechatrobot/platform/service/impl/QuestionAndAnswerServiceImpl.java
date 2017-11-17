package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.service.QuestionAndAnswerService;
import com.leon.wechatrobot.platform.util.file.FileUtil;
import com.leon.wechatrobot.platform.util.nlp.common.SimilarityUtil;

import java.util.Iterator;
import java.util.Map;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class QuestionAndAnswerServiceImpl implements QuestionAndAnswerService {

    public static void main(String[] args) {
        String qnswer = new QuestionAndAnswerServiceImpl().getMostSimilarQuestion("朱de 老师联系方式是什么");
        System.out.println(qnswer);
    }

    /**
     * 获取最相似的问题,并通过该问题找到答案
     * @param inputQuestion
     * @return 相关答案
     */
    public String getMostSimilarQuestion(String inputQuestion) {
        FileUtil.readFiles();
        String answer = FileUtil.publicReply.get(inputQuestion);
        if (answer == null) {
            double boost = 0.2;
            Iterator it = FileUtil.publicReply.entrySet().iterator();
            double start = System.currentTimeMillis();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                double similarity = SimilarityUtil.sentenceSimilarity(inputQuestion, (String) entry.getKey());
                if (similarity > boost) {
                    boost = similarity;
                    answer = (String) entry.getValue();
                }
            }
            double end = System.currentTimeMillis();
            System.out.println("cost time: " + (end - start) + "ms");
        }
        return answer;
    }
}
