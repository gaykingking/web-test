package com.ls.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author 18011618
 * @Description
 * @Date 16:38 2018/7/5
 * @Modify By
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
 
    private static final String logExceptionFormat = "Capture Exception By GlobalExceptionHandler: Code: %s Detail: %s";
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 
 
    //数据效验异常 (针对处理validation 框架)
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception ex) {
        return validationDataResultFormat(14, ex);
    }
 
    //记录日志
    private <T extends Throwable> void log(Integer status, T exception) {
        exception.printStackTrace();
        log.error(String.format(logExceptionFormat, status, exception.getMessage()));
    }
 
 
    private <T extends Throwable> DataResult validationDataResultFormat(Integer status, T exception) {
        log(status, exception);  // 记录日志
        BindingResult bindResult = null;
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        } else {
            msg = "系统繁忙，请稍后重试...";
        }
        return DataResult.build(status, msg);
    }

    @ResponseBody

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(Exception.class)

    public DataResult handleException(Exception ex) {

        if (ex instanceof BusinessException) {

            log.warn(ex.getMessage(), ex);

            BusinessException businessException = (BusinessException) ex;

            return new DataResult(businessException.getCode(), businessException.getMessage(),null);

        } else {

            log.error(ex.getMessage(), ex);

            return new DataResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),null);

        }

    }
}

