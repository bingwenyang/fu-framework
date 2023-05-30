package com.fu.nettywebsocket.controller;

import com.fu.core.model.Result;
import com.fu.zk.client.ZookeeperClient;
import com.fu.zk.entity.ZkNodeDetail;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 测试Curator调到分布式锁
 */
@RestController
public class CuratorController {


    @Autowired
    ZookeeperClient curatorUtil;


    /**
     * 测试节点是否存在
     *
     * @param path 关键路径
     * @return
     */
    @PostMapping("curator/node/exist")
    public Result<Boolean> checkNodeExist(String path) {
        return Result.ok(curatorUtil.exists(path));
    }

    /**
     * 查询所有子节点
     *
     * @param path 关键路径
     * @return
     */
    @GetMapping("curator/node/list")
    public Result<List<String>> getChildNodes(String path) {
        return Result.ok(curatorUtil.getChildNodes(path));
    }

    /**
     * 创建新的节点
     *
     * @param path 关键路径
     * @return
     */
    @GetMapping("curator/node/create")
    public Result<Boolean> createNode(String path) {
        return Result.ok(curatorUtil.createNode(path, null, CreateMode.EPHEMERAL, ZooDefs.Ids.OPEN_ACL_UNSAFE));
    }

    /**
     * 更新节点内容
     *
     * @param path 关键路径
     * @return
     */
    @PutMapping("curator/node/update")
    public Result<Boolean> updateNodeContent(String path) {
        return Result.ok(curatorUtil.updateNodeContent(path, "test1"));
    }

    /**
     * 读取节点详情
     *
     * @param path 关键路径
     * @return
     */
    @PutMapping("curator/node/detail")
    public Result<ZkNodeDetail> readNode(String path) {
        return Result.ok(curatorUtil.readNode(path));
    }

    /**
     * 删除节点
     *
     * @param path 关键路径
     * @return
     */
    @DeleteMapping("curator/node/delete")
    public Result<Boolean> deleteNode(String path) {
        return Result.ok(curatorUtil.deleteNode(path));
    }

    /**
     * 监听数据变化
     *
     * @param path 关键路径
     * @return
     */
    @PostMapping("curator/node/watch")
    public Result watchEvent(String path) {
        curatorUtil.watchEvent(path);
        return Result.ok();
    }
}
