package com.uuzz.zj;

import com.uuzz.zj.bean.ServerSetting;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Objects;

/**
 * Socket处理器
 *
 * @author zj
 * @time 2018-8-30
 */
public class SocketProcessor implements Runnable {

    /**
     * socket链接对象
     */
    protected Socket socket;

    public SocketProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            try {
                Request request = new Request(socket.getInputStream());
                Response response = new Response(socket.getOutputStream());
                if (!Objects.equals(request.getServletContext(), ServerSetting.SERVLET_CONTEXT)) {
                    return;
                }
                Servlet servlet = WebXmlLoader.matchServlet(request.getUrl());
                if (servlet == null) {
                    String res = Response.header + "no servlet can reduce this request!";
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(res.getBytes());
                    outputStream.flush();
                    outputStream.close();
                } else {
                    servlet.service(request, response);
                }
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
