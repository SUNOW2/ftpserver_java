package com.example.demo.dao;

import com.example.demo.domain.FtpPo;
import org.springframework.stereotype.Component;

/**
 * @author 徐旭
 * @data 2018/6/12 15:48
 */
@Component
public interface FtpMapper {
    /**
     * 描述：获取Ftp的编号
     * @return
     */
    String createFtpNo();

    /**
     * 描述：新增Ftp记录
     * @param ftpPo
     * @return
     */
    void addFtp(FtpPo ftpPo);
}
