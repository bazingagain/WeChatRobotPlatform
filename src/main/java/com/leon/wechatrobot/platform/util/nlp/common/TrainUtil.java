package com.leon.wechatrobot.platform.util.nlp.common;

import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;

import java.io.IOException;

/**
 * Created on 16/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class TrainUtil {
    private static final String TRAIN_FILE_NAME = "data/test/搜狗文本分类语料库已分词.txt";
    private static final String MODEL_FILE_NAME = "data/test/word2vec.txt";

    public static WordVectorModel trainOrLoadModel() throws IOException {
        if (!IOUtil.isFileExisted(MODEL_FILE_NAME)) {
            if (!IOUtil.isFileExisted(TRAIN_FILE_NAME)) {
                System.err.println("语料不存在，请阅读文档了解语料获取与格式：https://github.com/hankcs/HanLP/wiki/word2vec");
                System.exit(1);
            }
            Word2VecTrainer trainerBuilder = new Word2VecTrainer();
            return trainerBuilder.train(TRAIN_FILE_NAME, MODEL_FILE_NAME);
        }
        return new WordVectorModel(MODEL_FILE_NAME);
    }

}
