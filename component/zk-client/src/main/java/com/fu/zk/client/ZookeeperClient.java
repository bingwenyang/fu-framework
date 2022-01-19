package com.fu.zk.client;


import com.fu.zk.entity.ZkNodeDetail;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: zookeeper工具类
 */
public class ZookeeperClient {
    private static final Logger log = LoggerFactory.getLogger(ZookeeperClient.class);

    private final CuratorFramework curatorFramework;

    public ZookeeperClient(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }


    /**
     * 创建新的节点
     *
     * @param path        关键路径
     * @param initContent 节点的初始化内容
     * @param createMode  节点类型 PERSISTENT/PERSISTENT_SEQUENTIAL/EPHEMERAL/EPHEMERAL_SEQUENTIAL:持久/持久有序/临时/临时有序
     * @param aclList     权限列表 ZooDefs.Ids.OPEN_ACL_UNSAFE/CREATOR_ALL_ACL/READ_ACL_UNSAFE：完全开放/只有创建者可操作/只能读取
     * @return true/false
     */
    public boolean createNode(String path, String initContent, CreateMode createMode, ArrayList<ACL> aclList) {
        try {
            curatorFramework.create().creatingParentsIfNeeded()
                    .withMode(createMode)
                    .withACL(aclList)
                    .forPath(path, initContent == null ? "".getBytes() : initContent.getBytes());
            return true;
        } catch (Exception e) {
            log.error("创建节点失败,{},error:", path, e);
            return false;
        }
    }

    /**
     * 判断节点是否存在
     *
     * @param path 关键路径
     * @return true/false
     */
    public boolean exists(String path) {
        try {
            Stat stat = curatorFramework.checkExists().forPath(path);
            return stat != null;
        } catch (Exception e) {
            log.error("判断节点是否存在失败,{},error:", path, e);
            return false;
        }
    }

    /**
     * 查询所有子节点
     *
     * @param path 关键路径
     * @return List
     */
    public List<String> getChildNodes(String path) {
        List<String> childNodes = null;
        try {
            childNodes = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            log.error("查询子节点失败,{},error:", path, e);
        }
        return childNodes;
    }


    /**
     * 更新节点内容
     *
     * @param path          关键路径
     * @param updateContent 节点的更新内容
     * @return true/false
     */
    public boolean updateNodeContent(String path, String updateContent) {
        try {
            curatorFramework.setData()
                    .forPath(path, updateContent.getBytes());
            return true;
        } catch (Exception e) {
            log.error("更新节点内容失败,{},error:", path, e);
            return false;
        }
    }

    /**
     * 读取节点详情
     *
     * @param path 关键路径
     * @return ZkNodeDetail
     */
    public ZkNodeDetail readNode(String path) {
        // 记录该节点详细内容，stat=null表示节点不存在
        ZkNodeDetail zkNodeDetail = new ZkNodeDetail();
        Stat stat = new Stat();
        try {
            String content = new String(curatorFramework.getData()
                    .storingStatIn(stat)
                    .forPath(path));
            return zkNodeDetail.setStat(stat)
                    .setContent(content);
        } catch (Exception e) {
            log.error("获取节点内容失败,{},error:", path, e);
            return null;
        }
    }

    /**
     * 删除节点(注意暂时无法删除监听节点)
     *
     * @param path 关键路径
     * @return true/false
     */
    public boolean deleteNode(String path) {
        try {
            curatorFramework.delete()
                    .guaranteed()
                    .deletingChildrenIfNeeded()
                    .forPath(path);
            return true;
        } catch (Exception e) {
            log.error("删除节点失败,{},error:", path, e);
            return false;
        }
    }

    /**
     * 监听数据变化
     *
     * @param path 监听路径
     */
    public void watchEvent(String path) {
        // 笼统监听
        final NodeCache nodeCache = new NodeCache(curatorFramework, path);
        //调用start方法开始监听
        try {
            nodeCache.start();
        } catch (Exception e) {
            log.error("节点监听失败,{},error:", path, e);
        }
        //添加NodeCacheListener监听器
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                if (nodeCache.getCurrentData() != null) {
                    log.info("监听到事件变化，当前数据为{}", new String(nodeCache.getCurrentData().getData()));
                } else {
                    log.info("节点已经失效, path:{}", path);
                }

            }
        });
    }

}
