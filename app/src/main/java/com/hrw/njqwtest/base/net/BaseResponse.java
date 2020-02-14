package com.hrw.njqwtest.base.net;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 14:16
 * @desc:
 */
public class BaseResponse<T> {

    /**
     * httpCode : 200
     * flag : false
     * code : null
     * msg : 该用户不存在，请与管理员联系
     * data : null
     */

    private String httpCode;
    private boolean flag;
    private int code;
    private String msg;
    private T data;

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
