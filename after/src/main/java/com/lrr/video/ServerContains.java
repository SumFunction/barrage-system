package com.lrr.video;

import com.corundumstudio.socketio.SocketIOClient;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

public class ServerContains {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static ThreadPoolExecutor executor;
    public static LongAdder watchNumber;

    public static List<Map<String, SocketIOClient>> clientContainList;
    public static Map<String, SocketIOClient> clientContains = new HashMap<>();

    static {
        executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
        watchNumber = new LongAdder();
        clientContainList = new ArrayList<>();

        for(int i = 0;i < CORE_POOL_SIZE;i++)
            clientContainList.add(new ConcurrentHashMap<>());
    }
}
