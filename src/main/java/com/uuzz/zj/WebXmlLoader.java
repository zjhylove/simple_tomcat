package com.uuzz.zj;

import com.uuzz.zj.bean.ServletContainer;
import com.uuzz.zj.bean.ServletInfo;
import com.uuzz.zj.bean.ServletMapping;
import com.uuzz.zj.util.JaxbXmlUtil;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 读取web.xml配置信息
 *
 * @author zj
 * @time 2018-8-30
 */
public class WebXmlLoader {

    /**
     * web.xml默认路径
     */
    private static String WEB_XML_PATH = "web.xml";

    /**
     * 第一次成功创建servlet后缓存至此
     */
    private static final Map<String, Servlet> SERVLET_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取web.xml中所有servlet相关信息
     *
     * @return
     */
    public static ServletContainer fetchServletContainer() {
        return ServletContainerHolder.SERVLETS;
    }

    /**
     * 根据请求路径匹配对应的servlet
     *
     * @param url
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Servlet matchServlet(String url) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        if (SERVLET_CACHE.containsKey(url)) {
            return SERVLET_CACHE.get(url);
        }
        ServletContainer servlets = fetchServletContainer();
        ServletMapping matchMapping = servlets.getMappings().stream()
                .filter(mp -> Objects.equals(mp.getUrlPattern(), url))
                .findFirst().get();
        ServletInfo servletInfo = servlets.getServlets().stream()
                .filter(servlet -> Objects.equals(servlet.getName(), matchMapping.getName()))
                .findFirst().get();
        if (servletInfo == null) {
            return null;
        }
        Class<?> clz = Class.forName(servletInfo.getClz());
        Object servlet = clz.newInstance();
        if (!(servlet instanceof Servlet)) {
            throw new UnsupportedOperationException(String.format("%s为非Servlet实例本次拦截请求将不会起作用！", servlet.getClass().getName()));
        }
        SERVLET_CACHE.put(url, (Servlet) servlet);
        return (Servlet) servlet;
    }

    /**
     * 加载web.xml文件
     *
     * @return
     * @throws Exception
     */
    private ServletContainer load() {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(WEB_XML_PATH);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(in);
            String content = doc.asXML();
            return JaxbXmlUtil.convertToJavaBean(content, ServletContainer.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 单例加载web.xml
     */
    private static class ServletContainerHolder {
        private static final ServletContainer SERVLETS = new WebXmlLoader().load();
    }


}
