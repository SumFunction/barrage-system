package com.lrr.video.controller;

import com.lrr.video.service.VideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class VideoController {
    @Autowired
    VideoService videoService;
    @ApiOperation(value = "视频上传，返回视频id")
    @PostMapping("/video")
    public void uploadVideo(MultipartFile file){
        videoService.uploadVideo(file);
    }
}
