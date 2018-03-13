package com.leon.wechatrobot.platform.service.impl.baike;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.leon.wechatrobot.platform.model.CandidateAnswer;
import com.leon.wechatrobot.platform.service.BaikeService;
import com.leon.wechatrobot.platform.util.common.HttpUtil;

/**
 * Created on 23/02/2018.
 *
 * @author Xiaolei-Peng
 */
public class CNDBpediaSearchEngineServiceImpl implements BaikeService {

    @Override
    public CandidateAnswer searchCandidateAnswer(String kewword) {
        String json = request(kewword);
        JsonParser parser=new JsonParser();  //创建JSON解析器
        JsonObject object=(JsonObject) parser.parse(json);  //创建JsonObject对象
        JsonArray array=object.get("ret").getAsJsonArray();    //得到为json的数组
        if (array != null && array.size()>0 ) {
            JsonArray subArray=array.get(array.size()-1).getAsJsonArray();
            if (subArray.get(0).getAsString().equals("DESC")) {
                return new CandidateAnswer(subArray.get(1).getAsString(), 0);
            }
        }
        return null;
    }

    private String request(String keyword) {
        try {
            String url ="http://shuyantech.com/api/cndbpedia/avpair?q=" + keyword;
            return HttpUtil.getHttpResponse(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
