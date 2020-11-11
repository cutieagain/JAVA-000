Week04 作业题目（周四）：

一个简单的代码参考：[strong_end] https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301/src/main/java/java0/conc0303/Homework03.java

1.（选做）把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。

2.（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？
写出你的方法，越多越好，提交到 Github。

```
//思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？
public class Homework03 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        new Homework03().solution1();
//        new Homework03().solution2();
//        new Homework03().solution3();
//        new Homework03().solution4();
//        new Homework03().solution5();
        new Homework03().solution6();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

    class FiboRunable implements Runnable{
        private int result = -1;
        public void run() {
            result = sum();
        }
        public int getResult() {//获取线程返回结果
            return result;
        }
    }

    class FiboCallable implements Callable{
        private int result = -1;
        public int getResult() {//获取线程返回结果
            return result;
        }
        public Object call() throws Exception {
            result = sum();
            return result;
        }
    }

    //主线程一直sleep等待值成功计算出
    private void solution1() throws InterruptedException {
        long start=System.currentTimeMillis();
        FiboRunable fiboRunable = new FiboRunable();
        Thread thread = new Thread(fiboRunable);
        thread.start();
        // 获取子线程的返回值：主线程等待法
        while (fiboRunable.getResult() == -1){
            Thread.sleep(10);
        }
        // 确保  拿到result 并输出
        System.out.println("solution1 异步计算结果为："+ fiboRunable.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    //join方法阻塞当前线程以等待子线程执行完毕
    private void solution2() throws InterruptedException {
        long start=System.currentTimeMillis();
        FiboRunable fiboRunable = new FiboRunable();
        Thread thread = new Thread(fiboRunable);
        thread.start();
        // 获取子线程的返回值：主线程等待法
        thread.join();
        // 确保  拿到result 并输出
        System.out.println("solution2 异步计算结果为："+ fiboRunable.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    //FutureTask
    private void solution3() throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        FiboCallable fiboCallable = new FiboCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(fiboCallable);
        Thread thread = new Thread(futureTask);
        thread.start();
        // 获取子线程的返回值：主线程等待法
        // 确保  拿到result 并输出
        System.out.println("solution3 异步计算结果为："+ futureTask.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    //线程池提交Callable
    private void solution4() throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        FiboCallable fiboCallable = new FiboCallable();
        Future future = executorService.submit(fiboCallable);
        // 获取子线程的返回值：主线程等待法
        // 确保  拿到result 并输出
        System.out.println("solution4 异步计算结果为："+ future.get());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        executorService.shutdown();
    }

    //Semaphore
    private void solution5() throws InterruptedException {
        final int[] result = {-1};
        long start=System.currentTimeMillis();
        final Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                result[0] = sum();
                semaphore.release();
            }
        });
        thread.start();
        semaphore.acquire();
        // 确保  拿到result 并输出
        System.out.println("solution1 异步计算结果为："+ result[0]);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    //Exchanger
    private void solution6() throws InterruptedException {
        /*final int[] result = {-1, -1};
        final Exchanger<Integer> exchanger = new Exchanger<Integer>();

        long start=System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                result[0] = sum();
                try {
                    result[0] = exchanger.exchange(result[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    result[1] = sum();
                    result[1] = exchanger.exchange(result[1]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();

        // 确保  拿到result 并输出
        System.out.println("solution1 异步计算结果为："+ result[0]);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");*/

        long start=System.currentTimeMillis();
        final int[] result = {-1, -1};
        final Exchanger<Integer> exchanger=new Exchanger<Integer>();
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.execute(new Runnable() {
            public void run() {
                result[0] = sum();
                try {
                    result[0]=exchanger.exchange(result[0]);
//                    System.out.println("交换后，result[0]="+result[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        /*threadPool.execute(new Runnable() {
            public void run() {
                try {
                    result[1]=exchanger.exchange(result[1]);
                    System.out.println("交换后，result[1]="+result[1]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/
        result[1]=exchanger.exchange(result[1]);
        threadPool.shutdown();
        System.out.println("solution1 异步计算结果为："+ result[1]);
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}

```

Week04 作业题目（周六）：

1.（选做）列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。
##### 1、CountDownLatch
允许一个或者多个线程等待其他线程完成操作
例：统计数据的时候，分段进行统计，之后进行合并汇总，需要一起输出的时候
##### 2、CyclicBarrier
让一组线程到达一个屏障时候阻塞，在最后一个线程到达的屏障的时候，所有被屏障拦截的线程才会运行
例：当1个线程运行的时候，不够触发开始条件，满足10个线程起来的时候进行下一步任务的执行
##### 3、Semaphore
Semaphore用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理地使用公共资源
数据库的连接只能有10个可以连接，可以使用Semaphore做流量控制
##### 4、Exchanger
Exchanger是一个用于线程间协作的工具类。Exchanger用于进行线程间的数据交换
例：可用于数据校对工作

2.（选做）请思考：什么是并发？什么是高并发？实现高并发高可用系统需要考虑哪些因素，对于这些你是怎么理解的？

3.（选做）请思考：还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决办法。

4.（必做）把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到 Github 上。
可选工具：xmind，百度脑图，wps，MindManage 或其他。学习笔记

