package netty;

public class ClientJob {

    public  static boolean canStart=false;

    public static void test() throws InterruptedException {
        while (!canStart){
            Thread.sleep(2000);
            System.out.println("still can't start ");
        }
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            System.out.println(i);
        }
    }
}
