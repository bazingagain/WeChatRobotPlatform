package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.service.ClusterService;

import java.util.*;

/**
 * Created on 07/12/2017.
 *
 * @author Xiaolei-Peng
 */
public class BiKmeansClusterServiceImpl implements ClusterService{

    @Override
    public List<Integer> cluster(Map<Integer, float[]> map, int k) {
        int m = map.size();
        List<Integer> assignment = new ArrayList<>(m);
        List<ClusterSet> cluster = new ArrayList<>();
        bikmeans(k);
        return null;
    }

    private Vector mean(Map<Integer, Vector> map) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();

        }
        return null;
    }

    private List<ArrayList<float[]>> initCluster(int k) {
        List<ArrayList<float[]>> cluster = new ArrayList<ArrayList<float[]>>();
        for (int i = 0; i < k; i++) {
            cluster.add(new ArrayList<float[]>());
        }
        return cluster;
    }

    private void bikmeans(int k) {
        List<ArrayList<float[]>> cluster  = initCluster(k);
        while (cluster.size() < k) {

        }
    }

    class ClusterSet {
        private float erro;
        private List<float[]> clu;
        private float[] center;

        public float getErro() {
            return erro;
        }

        public void setErro(float erro) {
            this.erro = erro;
        }

        public List<float[]> getClu() {
            return clu;
        }

        public void setClu(List<float[]> clu) {
            this.clu = clu;
        }

        public float[] getCenter() {
            return center;
        }

        public void setCenter(float[] center) {
            this.center = center;
        }
    }
}
