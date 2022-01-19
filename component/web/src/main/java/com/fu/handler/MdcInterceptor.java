package com.fu.handler;

import com.fu.core.constant.GlobalConstants;
import com.fu.core.trace.Trace;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志链路追踪
 */
@Component
public class MdcInterceptor implements HandlerInterceptor {

    /**
     * MDC注入追踪ID
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Trace currentTrace;
        String traceId = request.getHeader(GlobalConstants.TRACE_ID);
        // 未设置参数
        if (StringUtils.isEmpty(traceId)) {
            currentTrace = Trace.first();
        } else {
            //如果手动设置参数
            String pid = request.getHeader(GlobalConstants.P_ID);
            String id = request.getHeader(GlobalConstants.ID);
            currentTrace = Trace.link(traceId, pid, id).next();
        }
        currentTrace.store();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 清除MDC内容
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }
}
