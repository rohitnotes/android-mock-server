package com.xpf.android.mock.entity;

/**
 * Created by x-sir on 2019-06-19 :)
 * Function:
 */
public class BaseEntity<T> {

    public T data;
    private String returnMsg = "";
    private String returnCode = "";
    private String resultCode = "";
    private String errCode = "";
    private String errCodeDes = "";

    public BaseEntity(BaseEntity r) {
        this.returnMsg = r.returnMsg;
        this.returnCode = r.returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }
}
