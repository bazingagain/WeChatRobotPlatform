package demo;

import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;


/**
 * Created on 26/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class DemoStopWord {
    public static void main(String[] args)
    {
//        String text = "不问这个是什么";
        String text = "可以把你的苹果手机给我看一看吗?";
        // 可以动态修改停用词词典
//        CoreStopWordDictionary.add("不问");
        System.out.println(NotionalTokenizer.segment(text));
//        System.out.println(StandardTokenizer.segment(text));
//        CoreStopWordDictionary.remove("不问");
//        System.out.println(NotionalTokenizer.segment(text));
    }
}
