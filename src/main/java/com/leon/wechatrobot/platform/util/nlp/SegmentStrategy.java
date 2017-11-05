package com.leon.wechatrobot.platform.util.nlp;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public interface SegmentStrategy {
    double computeSimilarity(String sentence1, String sentence2);
}
