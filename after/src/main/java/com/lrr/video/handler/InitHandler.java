package com.lrr.video.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.lrr.video.ServerContains;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitHandler {
    @Autowired
    RedisTemplate redisTemplate;
    @OnConnect
    public void onConnect(SocketIOClient client){
        ServerContains.clientContains.put(client.getSessionId().toString(),client);
        System.out.println(client.getSessionId().toString() + "连接了");
        redisTemplate.opsForValue().increment("watchNumber");//观看人数++
    }
    @OnDisconnect
    public void onDisconnect(SocketIOClient client){
        ServerContains.clientContains.remove(client.getSessionId().toString(),client);
        System.out.println(client.getSessionId().toString() + "断开了");
        redisTemplate.opsForValue().decrement("watchNumber");//观看人数--
    }
}
