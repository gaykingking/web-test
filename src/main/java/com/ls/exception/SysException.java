package com.ls.exception;

/**
 * 系统异常类
 * @author yaoning
 */
public class SysException extends RuntimeException {

    private SysMsgType sysMsgType;

    public SysException(String msg) {
        super(msg);
        sysMsgType = SysMsgType.ERROR_BIZ;
    }

    public SysException(String msg, Throwable e) {
        super(msg, e);
        sysMsgType = SysMsgType.ERROR_OTHER;
    }

    public SysException(String msg, SysMsgType code) {
        super(msg);
        this.sysMsgType = code;
    }

    public SysException(String msg, SysMsgType code, Throwable e) {
        super(msg, e);
        this.sysMsgType = code;
    }



}
