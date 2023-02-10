package com.lrr.video.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lrr.video.mapper")
public class MyBatisConfig {
}
