package com.uuzz.zj;

import com.alibaba.fastjson.JSONObject;
import com.uuzz.zj.bean.ServletContainer;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * WebXmlLoader unit case
 *
 * @author zj
 * @time 2018-8-30
 */
public class WebXmlLoaderTest extends TestCase {

    @Test
    public void testLoad() throws Exception {
        ServletContainer container = WebXmlLoader.fetchServletContainer();
        System.out.println(JSONObject.toJSONString(container));
    }
}