package com.leon.wechatrobot.platform.util.common;


/**
 * Created on 06/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class SimilarityUtil {

    public static double sentenceSimilarity(String sentence1, String sentence2) {
        return Config.docVectorModel.similarity(sentence1, sentence2);
    }

}
