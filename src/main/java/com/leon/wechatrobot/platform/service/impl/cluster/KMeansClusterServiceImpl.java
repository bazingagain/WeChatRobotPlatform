package com.leon.wechatrobot.platform.service.impl.cluster;

import com.hankcs.hanlp.mining.word2vec.Utils;
import com.leon.wechatrobot.platform.model.ClusterResult;
import com.leon.wechatrobot.platform.model.DataSet;
import com.leon.wechatrobot.platform.service.ClusterService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created on 06/04/2018.
 *
 * @author Xiaolei-Peng
 */

@Service
public class ClusterServiceImpl implements ClusterService {

    private final int MAX_ITER = 10;

    @Override
    public List<ClusterResult> cluster(DataSet dataSet, int clusterNum) {
        final int datasetNum = dataSet.getNum();
        final int layerSize = dataSet.getSize();
        // Run K-means on the word vectors
        System.err.printf("now computing K-means clustering (K=%d)\n", clusterNum);
        final int[] centcn = new int[clusterNum]; //存放每个簇中有多少个元素
        final int[] cl = new int[datasetNum];  // 存放每个元素属于哪一个簇的簇索引值
        final int centSize = clusterNum * layerSize;
        final double[] cent = new double[centSize];

        for (int i = 0; i < datasetNum; i++)  //分配初始簇 第i个数据分配到第 (i%clusterNum) 个簇
            cl[i] = i % clusterNum;

        for (int it = 0; it < MAX_ITER; it++)
        {
            for (int j = 0; j < centSize; j++)
                cent[j] = 0;
            for (int j = 0; j < clusterNum; j++)
                centcn[j] = 1;   //避免除0错误
            //计算得到每个簇所包含的元素,以及每个簇的总元素的个特征值叠加的和
            for (int k = 0; k < datasetNum; k++) {
                for (int l = 0; l < layerSize; l++) {
                    cent[layerSize * cl[k] + l] += dataSet.getMatrixElement(k, l);
                }
                centcn[cl[k]]++;
            }
            for (int j = 0; j < clusterNum; j++) {
                double closev = 0;
                for (int k = 0; k < layerSize; k++) {
                    cent[layerSize * j + k] /= centcn[j];  // 第j个簇中心 除以 该簇的所包含的元素 得到簇中心各特征的平均值, 取得簇心
                    closev += cent[layerSize * j + k] * cent[layerSize * j + k]; //x1^2 + x2^2 +... +x(layer1size)^2 簇心的各特征值的平方和
                }
                closev = Math.sqrt(closev);
                for (int k = 0; k < layerSize; k++) {
                    cent[layerSize * j + k] /= closev;
                }
            }
            // 将元素进行重新分配簇
            for (int k = 0; k < datasetNum; k++) {
                double closev = -10;
                int closeid = 0;
                for (int l = 0; l < clusterNum; l++) {
                    double x = 0;
                    for (int j = 0; j < layerSize; j++) {
                        x += cent[layerSize * l + j] * dataSet.getMatrixElement(k, j);
                    }
                    if (x > closev) {
                        closev = x;
                        closeid = l;
                    }
                }
                cl[k] = closeid;
            }
        }
        return saveKMeansClusterResult(dataSet, clusterNum, datasetNum, centcn, cl);
    }

    private List<ClusterResult> saveKMeansClusterResult(DataSet dataSet, int clusterNum, int datasetNum,
                                                        int[] centcn, int[] cl) {
        List<ClusterResult> allCluster = new ArrayList<>();
        for (int i = 0; i < clusterNum; i++) {
            ClusterResult result = new ClusterResult();
            result.setItemNum(centcn[i] - 1); //开始每个簇中的元素都初始化为1了,这里要减1
            result.setItems(new ArrayList<Object>());
            allCluster.add(result);
        }
        for (int i = 0; i < datasetNum; i++) {
            allCluster.get(cl[i]).getItems().add(dataSet.getItem(i));
        }
        return allCluster;
    }

}
