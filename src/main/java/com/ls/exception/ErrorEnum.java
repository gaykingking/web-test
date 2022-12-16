package com.ls.exception;

public enum ErrorEnum {

    /*

     * 错误信息

     * */

    E_20011(20011, "保存订单失败！"),
    E_20012(20012, "上传失败！")
    ;

    private Integer errorCode;

    private String errorMsg;

    ErrorEnum(Integer errorCode, String errorMsg) {

        this.errorCode = errorCode;

        this.errorMsg = errorMsg;

    }

    public Integer getErrorCode() {

        return errorCode;

    }

    public String getErrorMsg() {

        return errorMsg;

    }
}