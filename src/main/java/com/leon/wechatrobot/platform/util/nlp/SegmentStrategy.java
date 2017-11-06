package com.leon.wechatrobot.platform.util.nlp;

import java.util.Vector;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public interface SegmentStrategy {
    Vector<String> segmentWord(String sentence);
}
