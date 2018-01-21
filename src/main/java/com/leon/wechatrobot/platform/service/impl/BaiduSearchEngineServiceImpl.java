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
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class BaiduSearchEngineServiceImpl implements SearchEngineService {

    private static final int PAGE = 1;
    private static final int PAGESIZE = 10;
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
        String referer = "http://www.baidu.com/";
        //TODO 改写为多页查询 发现两次之间若时间间隔太少,则得到的第2页的数据是错误数据
        for (int i = 0; i < PAGE; i++) {
            query = "http://www.baidu.com/s?tn=monline_5_dg&ie=utf-8&wd=" + query+"&oq="+query+"&usm=3&f=8&bs="+query+"&rsv_bp=1&rsv_sug3=1&rsv_sug4=141&rsv_sug1=1&rsv_sug=1&pn=" + i * PAGESIZE;
            List<Evidence> result = searchBaidu(query, referer);
            if (result != null && result.size() > 0) {
                evidences.addAll(result);
            } else {
                break;
            }
        }
        return evidences;
    }

    private List<Evidence> searchBaidu(String url, String referer) {
        List<Evidence> evidences = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url)
                    .header("Accept", ACCEPT)
                    .header("Accept-Encoding", ENCODING)
                    .header("Accept-Language", LANGUAGE)
                    .header("Connection", CONNECTION)
                    .header("User-Agent", USER_AGENT)
                    .header("Host", HOST)
                    .header("Referer", referer)
                    .get();
            String resultCssQuery = "html > body > div > div > div > div > div";
            Elements elements = document.select(resultCssQuery);
            int i = 0;
            for (Element element : elements) {
                Elements subElements = element.select("h3 > a");
                if(subElements.size() != 1){
                    continue;
                }
                String title =subElements.get(0).text();
                if (title == null || "".equals(title.trim())) {
                    continue;
                }
                subElements = element.select("div.c-abstract");
                if(subElements.size() != 1){
                    continue;
                }
                String snippet =subElements.get(0).text();
                if (snippet == null || "".equals(snippet.trim())) {
                    continue;
                }
                Evidence evidence = new Evidence(title, snippet, null, ++i);
                evidences.add(evidence);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return evidences;
    }

    public static void main(String[] args) {
        BaiduSearchEngineServiceImpl im = new BaiduSearchEngineServiceImpl();
        List<Evidence> ls = im.search("姚明是谁?");
        for (Evidence e: ls) {
            System.out.println(e.getSnippet());
        }
    }
}
