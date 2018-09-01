package com.zhanghe.stockSimulating.Institution.exchangeCentor.OrderQueue;

import com.zhanghe.stockSimulating.Util.Enum.WorkState;
import com.zhanghe.stockSimulating.facade.bean.Order;

import java.util.concurrent.*;

public class OrderProcessThreadPoolManager {
    /* concurrent thread pool setting */
    private WorkState state = WorkState.Closing;
    private final static int CORE_POOL_SIZE = 2;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;

    public OrderProcessThreadPoolManager() {
    }

    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE));


    public void startProcess(){
        state = WorkState.Working;
        OrderProcessThread thread = null;
        new Thread(new OrderProcessThread(OrderQueueManager.getInstance().getWaitedBuyOrdersQueue(),OrderQueueManager.getInstance().getWaitedSellOrdersQueue())).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OrderProcessThread thread = null;
//                thread = new OrderProcessThread(OrderQueueManager.getInstance().getWaitedBuyOrdersQueue(),OrderQueueManager.getInstance().getWaitedSellOrdersQueue());
//
////                while (state.isValue()){
////                    thread = new OrderProcessThread(OrderQueueManager.getWaitedBuyOrdersQueue(),OrderQueueManager.getWaitedSellOrdersQueue());
////                    threadPool.execute(thread);
////                }
//            }
//        }).start();
    }
}
