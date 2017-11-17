package com.leon.wechatrobot.platform.util.nlp.common;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;

/**
 * Created on 16/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class Config {
    private static final String MODEL_FILE_NAME = "data/test/word2vec.txt";
    public static WordVectorModel wordVectorModel = null;
    public static DocVectorModel docVectorModel = null;

    static {
        try {
            wordVectorModel = new WordVectorModel(MODEL_FILE_NAME);
            docVectorModel = new DocVectorModel(wordVectorModel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
