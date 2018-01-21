package com.leon.wechatrobot.platform.service;

import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public interface WordService {

    List<Term> segmentWord(String sentence);

}
