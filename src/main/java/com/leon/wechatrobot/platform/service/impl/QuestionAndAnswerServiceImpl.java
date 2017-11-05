package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.service.QuestionAndAnswerService;
import com.leon.wechatrobot.platform.util.file.FileUtil;
import com.leon.wechatrobot.platform.util.nlp.IKAnalyzer.IKAnalyzerStrategy;

import java.util.Iterator;
import java.util.Map;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class QuestionAndAnswerServiceImpl implements QuestionAndAnswerService {

    public static void main(String[] args) {
        String qnswer = new QuestionAndAnswerServiceImpl().getMostSimilarQuestion("朱老师联系方式");
        System.out.println(qnswer);
    }

    public String getMostSimilarQuestion(String inputQuestion) {
        IKAnalyzerStrategy strategy = new IKAnalyzerStrategy();
        FileUtil.readFiles();
        String answer = FileUtil.publicReply.get(inputQuestion);
        if (answer == null) {
            double boost = 0.2;
            Iterator it = FileUtil.publicReply.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                double similarity = strategy.computeSimilarity(inputQuestion, (String) entry.getKey());
                if (similarity > boost) {
                    boost = similarity;
                    answer = (String) entry.getValue();
                }
            }

        }
        return answer;
    }
}
