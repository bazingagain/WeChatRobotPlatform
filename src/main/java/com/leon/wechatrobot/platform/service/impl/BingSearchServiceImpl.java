package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.SearchEngineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
public class BingSearchServiceImpl implements SearchEngineService {
    private static final int PAGE = 1;
    private static final int PAGESIZE = 10;
    private static final String ACCEPT = "text/html, */*; q=0.01";
    private static final String ENCODING = "gzip, deflate";
    private static final String LANGUAGE = "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3";
    private static final String CONNECTION = "keep-alive";
    private static final String HOST = "https://cn.bing.com";
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
        String referer = "https://cn.bing.com";
        for (int i = 0; i < PAGE; i++) {
            query = "https://cn.bing.com/search?q="+query+"&qs=n&pq=" + query+"&frist="+((i)*PAGESIZE+1)+"&FORM=PERE";
            List<Evidence> result = searchBing(query, referer);
            if (result != null && result.size() > 0) {
                evidences.addAll(result);
            } else {
                break;
            }
        }
        return evidences;
    }

    private List<Evidence> searchBing(String url, String referer) {
        List<Evidence> evidences = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements list = doc.select("li[class=b_algo]");
            for (int i = 0; i < list.size(); i++) {
                String titleContent = list.get(i).select("h2 > a").first().text();
                String questionUrl = list.get(i).select("h2 > a").attr("href");
                String snippet = list.get(i).select("div.b_caption > p").text();
                Evidence evidence = new Evidence(titleContent, snippet, questionUrl, i+1);
                evidences.add(evidence);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return evidences;
    }

    public static void main(String[] args) {
        BingSearchServiceImpl im = new BingSearchServiceImpl();
        List<Evidence> ls = im.search("姚明是谁?");
        for (Evidence e: ls) {
            System.out.println(e.getSort());
            System.out.println(e.getTitle());
            System.out.println(e.getSnippet());
            System.out.println(e.getUrl());
            System.out.println("______________________________________");
        }
    }

}
