package com.lrr.video.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.lrr.video.service.BarrageService;
import com.lrr.video.vo.Barrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BarrageListener {
    @Autowired
    BarrageService barrageService;
    @OnEvent("commit")
    public void commitBarrage(SocketIOClient channel, AckRequest request, Barrage data){
        barrageService.commitBarage(data,channel.getSessionId().toString());//提交一条弹幕
    }
}
