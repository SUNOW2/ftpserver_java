package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author 徐旭
 * @data 2018/6/11 17:32
 */
@Service
public interface Ftpservice {
    String uploadFile(String originFileName, InputStream input);
}
