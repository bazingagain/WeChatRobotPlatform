package com.leon.wechatrobot.platform.service.impl;

import com.leon.wechatrobot.platform.model.Evidence;
import com.leon.wechatrobot.platform.service.FilterService;

import java.util.Iterator;
import java.util.List;

/**
 * Created on 23/02/2018.
 *
 * @author Xiaolei-Peng
 */
public class FilterServiceImpl implements FilterService {
    @Override
    public List<Evidence> filterEvidence(List<Evidence> originalEvidence, String keyword) {
        Iterator it = originalEvidence.iterator();
        while (it.hasNext()) {
            Evidence e = (Evidence) it.next();
            if (!e.getSnippet().contains(keyword)) {
                it.remove();
            }
        }
        return originalEvidence;
    }
}
