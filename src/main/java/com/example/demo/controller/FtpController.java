package com.example.demo.controller;

import com.example.demo.conf.FastdfsResults;
import com.example.demo.service.FineUploaderService;
import com.example.demo.service.Ftpservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 徐旭
 * @data 2018/6/11 13:32
 */
@RestController
@RequestMapping(value = "/ftp")
@Slf4j
public class FtpController extends BaseController {

//    @Autowired
//    private FtpUtil ftpUtil;

    @Autowired
    private FineUploaderService fineUploaderService;

    @Autowired
    private Ftpservice ftpservice;

    /**
     * 描述：此处没有结合FineUploader
     * @param
     * @return
     */
//    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
//
//        log.info("上传文件开始");
//        Boolean flag = false;
//        try {
//            log.info("fileName=" + file.getOriginalFilename());
//            flag = ftpUtil.uploadFile(file.getOriginalFilename(), file.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.info("上传文件失败");
//        }
//        log.info("上传文件结束");
//        if (flag == true) {
//            return getSuccessResult();
//        }
//        return getFailResult();
//    }

    /**
     * 描述：用户分片上传文件(FineUploader)，将上传信息存入数据库，并提供文件下载的uri，
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/uploadFile", method = {RequestMethod.POST, RequestMethod.GET})
    public FastdfsResults uploadFile(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return fineUploaderService.uploadFile(req, resp);
    }

    /**
     * 描述：用户上传文件，将上传信息存入数据库，并提供文件下载的uri
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFileTest", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadFileTest(@RequestParam("file") MultipartFile file) {
        String uri = "";
        try {
            uri = ftpservice.uploadFile(file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }
}
