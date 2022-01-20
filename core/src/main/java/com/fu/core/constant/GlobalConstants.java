package com.fu.core.constant;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * 全局常量
 *
 * @see
 * @since JDK1.8
 */
public class GlobalConstants {

    private GlobalConstants() {
    }


    public static final String DEFAULT_CHAR = "UTF-8";
    public static final Charset UTF8 = StandardCharsets.UTF_8;

    public static final Integer DEFAULT_CAPACITY = 16;

    public static final String REDIS_DELIMITER = ":";

    /**
     * 日志追踪id
     */
    public static final String TRACE_ID = "traceId";

    /**
     * id
     */
    public static final String ID = "id";

    /**
     * 父id
     */
    public static final String P_ID = "pid";


    /**
     * 成功返回码
     */
    public static final int SUCCESS = 0;
    /**
     * 默认失败返回码 未知错误
     */
    public static final int FAIL = -1;

    public static final String SUCCESS_MSG = "SUCCESS";
    public static final String FAILED_MSG = "FAILED";

    /**
     * 执行器
     */
    public static final String GLOBAL_EXECUTOR = "analysisGlobalExecutor";

    /**
     *
     * 分布式锁相关
     */
    public static final String ZOOKEEPER = "zookeeper";
    public static final String REDIS = "redis";


}
