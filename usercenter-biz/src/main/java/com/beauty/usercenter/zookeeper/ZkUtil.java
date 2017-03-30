package com.beauty.usercenter.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class ZkUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZkUtil.class);

    private String zkAddress;

    private static final String ROOT_PATH = "/groups";

    private static final String ROOT_PATH_APP_NAME = "/groups/tradecenter";

    private static final int TIME_OUT = 3000;

    private ZooKeeper zooKeeper = null;

    public ZkUtil(String zkAddress) {
        this.zkAddress = zkAddress;
    }

    public void init() {
        if (StringUtils.isBlank(zkAddress)) {
            logger.error("zk地址为空");
            return;
        }
        try {
            if (null == zooKeeper) {
                // 初始化
                zooKeeper = new ZooKeeper(zkAddress, TIME_OUT, new Watcher() { // 监控所有被触发的事件
                    public void process(WatchedEvent event) {
                        logger.info("触发了{}事件，路径为{}！", new Object[] { event.getType(), event.getPath() });
                    }
                });
            }
        } catch (Exception e) {
            logger.error("init zookeeper fail, mes:{}", e);
        }
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean findLeader(String jobName) {
        if (null == zooKeeper) {
            logger.info("{}, zooKeeper为空", jobName);
            return false;
        }
        initNode(ROOT_PATH, zooKeeper);
        initNode(ROOT_PATH_APP_NAME, zooKeeper);
        String path = ROOT_PATH_APP_NAME + "/" + jobName;
        byte[] leader = null;
        try {
            leader = zooKeeper.getData(path, true, null);
        } catch (Exception e) {
            logger.error("{}, get path:{} data  fail, mes:{}", new Object[] { jobName, path, e.getMessage() });
        }
        if (null != leader) {
            // 不是Leader，已经有定时程序.
            logger.info("{}, this node is not leader, leader: {} is doing.",
                    new Object[] { jobName, new String(leader) });
        } else {
            String newLeader = null;
            try {
                newLeader = zooKeeper.create(path, InetAddress.getLocalHost().getHostAddress().getBytes(),
                        Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            } catch (Exception e) {
                logger.error("{},this node create path:{} fail, mes:{}",
                        new Object[] { jobName, path, e.getMessage() });
            }
            if (newLeader != null) {
                logger.info("this node can do the {} job, return true", jobName);
                return true;
            }
        }
        logger.info("this node can't do the {} job, return false", jobName);
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈初始化节点〉
     *
     * @param path
     * @param zk
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private static boolean initNode(String path, ZooKeeper zk) {
        try {
            if (null == zk.exists(path, true)) {
                zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                return true;
            }
        } catch (KeeperException e) {
            logger.error("initNode path:{}, KeeperException:{} ", new Object[] { path, e });
        } catch (InterruptedException e) {
            logger.error("initNode path:{}, InterruptedException:{} ", new Object[] { path, e });
        }
        return false;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param jobName
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void deleteNode(String jobName) {
        try {
            if (null != zooKeeper.exists(ROOT_PATH_APP_NAME + "/" + jobName, true)) {
                zooKeeper.delete(ROOT_PATH_APP_NAME + "/" + jobName, -1);
            }
        } catch (InterruptedException e) {
            logger.info("deleteNode path:{}, KeeperException:{} ", new Object[] { jobName, e });
        } catch (KeeperException e) {
            logger.info("deleteNode path:{}, KeeperException:{} ", new Object[] { jobName, e });
        }
    }
}
