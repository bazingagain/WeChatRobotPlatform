package demo;

import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
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
        DocVectorModel docVectorModel = TrainUtil.trainOrLoadDoc2VecModel();

        printNearest("人工智能", wordVectorModel);
        System.out.println(wordVectorModel.similarity("山东", "江苏"));
        System.out.println(wordVectorModel.similarity("山东", "上班"));

        System.out.println(docVectorModel.similarity("山西副省长贪污腐败开庭", "陕西村干部受贿违纪"));

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
