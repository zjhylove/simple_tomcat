package com.uuzz.zj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * request
 *
 * @author zj
 * @time 2018-830
 */
public class Request {

    /**
     * 请求方式
     */
    private Servlet.Method method;

    /**
     * 请求url
     */
    private String url;

    /**
     * 应用上下文
     */
    private String servletContext;


    public Request(InputStream in) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String reqStr = reader.readLine();
            if (reqStr == null || reqStr.trim().isEmpty()) {
                return;
            }
            String[] arr = reqStr.split(" ");
            this.method = Servlet.Method.findMethod(arr[0]);
            String[] split = arr[1].split("/");
            if (split.length <= 1) {
                return;
            }
            this.servletContext = split[1];
            this.url = arr[1].replace("/" + servletContext, "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Servlet.Method getMethod() {
        return method;
    }

    public void setMethod(Servlet.Method method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServletContext() {
        return servletContext;
    }

    public void setServletContext(String servletContext) {
        this.servletContext = servletContext;
    }
}
