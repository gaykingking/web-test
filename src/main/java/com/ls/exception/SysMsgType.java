package com.ls.exception;

/**
 * 系统信息类型
 * @author yaoning
 */
public enum SysMsgType {

    /**
     * 操作成功
     */
    OK(0,"操作成功"),
    /**
     *无效的参数
     */
    ERROR_PARAM(100,"无效的参数"),
    /**
     *业务异常
     */
    ERROR_BIZ(200,"业务异常"),
    /**
     *数据库异常
     */
    ERROR_DB(300,"数据库异常"),
    /**
     *其他异常
     */
    ERROR_WORKFLOW(400,"工作流异常"),
    /**
     *其他异常
     */
    ERROR_REMOTECALL(400,"远程接口调用失败"),
    /**
     * 授权认证失败
     */
    ERROR_USERNOTFUND(401,"授权认证失败"),
    /**
     *其他异常
     */
    ERROR_FILEUPLOAD(500,"文件上传异常"),
    /**
     *其他异常
     */
    ERROR_OTHER(900,"其他异常");


    /**
     * 信息编号
     */
    private final int code;

    /**
     * 信息描述
     */
    private final String desc;

     SysMsgType(final int code, final String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
