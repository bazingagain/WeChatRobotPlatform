package com.leon.wechatrobot.platform.util.nlp.common;

import com.leon.wechatrobot.platform.util.nlp.SegmentStrategy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * Created on 06/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class SimilarityUtil {
    private static final double YUZHI = 2.0;
    /**
     * 根据语义向量计算相似度,这里采用余弦相似度实现
     * @param sentence1
     * @param sentence2
     * @param strategy
     * @return
     */
    public static double consinSimilarity(String sentence1, String sentence2, SegmentStrategy strategy) {
        Map<String, double[]> T = unionVector(strategy.segmentWord(sentence1), strategy.segmentWord(sentence2));
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
}
