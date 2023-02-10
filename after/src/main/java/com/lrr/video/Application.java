package com.lrr.video;


import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private SocketIOServer socketIOServer;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("socket已经启动");
        socketIOServer.start();
    }
}
