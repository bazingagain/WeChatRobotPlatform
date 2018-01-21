package com.leon.wechatrobot.platform.util.common;

import java.util.List;

/**
 * Created on 07/12/2017.
 *
 * @author Xiaolei-Peng
 */
public class CommonUtils {
    /**
     * 计算两个点之间的距离
     *
     * @param element
     *            点1
     * @param center
     *            点2
     * @return 距离
     */
    public static  float distance(float[] element, float[] center) {
        float distance = 0.0f;
        float z = 0.0f;
        for (int i = 0; i < element.length; i++) {
            float v = element[i] - center[i];
            z += (v*v);
        }
        distance = (float) Math.sqrt(z);
        return distance;
    }
    /**
     * 求两点误差平方的方法
     *
     * @param element
     *            点1
     * @param center
     *            点2
     * @return 误差平方
     */
    public static  float errorSquare(float[] element, float[] center) {
        float errSquare = 0.0f;
        for (int i = 0; i < element.length; i++) {
            float v = element[i] - center[i];
            errSquare += (v*v);
        }
        return errSquare;
    }
    /**
     * 计算误差平方和准则函数方法
     */
    public static  float countRule(List<float[]> cluster, float[] center) {
        float jcF = 0;
        for (int j = 0; j < cluster.size(); j++) {
            jcF += CommonUtils.errorSquare(cluster.get(j), center);
        }
        return  jcF;
    }
    /**
     * 打印数据，测试用
     *
     * @param dataArray
     *            数据集
     * @param dataArrayName
     *            数据集名称
     */
    public static  void printDataArray(List<float[]> dataArray, String dataArrayName) {
        for (int i = 0; i < dataArray.size(); i++) {
            System.out.println("print:" + dataArrayName + "[" + i + "]={"
                    + dataArray.get(i)[0] + "," + dataArray.get(i)[1] + "}");
        }
        System.out.println("===================================");
    }

}
