package basic.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        singleThread();
    }

    public static void singleThread()  throws Exception {
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
        System.out.println("finish");
        executor.shutdown();
    }

    public static void multiThread()  throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2,
            r -> {
                Thread t = new Thread(r, "Thead");
                return t;
            });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(IntStream.range(0, 2)
            .mapToObj(i -> CompletableFuture.supplyAsync(FutureDemo::fetchPrice, executor))
            .toArray(CompletableFuture[]::new));
        voidCompletableFuture.get();
        executor.shutdown();
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
