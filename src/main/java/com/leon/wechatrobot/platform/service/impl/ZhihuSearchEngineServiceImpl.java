package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.SearchEngineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class ZhihuSearchEngineServiceImpl implements SearchEngineService {

    @Override
    public List<Evidence> search(String sentence) {
        return searchZhihu(sentence);
    }

    private List<Evidence> searchZhihu(String sentence) {
        List<Evidence> evidences = new ArrayList<>();
        try {
            String query = URLEncoder.encode(sentence, "UTF-8");
            String url = "https://www.zhihu.com/search?type=content&q=" + query;
            Document doc = Jsoup.connect(url).get();
            Elements list = doc.select("div[class=Card]").select("div.List-item");
            for (int i = 0; i < list.size(); i++) {
                Element titleContent = list.get(i).select("h2.ContentItem-title").first();
                String richContent = list.get(i).select("span[itemprop=text]").first().text();
                String questionUrl = titleContent.child(0).select("meta[itemprop=url]").first().attr("content");
                String question = titleContent.child(0).select("meta[itemprop=name]").first().attr("content").replace("<em>", "").replace("</em>", "");
                String snippet = richContent.substring(richContent.indexOf("：")+1);
                Evidence evidence = new Evidence(question, snippet, questionUrl, i+1);
                evidences.add(evidence);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return evidences;
    }

}
