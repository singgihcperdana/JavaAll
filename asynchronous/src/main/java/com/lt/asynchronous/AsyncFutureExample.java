package com.lt.asynchronous;

import java.util.concurrent.*;

/**
 * @author liangtao
 * @Date 2020/8/31
 **/
public class AsyncFutureExample {

    private static final int AVALIABLE_PROCESSORS=Runtime.getRuntime().availableProcessors();
    private final static ThreadPoolExecutor POOL_EXECUTOR=new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
            AVALIABLE_PROCESSORS*2,1, TimeUnit.MINUTES,new LinkedBlockingQueue<>(5));
    public static String doSomethingA() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");

        return "TaskAResult";
    }

    public static String doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB---");
        return "TaskBResult";

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 1.创建future任务
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            String result = null;
            try {
                result = doSomethingA();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 2.开启异步单元执行任务A
//        Thread thread = new Thread(futureTask, "threadA");
//        thread.start();
        POOL_EXECUTOR.execute(futureTask);

        // 3.执行任务B
        String taskBResult = doSomethingB();

        // 4.同步等待线程A运行结束
        String taskAResult = futureTask.get();

        // 5.打印两个任务执行结果
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);

    }
}