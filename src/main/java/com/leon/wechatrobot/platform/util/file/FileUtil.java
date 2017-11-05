package com.leon.wechatrobot.platform.util.file;

import com.blade.kit.StringKit;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 05/11/2017.
 *
 * @author Xiaolei-Peng
 */
public class FileUtil {
    public static Map<String, String> publicReply = new HashMap<String, String>();
    public static Map<Integer, String> publicQuestionList = new HashMap<Integer, String>();

    public static void main(String[] args) {
        readFiles();
        System.out.println(publicQuestionList.get(1));
    }


    public static void readFiles(){
        String s[];
        InputStreamReader read = null;
        InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream("public.txt");
        BufferedReader br = null;
        try {
            read = new InputStreamReader(stream, "UTF-8");
            br = new BufferedReader(read);
            String str = br.readLine();
            while (str != null) {
                str = br.readLine();
                if (str == null)
                    break;
                s = str.split("--");
                if (s.length >= 2) { // 只要存在2个以上的分词即可
                    s[0] = s[0].trim();
                    s[1] = s[1].trim();
                    if (!StringKit.isAnyBlank(s[0], s[1])) {
                        if (s[0].contains("<list>")) { //问题列表
                            publicReply.put(s[0].substring(s[0].indexOf(">") + 1), s[1]);
                            String[] questions = s[1].split("、");
                            generateQuestionList(questions);
                        } else {
                            publicReply.put(s[0], s[1]);
                        }
                    }
                }
            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateQuestionList(String[] questions) {
        for (String question : questions) {
            if (question.contains(".")) {
                try {
                    String index = question.substring(0, question.indexOf("."));
                    int questionIndex = Integer.parseInt(index); //问题唯一编号值
                    publicQuestionList.put(questionIndex, question.substring(question.indexOf(".")+1));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
