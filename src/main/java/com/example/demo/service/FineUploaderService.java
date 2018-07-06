package com.example.demo.service;

/**
 * @author 徐旭
 * @data 2018/6/11 16:31
 */

import com.example.demo.conf.FastdfsResults;
import com.example.demo.conf.RequestParser;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * 注：静态方法和内部类未重构
 */
@Component
public interface FineUploaderService {
    void init() throws ServletException;

    void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void handleDeleteFileRequest(String uuid, HttpServletResponse resp) throws IOException;

    FastdfsResults uploadFile(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void writeFileForNonMultipartRequest(HttpServletRequest req, RequestParser requestParser) throws Exception;

    String writeFileForMultipartRequest(RequestParser requestParser) throws Exception;

    File mergeFiles(File outputFile, File partFile) throws IOException;

    File writeFile(InputStream in, File out, Long expectedFileSize) throws IOException;

    void writeResponse(PrintWriter writer, String failureReason, boolean isIframe, boolean restartChunking, RequestParser requestParser);
}
