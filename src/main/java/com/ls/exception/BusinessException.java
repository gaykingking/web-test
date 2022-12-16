package com.ls.exception;

public class BusinessException extends RuntimeException {

private static final long serialVersionUID = 1L;

private Integer code;
private String msg;
/**

* @param errorEnum 以错误的ErrorEnum做参数

*/

public BusinessException(ErrorEnum errorEnum) {

super(errorEnum.getErrorMsg());

this.code = errorEnum.getErrorCode();

}
public BusinessException(String msg){
    super(msg);
}
public Integer getCode() {

return code;

}

public void setCode(Integer code) {

this.code = code;

}

}