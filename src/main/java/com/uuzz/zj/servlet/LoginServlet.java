package com.uuzz.zj.servlet;

import com.uuzz.zj.Request;
import com.uuzz.zj.Response;
import com.uuzz.zj.Servlet;

/**
 * 登录servlet
 *
 * @author zj
 * @time 2018-8-30
 */
public class LoginServlet extends Servlet {


    @Override
    public void doGet(Request request, Response response) {
        doPost(request, response);
    }

    @Override
    public void doPost(Request request, Response response) {
        try {
            String msg = Response.header + "欢迎登录";
            response.out.write(msg.getBytes());
            response.out.flush();
            response.out.close();
        } catch (Exception e) {
            throw new RuntimeException("登录发生异常，请联系管理员", e);
        }
    }
}
