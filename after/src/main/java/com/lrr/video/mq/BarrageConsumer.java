package com.lrr.video.mq;

import com.corundumstudio.socketio.SocketIOClient;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lrr.video.ServerContains;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BarrageConsumer {
    /**
     * @RabbitListener注解用于不停监听消息
     *  queues：用于监听哪些队列
     */
    @RabbitListener(queues = {"BarrageQueue"})
    public void receive(String message){
        Gson gson = new Gson();

        Map map = gson.fromJson(message, Map.class);
        String sessionId = (String)map.get("sessionId");
        Map<String, SocketIOClient> clientContains = ServerContains.clientContains;
        SocketIOClient socketIOClient = clientContains.get(sessionId);
        if(socketIOClient != null)
            socketIOClient.sendEvent("get",map.get("message"));
    }
}
