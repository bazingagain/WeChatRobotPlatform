package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.SearchEngineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 19/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class WikiSearchEngineServiceImpl implements SearchEngineService {
    private static final String ACCEPT = "text/html, */*; q=0.01";
    private static final String ENCODING = "gzip, deflate";
    private static final String LANGUAGE = "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3";
    private static final String CONNECTION = "keep-alive";
    private static final String HOST = "www.baidu.com";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:31.0) Gecko/20100101 Firefox/31.0";

    @Override
    public List<Evidence> search(String sentence) {
        String query = "";
        List<Evidence> evidences = new ArrayList<>();
        try {
            query = URLEncoder.encode(sentence, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        String referer = "https://zh.wikipedia.org";
        query = "https://zh.wikipedia.org/wiki/" + query;
        List<Evidence> result = searchWiki(query, referer);
        if (result != null && result.size() > 0) {
            evidences.addAll(result);
        }
        return evidences;
    }

    private List<Evidence> searchWiki(String url, String referer) {
        List<Evidence> evidences = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            String resultCssQuery = "html > body > div#content > div#bodyContent > divmw-content-text > div#mw-parser-output > p";
            Elements list = doc.select(resultCssQuery);

            System.out.println(list);
//            for (int i = 0; i < list.size(); i++) {
//            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return evidences;
    }

    public static void main(String[] args) {
        WikiSearchEngineServiceImpl im = new WikiSearchEngineServiceImpl();
        List<Evidence> ls = im.search("模式识别");
        for (Evidence e: ls) {
            System.out.println(e.getSnippet());
        }
    }

}
