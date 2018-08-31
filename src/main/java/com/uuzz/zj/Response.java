package com.uuzz.zj;

import java.io.OutputStream;

/**
 * Response
 *
 * @author zj
 * @time 2018-8-30
 */
public class Response {

    /**
     * 输出流
     */
    public OutputStream out;

    /**
     * 响应头
     */
    public static final String header = "HTTP/1.1 200 \r\n" +
            "Content-Type: text/html;charset=UTF-8\r\n" +
            "\r\n";

    public Response(OutputStream out) {
        this.out = out;
    }
}
