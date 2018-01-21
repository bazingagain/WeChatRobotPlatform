package com.leon.wechatrobot.platform.util.common;

import com.hankcs.hanlp.classification.classifiers.IClassifier;
import com.hankcs.hanlp.classification.classifiers.NaiveBayesClassifier;
import com.hankcs.hanlp.classification.models.AbstractModel;
import com.hankcs.hanlp.classification.models.NaiveBayesModel;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.mining.word2vec.*;

import java.io.IOException;

/**
 * Created on 16/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class TrainUtil {
    private static final String TRAIN_WORD2VEC_FILE_NAME = "data/train/搜狗文本分类语料库已分词.txt";
    private static final String TRAIN_CLASSIFIER_CORPUS_FOLDER = "data/train/搜狗文本分类语料库迷你版";

//    private static final String MODEL_WORD2VEC_FILE_NAME = "data/model/word2vec.mod";
    private static final String MODEL_WORD2VEC_FILE_NAME = "data/model/wiki.zh.vec";
    private static final String MODEL_NAIVEBAYES_CLASSIFIER_DATA = "data/model/naivebayesmodel.mod";


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

    /**
     * 训练或加载 朴素贝叶斯分类模型
     * @return
     * @throws IOException
     */
    public static IClassifier trainOrLoadClassifierModel() throws IOException {
        if (!IOUtil.isFileExisted(MODEL_NAIVEBAYES_CLASSIFIER_DATA)) {
            if (!IOUtil.isFileExisted(TRAIN_CLASSIFIER_CORPUS_FOLDER)) {
                System.err.println("分类训练语料不存在");
                System.exit(1);
            }
            IClassifier classifier = new NaiveBayesClassifier(); // 创建分类器，更高级的功能请参考IClassifier的接口定义
            classifier.train(TRAIN_CLASSIFIER_CORPUS_FOLDER);
            AbstractModel model = classifier.getModel();
            IOUtil.saveObjectTo(model, MODEL_NAIVEBAYES_CLASSIFIER_DATA);
            return classifier;
        }
        NaiveBayesModel model = (NaiveBayesModel) IOUtil.readObjectFrom(MODEL_NAIVEBAYES_CLASSIFIER_DATA);
        return new NaiveBayesClassifier(model);
    }

//    public static IClassifier trainClassifierFromKmean() {
//
//        return new NaiveBayesClassifier(model);
//    }
}
