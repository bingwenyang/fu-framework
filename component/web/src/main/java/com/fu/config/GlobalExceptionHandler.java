package com.fu.config;


import com.fu.core.exception.GlobalException;
import com.fu.core.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
  * 通用异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result ExceptionHandler(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getRespBeanEnum());
        } else {
            return Result.failed(e.getMessage());
        }
//        return Result.error(ComErrorCode.ERROR);
    }

}
