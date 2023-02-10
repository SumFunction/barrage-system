package com.lrr.video.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VodUtils implements InitializingBean {
    @Value("${aliyun.vod.accessKeyId}")
    private String keyId;
    @Value("${aliyun.vod.accessKeySecret}")
    private String keySecret;
    @Value("${aliyun.vod.endpoint}")
    private String endpoint;
    @Value("${aliyun.vod.bucketName}")
    private String bucketName;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String END_POINT;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        END_POINT = "https://" + endpoint;
        BUCKET_NAME = bucketName;
    }
}
