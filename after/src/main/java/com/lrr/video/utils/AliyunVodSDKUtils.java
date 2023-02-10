package com.lrr.video.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

public class AliyunVodSDKUtils {
    public static OSS initVodClient(String accessKeyId, String
            accessKeySecret,String endpoint) throws ClientException {
        // 填写Bucket名称，例如examplebucket。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }
}
