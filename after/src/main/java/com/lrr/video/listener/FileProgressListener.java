package com.lrr.video.listener;

import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import lombok.Data;


@Data
public class FileProgressListener implements ProgressListener {
    private long bytesWritten = 0;

    private long totalBytes = -1;

    private boolean succeed = false;

    public FileProgressListener(int totalBytes){
        this.totalBytes = totalBytes;
    }

    //文件上传进度条
    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("开始上传文件......");
                break;
            case REQUEST_CONTENT_LENGTH_EVENT:
                System.out.println(this.totalBytes + "开始上传");
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                this.bytesWritten += bytes;
                if (this.totalBytes != -1) {
                    int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                    System.out.println(bytes + " bytes 已经被写, 上传进度: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                } else {
                    System.out.println(bytes + " bytes 已经被写, 上传进度: 未知大小" + "(" + this.bytesWritten + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                System.out.println("成功上传, " + this.bytesWritten + " bytes 已经被完整上传");
                break;
            case TRANSFER_FAILED_EVENT:
                System.out.println("失败上传, " + this.bytesWritten + " bytes 已经被上传");
                break;
            default:
                break;
        }
    }

}
