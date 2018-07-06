package com.example.demo.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 徐旭
 * @data 2018/6/11 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    /**
     * 状态：ok 成功，fail 失败
     */
    private String result;

    /**
     * 状态码
     */
    private Integer rescode;

    /**
     * 备注原因
     */
    private String msg;

    /**
     * 返回对象
     */
    private T data;
}
