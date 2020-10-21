package basic.netty;

public class Test {
    public static final Object lock= new Object();

    public  static  void test() throws InterruptedException {
        synchronized (lock) {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1000);
                System.out.println(i);
                System.out.println(NettyServerHandler.flag);
                if (!NettyServerHandler.flag){
                    lock.wait();
                }
            }
        }
    }
}
