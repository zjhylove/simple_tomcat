package com.uuzz.zj.bean;

import javax.xml.bind.annotation.*;

/**
 * servlet info
 *
 * @author zj
 * @time 2018-8-30
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "clz"})
@XmlRootElement(name = "servlet")
public class ServletInfo {

    /**
     * 名称
     */
    @XmlElement(name = "servlet-name")
    private String name;

    /**
     * 完整类名
     */
    @XmlElement(name = "servlet-class")
    private String clz;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }
}
