package basic.zk.watcher;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class TestZookeeperWatcher {
    private static Logger logger=Logger.getLogger(TestZookeeperWatcher.class);

    /** 信号量，阻塞程序执行，用于等待zookeeper连接成功，发送成功信号 */
    private CountDownLatch countDown=new CountDownLatch(1);

    public void test() throws InterruptedException {
        ManyWatcherDefault defaultWatcher = new ManyWatcherDefault(countDown);
        ZooKeeper zkClient=null;
        try {
            zkClient=new ZooKeeper("192.168.26.11:30860",10000,defaultWatcher);
            //建立连接之前一直阻塞
            countDown.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("=====实验1测试同一个节点注册多个监听=======");
        String path="/test2";
        ManyWatcherA a = new ManyWatcherA(zkClient, path);
        ManyWatcherB b = new ManyWatcherB(zkClient, path);
        try {
            //此时/test节点不存在，下面两个方法都是返回null
            zkClient.exists(path, a);
            zkClient.getChildren(path, b);
            //下面创建/test节点，会触发A,B两个监听
            zkClient.create(path, "testValue".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        logger.info("=====实验2测试getData()方法注册监听，NodeCreated是否可以监听到=====");
        path="/test222";
        ManyWatcherA a22 = new ManyWatcherA(zkClient, path);
        ManyWatcherB b22 = new ManyWatcherB(zkClient, path);
        try {
            zkClient.create(path, "test1Value".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            byte[] data = zkClient.getData(path, a22, null);
            System.out.println(new String(data));
            zkClient.getData(path, b22, null);
            zkClient.setData(path, "test222Value".getBytes(),-1 );
            Thread.sleep(1000);
            zkClient.setData(path, "test222Value".getBytes(),-1 );

            //下面创建/test222节点，测试监听a22，b22是否被触发
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Thread.sleep(5000);

    }

    /**
     * 这是默认的watcher实现。
     */
    static class ManyWatcherDefault implements Watcher {
        private static final Logger logger=Logger.getLogger(ManyWatcherDefault.class);

        private CountDownLatch countDown;

        public ManyWatcherDefault(CountDownLatch countDown){
            this.countDown=countDown;
        }

        @Override
        public void process(WatchedEvent event) {
            KeeperState keeperState = event.getState();
            EventType eventType = event.getType();
            if(KeeperState.SyncConnected.equals(keeperState)){
                if(EventType.None.equals(eventType)){
                    //连接建立成功，则释放信号量，让阻塞的程序继续向下执行
                    countDown.countDown();
                    logger.info("=========默认监听到None事件：keeperState = "
                                    + keeperState + "  :  eventType = " + eventType);
                }
            }
        }
    }

    static class ManyWatcherA implements Watcher {
        private static final Logger logger=Logger.getLogger(ManyWatcherA.class);

        private ZooKeeper zkClient;
        /** 监控的路径 */
        private String watchPath;

        public ManyWatcherA(ZooKeeper zkClient,String watchPath){
            this.zkClient=zkClient;
            this.watchPath=watchPath;
        }

        @Override
        public void process(WatchedEvent event) {
            try {
                zkClient.exists(watchPath, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            KeeperState keeperState = event.getState();
            EventType eventType = event.getType();
            //当前事件发生的path
            String path = event.getPath();
            logger.info("=========ManyWatcherA监听到"+path+"地址发生事件：keeperState = "
                            + keeperState + "  :  eventType = " + eventType);
        }
    }

    static class ManyWatcherB implements Watcher {
        private static final Logger logger=Logger.getLogger(ManyWatcherB.class);

        private ZooKeeper zkClient;
        /** 监控的路径 */
        private String watchPath;

        public ManyWatcherB(ZooKeeper zkClient,String watchPath){
            this.zkClient=zkClient;
            this.watchPath=watchPath;
        }

        @Override
        public void process(WatchedEvent event) {
            try {
                byte[] data=zkClient.getData(watchPath, this,null );
                System.out.println(new String(data));
            } catch (Exception e) {
                e.printStackTrace();
            }
            KeeperState keeperState = event.getState();
            EventType eventType = event.getType();
            //当前事件发生的path
            String path = event.getPath();
            logger.info("=========ManyWatcherB监听到"+path+"地址发生事件：keeperState = "
                            + keeperState + "  :  eventType = " + eventType);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestZookeeperWatcher t = new TestZookeeperWatcher();
        t.test();
    }
}
