package com.lrr.video.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Barrage {
    private String text;//弹幕文本
    private Double time;//视频提交时间
    private Long createTime;//弹幕创建时间,时间戳格式

    @Override
    public String toString() {
        return "Barrage{" +
                "text='" + text + '\'' +
                ", time=" + time +
                ", createTime=" + createTime +
                '}';
    }
}
