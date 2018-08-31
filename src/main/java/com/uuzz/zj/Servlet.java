package com.uuzz.zj;

/**
 * servlet
 *
 * @author zj
 * @time 2018-8-30
 */
public abstract class Servlet {

    /**
     * 请求方式
     */
    public enum Method {
        /**
         * post
         */
        POST("post"),
        /**
         * get
         */
        GET("get");

        private String mark;

        Method(String mark) {
            this.mark = mark;
        }

        public String getMark() {
            return mark;
        }

        /**
         * 获取对应的请求方式
         *
         * @param method
         * @return
         */
        public static Method findMethod(String method) {
            for (Method m : Method.values()) {
                if (m.getMark().equalsIgnoreCase(method)) {
                    return m;
                }
            }
            return POST;
        }
    }

    /**
     * 模板方法
     *
     * @param request
     * @param response
     */
    public void service(Request request, Response response) {

        if (request.getMethod() == Method.POST) {
            doPost(request, response);
        } else {
            doGet(request, response);
        }
    }

    /**
     * get
     *
     * @param request
     * @param response
     */
    public abstract void doGet(Request request, Response response);

    /**
     * post
     *
     * @param request
     * @param response
     */
    public abstract void doPost(Request request, Response response);

}
