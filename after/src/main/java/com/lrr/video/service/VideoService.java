package com.lrr.video.service;

import com.lrr.video.bean.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author Lenovo
* @description 针对表【video】的数据库操作Service
* @createDate 2022-12-13 12:26:30
*/
public interface VideoService extends IService<Video> {
    boolean uploadVideo(MultipartFile file);

}
