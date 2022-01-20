package com.fu.core.model;


import com.fu.core.constant.GlobalConstants;
import com.fu.core.exception.ComErrorCode;
import lombok.Data;

/**
 * 统一的接口返回对象
 */
@Data
public class Result<T> {

    /**
     * 结果返回码
     */
    private int code;
    /**
     * 结果信息描述
     */
    private String msg;
    /**
     * 结果数据
     */
    private T data;


    public Result() {
    }

    /**
     * 默认构造
     *
     * @param data
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    private static <T> Result<T> build(T data, int code, String msg) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }


    // region 默认构造

    public static <T> Result<T> ok() {
        return build(null, GlobalConstants.SUCCESS, GlobalConstants.SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data) {
        return build(data, GlobalConstants.SUCCESS, GlobalConstants.SUCCESS_MSG);
    }

    public static <T> Result<T> ok(T data, String msg) {
        return build(data, GlobalConstants.SUCCESS, msg);
    }

    public static <T> Result<T> failed() {
        return build(null, GlobalConstants.FAIL, GlobalConstants.FAILED_MSG);
    }

    public static <T> Result<T> failed(String msg) {
        return build(null, GlobalConstants.FAIL, msg);
    }

    public static <T> Result<T> failed(int code, String msg) {
        return build(null, code, msg);
    }

    public static <T> Result<T> failed(T data) {
        return build(data, GlobalConstants.FAIL, GlobalConstants.FAILED_MSG);
    }

    public static <T> Result<T> failed(T data, String msg) {
        return build(data, GlobalConstants.FAIL, msg);
    }

    /**
     * 失败返回
     *
     * @return
     */
    public static <T> Result<T> error(ComErrorCode respBeanEnum) {
        return build(null, respBeanEnum.getCode(), respBeanEnum.getMsg());

    }

    /**
     * 失败返回
     *
     * @return
     */
    public static <T> Result<T> error(ComErrorCode respBeanEnum, T object) {
        return build(object, respBeanEnum.getCode(), respBeanEnum.getMsg());

    }


}
