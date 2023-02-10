package com.lrr.video.mq;

import com.corundumstudio.socketio.SocketIOClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lrr.video.ServerContains;
import com.lrr.video.vo.Barrage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class BarrageProducer {
    @Autowired
    AmqpTemplate amqpTemplate;
    public void produceMessage(Barrage barrage,String sessionId){
        Gson gson = new Gson();
        Map<String, SocketIOClient> clientContains = ServerContains.clientContains;
        clientContains.forEach((k,v) -> {
            if(!k.equals(sessionId)){
                HashMap<String, Object> map = new HashMap<>();
                try {
                    String message = gson.toJson(barrage);
                    map.put("message",message);
                    map.put("sessionId",k);
                    String data = gson.toJson(map);
                    amqpTemplate.convertAndSend("BarrageExchange","message",data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
