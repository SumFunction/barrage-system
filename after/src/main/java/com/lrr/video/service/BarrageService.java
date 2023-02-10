package com.lrr.video.service;

import com.lrr.video.vo.Barrage;

public interface BarrageService {
    void commitBarage(Barrage barrage,String sessionId);
}
