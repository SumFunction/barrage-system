package com.lrr.video.service.impl;

import com.lrr.video.mq.BarrageProducer;
import com.lrr.video.service.BarrageService;
import com.lrr.video.vo.Barrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarrageServiceImpl implements BarrageService {
    @Autowired
    BarrageProducer barrageProducer;
    @Override
    public void commitBarage(Barrage barrage,String sessionId) {
        barrageProducer.produceMessage(barrage,sessionId);
    }
}
