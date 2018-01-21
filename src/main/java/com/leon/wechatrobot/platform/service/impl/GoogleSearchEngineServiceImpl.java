package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.SearchEngineService;
import com.leon.wechatrobot.platform.util.common.TextExtract;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 18/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class GoogleSearchEngineServiceImpl implements SearchEngineService {

    //使用摘要
    private static final boolean SUMMARY = true;

    private static final int PAGE = 1;
    private static final int PAGESIZE = 8;

    @Override
    public List<Evidence> search(String sentence) {
        String query = "";
        List<Evidence> evidences = null;
        try {
            query = URLEncoder.encode(sentence, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        for (int i = 0; i < 1; i++) {
            query = "http://ajax.googleapis.com/ajax/services/search/web?start=" + i * PAGESIZE + "&rsz=large&v=1.0&q=" + query;
            evidences = searchGoogle(query);
            if (evidences.size() > 0) {
                return evidences;
            } else {
                break;
            }
        }
        return evidences;
    }

    private List<Evidence> searchGoogle(String query) {
        List<Evidence> evidences = new ArrayList<>();
        try {
            HttpClient httpClient = new HttpClient();
            GetMethod getMethod = new GetMethod(query);

            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());

            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
//                LOG.error("Method failed: " + getMethod.getStatusLine());
            }
            byte[] responseBody = getMethod.getResponseBody();
            String response = new String(responseBody, "UTF-8");
//            LOG.debug("搜索返回数据：" + response);
            JSONObject json = new JSONObject(response);
            String totalResult = json.getJSONObject("responseData").getJSONObject("cursor").getString("estimatedResultCount");
            int totalResultCount = Integer.parseInt(totalResult);
//            LOG.info("搜索返回记录数： " + totalResultCount);

            JSONArray results = json.getJSONObject("responseData").getJSONArray("results");

//            LOG.debug(" Results:");
            for (int i = 0; i < results.length(); i++) {
                Evidence evidence = new Evidence(null, null, null,(i+1));
                JSONObject result = results.getJSONObject(i);
                String title = result.getString("titleNoFormatting");
//                LOG.debug(title);
                evidence.setTitle(title);
                if (SUMMARY) {
                    String content = result.get("content").toString();
                    content = content.replaceAll("<b>", "");
                    content = content.replaceAll("</b>", "");
                    content = content.replaceAll("\\.\\.\\.", "");
//                    LOG.debug(content);
                    evidence.setSnippet(content);
                } else {
                    //从URL中提取正文
                    String url = result.get("url").toString();
                    String content = getHTMLContent(url);
                    if (content == null) {
                        content = result.get("content").toString();
                        content = content.replaceAll("<b>", "");
                        content = content.replaceAll("</b>", "");
                        content = content.replaceAll("\\.\\.\\.", "");
                    }
                    evidence.setSnippet(content);
//                    LOG.debug(content);
                }
                evidences.add(evidence);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            LOG.error("执行搜索失败：", e);
        }
        return evidences;
    }

    public static String getHTMLContent(String url) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            StringBuilder html = new StringBuilder();
            String line = reader.readLine();
            while (line != null) {
                html.append(line).append("\n");
                line = reader.readLine();
            }
            String content = TextExtract.parse(html.toString());
            return content;
        } catch (Exception e) {
            e.printStackTrace();
//            LOG.debug("解析URL失败：" + url, e);
        }
        return null;
    }

    public static void main(String[] args) {
        GoogleSearchEngineServiceImpl im = new GoogleSearchEngineServiceImpl();
        List<Evidence> evidences = im.search("模式识别是什么?");
        for (Evidence e : evidences) {
            System.out.println(e.getSnippet());
        }
    }
}
