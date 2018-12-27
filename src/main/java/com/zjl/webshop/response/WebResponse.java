package com.zjl.webshop.response;/**
 * @Auther: bee
 * @Date: 2018/11/27 09:19
 * @Description:
 */

import org.springframework.stereotype.Component;

/**
 *@ClassName WebResponse
 *@Description 统一响应格式
 *@Author zjl
 *Date 2018/11/27 9:19
 *@Version 1.0
 **/
@Component
public class WebResponse {
    /**状态码*/
    private int code;
    /**返回数据*/
    private Object data;
    /**信息*/
    private String msg;

    public  WebResponse ok(Object data){
        this.code = 200;
        this.data = data;
        this.msg = "请求成功";
        return this;
    }

    public WebResponse error(int code,Object data,String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
