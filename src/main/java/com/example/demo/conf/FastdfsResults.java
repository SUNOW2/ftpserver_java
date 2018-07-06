package com.example.demo.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 徐旭
 * @data 2018/6/11 16:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FastdfsResults {
    private Boolean success;
    private String error;
    private Boolean reset;
    private Result result;
    public FastdfsResults(String error, Boolean reset) {
        this.error = error;
        this.reset = reset;
    }

    public FastdfsResults(Boolean success, String name, String uri) {
        this.success = success;
        this.result = new Result(name, uri);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Result {
        private String name;
        private String uri;
    }
}
