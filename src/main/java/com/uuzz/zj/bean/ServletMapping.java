package com.uuzz.zj.bean;

import javax.xml.bind.annotation.*;

/**
 * ServletMapping
 *
 * @author zj
 * @time 2018-8-30
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "urlPattern"})
@XmlRootElement(name = "servlet-mapping")
public class ServletMapping {

    /**
     * servlet 名称
     */
    @XmlElement(name = "servlet-name")
    private String name;

    /**
     * servlet 过滤路径
     */
    @XmlElement(name = "url-pattern")
    private String urlPattern;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
}
