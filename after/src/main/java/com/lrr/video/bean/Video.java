package com.lrr.video.bean;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName video
 */
@TableName(value ="video")
@Data
public class Video implements Serializable {
    /**
     * 视频id
     */
    @TableId
    private Integer id;

    /**
     * 视频播放地址
     */
    private String resourceId;

    /**
     * 视频名称
     */
    private String title;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)//表明 插入的时候 会调用实现属性自动填充
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE) //插入跟更新的时候都会调用updateFill方法
    private Date updateTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}