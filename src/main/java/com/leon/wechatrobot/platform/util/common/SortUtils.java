package com.leon.wechatrobot.platform.util.common;

import java.util.*;

/**
 * Created on 27/01/2018.
 *
 * @author Xiaolei-Peng
 */
public class SortUtils {

    public static <K> List<Map.Entry<K, Integer>> sortByIntegerValue(Map<K, Integer> map) {
        List<Map.Entry<K, Integer>> orderList = new ArrayList<>(map.entrySet());
        Collections.sort(orderList, new Comparator<Map.Entry<K, Integer>>() {
            @Override
            public int compare(Map.Entry<K, Integer> o1, Map.Entry<K, Integer> o2) {
                return (o1.getValue() - o2.getValue());
            }
        });
        return orderList;
    }
}
