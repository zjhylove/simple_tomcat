package com.uuzz.zj.bean;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 应用信息
 *
 * @author zj
 * @time 2018-8-30
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"servlets", "mappings"})
@XmlRootElement(name = "web-app", namespace = "http://java.sun.com/xml/ns/javaee")
public class ServletContainer {

    @XmlElement(name = "servlet")
    private List<ServletInfo> servlets = new LinkedList<>();

    @XmlElement(name = "servlet-mapping")
    private List<ServletMapping> mappings = new LinkedList<>();

    public List<ServletInfo> getServlets() {
        return servlets;
    }

    public void setServlets(List<ServletInfo> servlets) {
        this.servlets = servlets;
    }

    public List<ServletMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<ServletMapping> mappings) {
        this.mappings = mappings;
    }

}
