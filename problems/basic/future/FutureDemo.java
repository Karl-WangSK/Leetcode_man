package basic.future;

import scala.Int;
import sun.applet.Main;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2,
            r -> {
                Thread t = new Thread(r, "async_compact_thread");
                return t;
            });
        // 创建异步执行任务:
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(FutureDemo::fetchPrice, executor);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
        executor.shutdown();
    }

    public static void multiThread()  throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2,
            r -> {
                Thread t = new Thread(r, "async_compact_thread");
                return t;
            });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(IntStream.range(0, 2)
            .mapToObj(i -> CompletableFuture.supplyAsync(FutureDemo::fetchPrice, executor))
            .toArray(CompletableFuture[]::new));
        voidCompletableFuture.get();
    }


    static int fetchPrice() {
        int num = 0;
        for (int i = 0; i < 5; i++) {
            num +=i ;
            System.out.println(num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return num;
    }
}
