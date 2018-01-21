package com.leon.wechatrobot.platform.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created on 07/12/2017.
 *
 * @author Xiaolei-Peng
 */
public interface ClusterService {
    List<Integer> cluster(Map<Integer, float[]> map, int k);
}
