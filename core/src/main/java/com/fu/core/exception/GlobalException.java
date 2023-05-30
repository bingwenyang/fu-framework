package com.fu.core.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局异常处理
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {
    private ComErrorCode respBeanEnum;

    private static final long serialVersionUID = 1L;
    private static final String EXCEPTION = "未知异常";
    protected String msg;
    protected int code;

    public GlobalException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public GlobalException(ComErrorCode respBeanEnum) {
        this.respBeanEnum = respBeanEnum;
    }
}
