package com.uuzz.zj;

import com.uuzz.zj.bean.ServerSetting;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tomcat启动入口
 *
 * @author zj
 * @time 2018-8-30
 */
public class Server {

    public static void main(String[] args) {

        try {
            System.out.println("tomcat is starting!");
            ServerSocket serverSocket = new ServerSocket(ServerSetting.PORT);
            ExecutorService executorService = Executors.newFixedThreadPool(ServerSetting.MAX_CONNECTIONS);
            while (true) {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(ServerSetting.TIMEOUT);
                executorService.execute(new SocketProcessor(socket));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
