package com.fu.core.trace;


import com.fu.core.constant.GlobalConstants;
import com.fu.core.util.UUIDUtil;
import org.slf4j.MDC;


/**
 * 日志追踪对象
 */
public class Trace {
    /**
     * 追踪ID，链路上所有节点都携带这个ID
     */
    private String tid;
    /**
     * 父节点ID
     */
    private String pid;
    /**
     * 节点ID
     */
    private String id;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param tid 追踪id
     * @param pid 父节点id
     * @param id  节点id
     * @return
     */
    private static Trace build(String tid, String pid, String id) {
        Trace trace = new Trace();
        trace.setId(id);
        trace.setPid(pid);
        trace.setTid(tid);
        return trace;
    }

    /**
     * 第一个节点
     *
     * @return 第一个节点
     */
    public static Trace first() {
        String tid = UUIDUtil.getUUID();
        String pid = "0";
        String id = UUIDUtil.getUUID();
        return build(tid, pid, id);

    }


    /**
     * 链路节点
     *
     * @param tid 追踪id
     * @param pid 父节点id
     * @param id  当前追踪节点的ID
     * @return 中间链路节点
     */
    public static Trace link(String tid, String pid, String id) {
        return build(tid, pid, id);
    }


    /**
     * 下一个节点
     *
     * @return 下一个追踪节点
     */
    public Trace next() {
        String tid = this.tid;
        String pid = this.id;
        String id = UUIDUtil.getUUID();
        return build(tid, pid, id);
    }

    /**
     * 保存到MDC
     */
    public void store() {
        MDC.put(GlobalConstants.TRACE_ID, tid);
        MDC.put(GlobalConstants.ID, id);
        MDC.put(GlobalConstants.P_ID, pid);
    }


    @Override
    public String toString() {
        return "tid=" + this.tid + ",id=" + this.id + ",pid=" + this.pid;
    }
}
