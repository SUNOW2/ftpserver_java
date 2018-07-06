package com.example.demo.service.Impl;

import com.example.demo.dao.FtpMapper;
import com.example.demo.domain.FtpPo;
import com.example.demo.service.Ftpservice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * @author 徐旭
 * @data 2018/6/11 17:31
 */
@Service
@Slf4j
public class FtpServiceImpl implements Ftpservice {

    @Value("${ftp.address}")
    private String FTP_ADDRESS;

    @Value("${ftp.port}")
    private int FTP_PORT;

    @Value("${ftp.username}")
    private String FTP_USERNAME;

    @Value("${ftp.password}")
    private String FTP_PASSWORD;

    @Value("${ftp.basePath}")
    private String FTP_BASEPATH;

    @Value("${ftp.downloadIp}")
    private String FTP_DOWNLOADIP;

    @Autowired
    private FtpMapper ftpMapper;

    @Override
    @Transactional
    public String uploadFile(String originFileName, InputStream input) {

        String uri = "";
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return "连接FTP服务器失败";
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            ftp.makeDirectory(uuid);
            ftp.changeWorkingDirectory(uuid);
            String filePath = uuid + "/" + originFileName;
            log.info("文件路径=" + FTP_BASEPATH + uuid + "/" + originFileName);
            if(ftp.storeFile(originFileName, input)){
                FtpPo ftpPo = new FtpPo();
                ftpPo.setFtpNo(ftpMapper.createFtpNo());
                ftpPo.setOriginalFile(filePath);
                ftpPo.setFtpDate(new Date());
                log.info("ftpPo=" + ftpPo);
                ftpMapper.addFtp(ftpPo);
                uri = FTP_DOWNLOADIP + filePath;
            }
            input.close();
            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return uri;
    }
}
