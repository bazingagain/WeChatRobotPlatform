package com.leon.wechatrobot.platform.service.impl;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.leon.wechatrobot.platform.service.WordService;

import java.util.List;

/**
 * Created on 17/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class WordServiceImpl implements WordService {
    @Override
    public List<Term> segmentWord(String sentence) {
        return StandardTokenizer.segment(sentence);
    }
}
