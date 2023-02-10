package com.lrr.video.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lrr.video.bean.Video;
import com.lrr.video.listener.FileProgressListener;
import com.lrr.video.service.VideoService;
import com.lrr.video.mapper.VideoMapper;
import com.lrr.video.utils.AliyunVodSDKUtils;
import com.lrr.video.utils.VodUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.SQLOutput;
import java.util.UUID;

/**
* @author LianRongRong
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService {
    @Value("${aliyun.vod.endpoint}")
    String endPoint;

    @Override
    public boolean uploadVideo(MultipartFile file) {
        OSS client = AliyunVodSDKUtils.initVodClient(VodUtils.ACCESS_KEY_ID, VodUtils.ACCESS_KEY_SECRET, VodUtils.END_POINT);
        OutputStream out = null;
        FileProgressListener fileProgressListener = null;
        String fileName = "";
        try {
            InputStream inputStream = file.getInputStream();
            fileName = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
            fileProgressListener = new FileProgressListener(inputStream.available());
            client.putObject(new PutObjectRequest(VodUtils.BUCKET_NAME, fileName, inputStream)
            .<PutObjectRequest>withProgressListener(fileProgressListener));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (client != null) {
                client.shutdown();
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        boolean flag = fileProgressListener == null ? false : fileProgressListener.isSucceed();
        if(flag){
            String resource_url = "https://" + VodUtils.BUCKET_NAME + "." + endPoint + "/" + fileName;
            System.out.println(resource_url);
        }
        return flag;
    }
}




