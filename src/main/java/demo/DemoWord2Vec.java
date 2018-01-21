package demo;

import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.leon.wechatrobot.platform.util.common.TrainUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Created on 16/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class DemoWord2Vec {

    public static void main(String[] args) throws IOException
    {
        WordVectorModel wordVectorModel = TrainUtil.trainOrLoadWord2VecModel();

        printNearest("人工智能", wordVectorModel);

    }

    static void printNearest(String word, WordVectorModel model)
    {
        System.out.printf("\n                                                Word     Cosine\n------------------------------------------------------------------------\n");
        for (Map.Entry<String, Float> entry : model.nearest(word))
        {
            System.out.printf("%50s\t\t%f\n", entry.getKey(), entry.getValue());
        }
    }

}
