package com.leon.wechatrobot.platform.util.nlp.IKAnalyzer;

import com.leon.wechatrobot.platform.util.nlp.SegmentStrategy;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class IKAnalyzerStrategy implements SegmentStrategy {
    private static final double YUZHI = 2.0;

    public static void main(String[] args) {
        SegmentStrategy strategy = new IKAnalyzerStrategy();
        String str1 = "我从小就不由自主地认为自己长大以后一定得成为一个象我父亲一样的画家, 可能是父母潜移默化的影响。其实我根本不知道作为画家意味着什么，我是否喜欢，最重要的是否适合我，我是否有这个才华。其实人到中年的我还是不确定我最喜欢什么，最想做的是什么？我相信很多人和我一样有同样的烦恼。毕竟不是每个人都能成为作文里的宇航员，科学家和大教授。知道自己适合做什么，喜欢做什么，能做好什么其实是个非常困难的问题。"
                + "幸运的是，我想我的孩子不会为这个太过烦恼。通过老大，我慢慢发现美国高中的一个重要功能就是帮助学生分析他们的专长和兴趣，从而帮助他们选择大学的专业和未来的职业。我觉得帮助一个未成形的孩子找到她未来成长的方向是个非常重要的过程。"
                + "美国高中都有专门的职业顾问，通过接触不同的课程，和各种心理，个性，兴趣很多方面的问答来帮助每个学生找到最感兴趣的专业。这样的教育一般是要到高年级才开始， 可老大因为今年上计算机的课程就是研究一个职业走向的软件项目，所以她提前做了这些考试和面试。看来以后这样的教育会慢慢由电脑来测试了。老大带回家了一些试卷，我挑出一些给大家看看。这门课她花了2个多月才做完，这里只是很小的一部分。"
                + "在测试里有这样的一些问题："
                + "你是个喜欢动手的人吗？ 你喜欢修东西吗？你喜欢体育运动吗？你喜欢在室外工作吗？你是个喜欢思考的人吗？你喜欢数学和科学课吗？你喜欢一个人工作吗？你对自己的智力自信吗？你的创造能力很强吗？你喜欢艺术，音乐和戏剧吗？  你喜欢自由自在的工作环境吗？你喜欢尝试新的东西吗？ 你喜欢帮助别人吗？你喜欢教别人吗？你喜欢和机器和工具打交道吗？你喜欢当领导吗？你喜欢组织活动吗？你什么和数字打交道吗？";
        String str2 = "我从小就好步骤不由得我AAS自主地认为自己长大以后一定得成为一个象我父亲一样的画家, 可能是父母潜移默化的影响。其实我根本不知道作为画家意味着什么，我是否喜欢，最重要的是否适合我，我是否有这个才华。其实人到中年的我还是不确定我最喜欢什么，最想做的是什么？我相信很多人和我一样有同样的烦恼。毕竟不是每个人都能成为作文里的宇航员，科学家和大教授。知道自己适合做什么，喜欢做什么，能做好什么其实是个非常困难的问题。"
                + "幸运的是，我想我的孩子不会为这个太过烦恼。通过老大，我慢慢发现美国高中的一个重要功能就是帮助学生分析他们的专长和兴趣，从而帮助他们选择大学的专业和未来的职业。我觉得帮助一个未成形的孩子找到她未来成长的方向是个非常重要的过程。"
                + "美国高中都有过接触不同的课程，和各种心理，个性，兴趣很多方面的问答来帮助每个学生找到最感兴趣的专业。这样的教育一般是要到高年级才开始， 可老大因为今年上计算机的课程就是研究一个职业走向的软件项目，所以她提前做了这些考试和面试。看来以后这样的教育会慢慢由电脑来测试了。老大带回家了一些试卷，我挑出一些给大家看看。这门课她花了2个多月才做完，这里只是很小的一部分。"
                + "在测试里有这样的一些问题："
                + "你是个喜欢动手的人吗？阿萨修东西吗？你喜欢体育运动吗？你喜欢在室外工作吗？你是个喜欢思考的人吗？你喜欢数学和科学课吗？你喜欢一个人工作吗？你对自己的智力自信吗？你的创造能力很强吗？你喜欢艺术，音乐和戏剧吗？  你喜欢自由自在的工作环境吗？你喜欢尝试新的东西吗？ 你喜欢帮助别人吗？你喜欢教别人吗？你喜欢和机器和工具打交道吗？你喜欢当领导吗？你喜欢组织活动吗？你什么和数字打交道吗？";

        double similarity = strategy.computeSimilarity("课程主要内容", "课程的主要内容");
        double similarity2 = strategy.computeSimilarity(str1, str2);
        System.out.println(similarity);
        System.out.println(similarity2);
    }

    /**
     * 根据语义向量计算相似度,这里采用余弦相似度实现
     * @param sentence1
     * @param sentence2
     * @return
     */
    public double computeSimilarity(String sentence1, String sentence2) {
        Map<String, double[]> T = unionVector(segmentWord(sentence1), segmentWord(sentence2));
        Iterator<String> it = T.keySet().iterator();
        double s1 = 0 , s2 = 0, sum = 0;  //S1、S2
        while( it.hasNext() ){
            double[] c = T.get( it.next() );
            sum += c[0]*c[1];
            s1 += c[0]*c[0];
            s2 += c[1]*c[1];
        }
        return sum / Math.sqrt( s1*s2 );
    }

    private static Map<String, double[]> unionVector(Vector<String> v1, Vector<String> v2) {
        Map<String, double[]> unionVector = new HashMap<String, double[]>();
        for (String index : v1) {
            if( index != null){
                double[] c = unionVector.get(index);
                c = new double[2];
                c[0] = 1;	//T1的语义分数Ci
                c[1] = YUZHI;//T2的语义分数Ci
                unionVector.put( index, c );
            }
        }
        for (String index : v2) {
            if (index != null) {
                double[] c = unionVector.get(index);
                if (c != null && c.length == 2) {
                    c[1] = 1;
                } else {
                    c = new double[2];
                    c[0] = YUZHI;
                    c[1] = 1;
                    unionVector.put(index, c);
                }
            }
        }
        return unionVector;
    }

    private static Vector<String> segmentWord(String sentence) {
        IKAnalyzer analyzer = new IKAnalyzer();
        analyzer.setUseSmart(true);
        Vector<String> sentenceVector = new Vector<String>();
        try {
            TokenStream tokenStream = analyzer.tokenStream("sentence", new StringReader(sentence));
            tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                CharTermAttribute charTermAttribute = tokenStream
                        .getAttribute(CharTermAttribute.class);
                sentenceVector.add(charTermAttribute.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sentenceVector;
    }
}
