package com.uuzz.zj.bean;

import java.util.ResourceBundle;

/**
 * 服务配置信息
 *
 * @author zj
 * @time 2018-8-30
 */
public class ServerSetting {

    /**
     * 资源绑定对象
     */
    private static volatile ResourceBundle setting;

    /**
     * 所绑定的属性文件名称
     */
    private static final String PROPERTY_FILE_NAME = "application";

    /**
     * 应用上下文
     */
    public static final String SERVLET_CONTEXT = getCfgVal("server.servlet-context");

    /**
     * 端口
     */
    public static final int PORT = Integer.valueOf(getCfgVal("server.port"));

    /**
     * 支持最大连接数
     */
    public static final int MAX_CONNECTIONS = Integer.valueOf(getCfgVal("server.max-connections"));

    /**
     * 超时时间
     */
    public static final int TIMEOUT = Integer.valueOf(getCfgVal("server.timeout"));


    /**
     * 根据key值，获取对应的value值
     *
     * @param key String
     * @return value - String
     */
    private static String getCfgVal(String key) {

        return getResource().getString(key);
    }

    /**
     * 单例获取
     *
     * @return
     */
    private static ResourceBundle getResource() {

        if (setting == null) {
            synchronized (ServerSetting.class) {
                if (setting == null) {
                    setting = ResourceBundle.getBundle(PROPERTY_FILE_NAME);
                }
            }
        }
        return setting;
    }

}
