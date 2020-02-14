package com.hrw.njqwtest.base.net;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2020/01/17 16:16
 * @desc:
 */
public class DataFaultException extends Exception {

    private int errorCode;

    public DataFaultException(int errorCode, String message) {
        super(message);
        this.errorCode=errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
