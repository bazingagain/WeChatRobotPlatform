package com.leon.wechatrobot.platform.util.common;

import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.mining.word2vec.*;

import java.io.IOException;

/**
 * Created on 16/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class TrainUtil {

//    private static final String MODEL_WORD2VEC_FILE_NAME = "data/model/word2vec.mod";
    private static final String TRAIN_WORD2VEC_FILE_NAME = "data/train/wiki_seg.txt";
//    private static final String MODEL_WORD2VEC_FILE_NAME = "data/model/wiki1.zh.vec";
    private static final String MODEL_WORD2VEC_FILE_NAME = "data/model/hanlp-wiki-vec-zh.txt";
//    private static final String MODEL_WORD2VEC_FILE_NAME = "data/model/hanlp-wiki-vec-zh.txt";


    /**
     * 训练或加载word2vec模型
     * @return
     * @throws IOException
     */
    public static WordVectorModel trainOrLoadWord2VecModel() throws IOException {
        if (!IOUtil.isFileExisted(MODEL_WORD2VEC_FILE_NAME)) {
            if (!IOUtil.isFileExisted(TRAIN_WORD2VEC_FILE_NAME)) {
                System.err.println("语料不存在，请阅读文档了解语料获取与格式：https://github.com/hankcs/HanLP/wiki/word2vec");
                System.exit(1);
            }
            Word2VecTrainer trainerBuilder = new Word2VecTrainer();
            return trainerBuilder.train(TRAIN_WORD2VEC_FILE_NAME, MODEL_WORD2VEC_FILE_NAME);
        }
        return new WordVectorModel(MODEL_WORD2VEC_FILE_NAME);
    }

    public static DocVectorModel trainOrLoadDoc2VecModel() throws IOException{
        return new DocVectorModel(trainOrLoadWord2VecModel());
    }

    public static void main(String[] args) {
        try {
            trainOrLoadWord2VecModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
