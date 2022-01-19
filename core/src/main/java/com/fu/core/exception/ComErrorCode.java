package com.fu.core.exception;


public enum ComErrorCode implements IError {
    /**
     * 常见错误
     */
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),

    LOGIN_ERROR(502, "账号密码错误"),
    MOBILE_ERROR(502, "手机号码错误"),
    LOGIN_ERROR_S(503, "登陆失败"),


    STOCK_NOT_ENOUGH(504, "库存不足"),
    GOODS_NOT_EXIST(506, "商品不存在"),
    GOODS_HAS_GOT(505, "已经抢购过了"),


    ERROR_SQL(801, "非法字符串");
    private Integer code;
    private String msg;

    ComErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
