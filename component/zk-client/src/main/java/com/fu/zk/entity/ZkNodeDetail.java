package com.fu.zk.entity;

import org.apache.zookeeper.data.Stat;

/**
 * @Description: zk节点详情内容
 */
public class ZkNodeDetail {
    /**
     * 配置相关信息
     */
    Stat stat = new Stat();

    /**
     * 节点内容
     */
    String content;

    public ZkNodeDetail() {

    }

    public ZkNodeDetail setStat(Stat stat) {
        this.stat = stat;
        return this;
    }

    public ZkNodeDetail setContent(String content) {
        this.content = content;
        return this;
    }

    public Stat getStat() {
        return stat;
    }

    public String getContent() {
        return content;
    }
}
