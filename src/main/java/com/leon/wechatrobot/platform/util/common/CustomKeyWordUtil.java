package com.leon.wechatrobot.platform.util.common;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.leon.wechatrobot.platform.util.file.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 13/03/2018.
 *
 * @author Xiaolei-Peng
 */
public class CustomKeyWordUtil {
    private static String RESOURCEPATH = ClassifyUtil.class.getClassLoader().getResource("").getPath();
    private static final String CustomKeywordFilePath = RESOURCEPATH+"data/custom/customkeyword.txt";

    private static List<String> keywords = new ArrayList<>();

    static {
        InputStreamReader read = null;
        File file = new File(CustomKeywordFilePath);
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addCustomKeyword() {
        for (String keyword : keywords) {
            CustomDictionary.add(keyword);
        }
    }

    public static void removeCustomKeyword() {
        for (String keyword : keywords) {
            CustomDictionary.remove(keyword);
        }
    }

}
