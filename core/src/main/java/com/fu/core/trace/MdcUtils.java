package com.fu.core.trace;


import com.fu.core.constant.GlobalConstants;
import com.fu.core.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Objects;
import java.util.concurrent.Callable;


public class MdcUtils {

    private static final Logger log = LoggerFactory.getLogger(MdcUtils.class);

    /**
     * 获取跟踪ID
     *
     * @return
     */
    public static String getAndSet(String key) {
        String traceId = MDC.get(key);
        if (Objects.isNull(traceId)) {
            traceId = ToolUtil.getUUID();
            MDC.put(key, traceId);
        }
        return traceId;
    }


    public static Trace getStoreTrace() {
        return Trace.link(getAndSet(GlobalConstants.TRACE_ID), getAndSet(GlobalConstants.P_ID), getAndSet(GlobalConstants.ID));

    }

    public static void run(Runnable runnable, Trace trace) {
        Trace next = trace.next();
        next.store();
        log.debug("父节点[{}],子节点[{}]", trace, next);
        try {
            // 执行异步操作
            runnable.run();
        } finally {
            // 清空MDC内容
            MDC.clear();
        }
    }

    /**
     * spring @Ascy 调用的是该方法
     *
     * @param task
     * @param trace
     * @param <T>
     * @return
     */
    public static <T> T call(Callable<T> task, Trace trace) {
        T result = null;
        Trace next = trace.next();
        next.store();
        log.debug("父节点[{}],子节点[{}]", trace, next);
        try {
            // 执行异步操作
            result = task.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 清空MDC内容
            MDC.clear();
        }
        return result;
    }
}
