package com.leon.wechatrobot.platform.util.nlp.hanlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;
import java.util.Vector;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class HanLpStrategy {

    public Vector<String> segmentWord(String sentence) {
        List<Term> termList = StandardTokenizer.segment(sentence);
        Vector<String> list = new Vector<String>(termList.size());
        for (Term s : termList) {
            list.add(s.toString());
        }
        return list;
    }

    public List<String> getKeyword(String content, int number) {
        return HanLP.extractKeyword(content, number);
    }

    public List<Double> sentenceVector(String sentence) {
        Word2VecTrainer trainerBuilder = new Word2VecTrainer();
        WordVectorModel wordVectorModel = trainerBuilder.train("data/msr_training.utf8.txt", "data/msr_vectors.bin");
        wordVectorModel.nearest("中国");
        return null;
    }

}
