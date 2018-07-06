package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 徐旭
 * @data 2018/6/12 15:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FtpPo {
    /**
     * 文件存储的序号
     */
    private String ftpNo;

    /**
     * 上传的文件在FTP文件服务器中的地址
     */
    private String originalFile;

    /**
     * 上传文件的时间
     */
    private Date ftpDate;
}
