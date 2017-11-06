package com.leon.wechatrobot.platform.util.nlp.IKAnalyzer;

import com.leon.wechatrobot.platform.util.nlp.SegmentStrategy;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class IKAnalyzerStrategy implements SegmentStrategy {

    public Vector<String> segmentWord(String sentence) {
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
