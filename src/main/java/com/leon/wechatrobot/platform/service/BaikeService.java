package com.leon.wechatrobot.platform.service;

import com.leon.wechatrobot.platform.model.CandidateAnswer;

/**
 * Created on 23/02/2018.
 *
 * @author Xiaolei-Peng
 */
public interface BaikeService {
    CandidateAnswer searchCandidateAnswer(String kewword);
}
