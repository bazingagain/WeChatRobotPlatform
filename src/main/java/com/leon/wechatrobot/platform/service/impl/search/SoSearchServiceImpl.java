package com.leon.wechatrobot.platform.service.impl.searchEngine;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.SearchEngineService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created on 19/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class SoSearchEngineServiceImpl implements SearchEngineService {
    private static final int PAGE = 1;

    @Override
    public List<Evidence> search(String sentence) {
        String query = "";
        List<Evidence> evidences = new ArrayList<>();
        try {
            query = URLEncoder.encode(sentence, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        String referer = "https://www.so.com";
        //TODO 改写为多页查询 发现两次之间若时间间隔太少,则得到的第2页的数据是错误数据
        for (int i = 0; i < PAGE; i++) {
            query = "https://www.so.com/s?q="+query+"&pn="+(i+1);
            List<Evidence> result = searchSogou(query, referer);
            if (result != null && result.size() > 0) {
                evidences.addAll(result);
            } else {
                break;
            }
        }
        return evidences;
    }

    private List<Evidence> searchSogou(String url, String referer) {
        List<Evidence> evidences = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements list = doc.select("li[class=res-list]");
            for (int i = 0; i < list.size(); i++) {
                String titleContent = list.get(i).select("h3 > a").text();
                if (titleContent.contains("_")) {
                    titleContent = titleContent.substring(0, titleContent.indexOf("_"));
                }
                String questionUrl = list.get(i).select("h3 > a").attr("href");
                Element ele = list.get(i).select("div").first();
                Elements e = ele.select("p");
                e.remove();
                Elements e2 = ele.select("a");
                e2.remove();
                Elements e3 = ele.select("ul");
                e3.remove();
                Elements e4 = ele.select("span");
                e4.remove();
                String snippet = ele.text();
                if (snippet != null && !snippet.equals("")) {
                    if (snippet.contains("。")) {
                        snippet = snippet.substring(0, snippet.lastIndexOf("。")+1);
                    }
                    if (snippet.contains("...")) {
                        snippet = snippet.substring(0, snippet.lastIndexOf("..."));
                    }
                    Evidence evidence = new Evidence(titleContent, snippet.trim(), questionUrl, i+1);
                    evidences.add(evidence);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return evidences;
    }

}
