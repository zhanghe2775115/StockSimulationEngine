package com.zhanghe.stockSimulating.exchangeCentor;

import com.zhanghe.stockSimulating.Enum.WorkState;
import com.zhanghe.stockSimulating.stockBean.Order;
import com.zhanghe.stockSimulating.stockBean.Stock;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by Drake on 2018/3/11.
 */
public class StockExchangeCentor {
    private final static int CORE_POOL_SIZE = 2;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;
    // 消息缓冲队列
    private Queue<Order> cacheQueue = null;//= new ConcurrentLinkedDeque<Order>();
    private Queue<Order> queueImplA = new ConcurrentLinkedDeque<Order>();
    private Queue<Order> queueImplB = new ConcurrentLinkedDeque<Order>();
    private String exChangeCentorName;
    private List<Stock> stocks;
    private static StockExchangeCentor stockExchangeCentor;
    private Queue<Order> producerQueue;
    private Queue<Order> consumerQueue;
    private WorkState inProcessState;
    private WorkState outProcessStae;
    private WorkState workState;
    private InProcessThread inProcessThread;
    long count = 0;

    final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("转交调度线程池");
            cacheQueue.offer(((OrderProcessThread) r).getOrder());
        }
    };
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), this.handler);

    public StockExchangeCentor(String exChangeCentorName) {
        producerQueue = queueImplA;//new LinkedList<Order>();
        consumerQueue = queueImplB;
        cacheQueue = new ConcurrentLinkedDeque<Order>();
        inProcessThread = new InProcessThread();
        this.exChangeCentorName = exChangeCentorName;
    }

    public boolean pushOrder(Order order) {
        StringBuilder sb = new StringBuilder(order.getOrderID());
        sb.append(System.currentTimeMillis());
        order.setOrderID(sb.toString());
        producerQueue.add(order);
        return true;
    }

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
    final ScheduledFuture taskHandler = scheduler.scheduleAtFixedRate(new Runnable() {
        @Override
        public void run() {
            if (!cacheQueue.isEmpty()) {
                if (threadPool.getQueue().size() < WORK_QUEUE_SIZE) {
                    System.out.print("调度：");
                    Order order = cacheQueue.poll();
                    OrderProcessThread orderProcessThread = new OrderProcessThread();
                    orderProcessThread.setOrder(order);
                    threadPool.execute(orderProcessThread);
                }
                // while (msgQueue.peek() != null) {
                // }
            }
        }
    }, 0, 1, TimeUnit.SECONDS);


    private class InProcessThread extends Thread {
        public InProcessThread() {
            System.out.println("InProcessThread construct");
        }

        public void run() {
            Order order;
            System.out.print("InProcessThread start");
            try {
                while (inProcessState == WorkState.Working) {
                    if (!producerQueue.isEmpty()) {
                        while (!producerQueue.isEmpty()) {
                            //System.out.println("current tempQueue size:"+
                            //do swap
                            synchronized (producerQueue) {
                                Queue<Order> temp = producerQueue;
                                producerQueue = consumerQueue;
                                consumerQueue = temp;
                            }
                            OrderProcessThread orderProcessThread;
                            while (!consumerQueue.isEmpty()) {
                                orderProcessThread = new OrderProcessThread();
                                orderProcessThread.setOrder(consumerQueue.poll());

                                threadPool.execute(orderProcessThread);
                            }
                            sleep(500);
                        }
                    }
                }
            } catch (Exception e) {

            }


        }
    }

    public static void start() {
        StockExchangeCentor stockExchangeCentor = getInstance();
        stockExchangeCentor.setWorkState(WorkState.Working);
        stockExchangeCentor.setInProcessState(WorkState.Working);
        stockExchangeCentor.inProcessThread.start();
    }

    public static StockExchangeCentor getInstance() {
        if (stockExchangeCentor == null) {
            synchronized (StockExchangeCentor.class) {
                stockExchangeCentor = new StockExchangeCentor("NewYork");
            }
        }
        return stockExchangeCentor;
    }

    public Stock getStock(int id) {
        for (Stock s : stocks
        ) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    public String getExChangeCentorName() {
        return exChangeCentorName;
    }

    public void setExChangeCentorName(String exChangeCentorName) {
        this.exChangeCentorName = exChangeCentorName;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public void setProducerQueue(Queue<Order> producerQueue) {
        this.producerQueue = producerQueue;
    }

    public Queue<Order> getProducerQueue() {
        return producerQueue;
    }

    public WorkState getWorkState() {
        return workState;
    }

    public void setWorkState(WorkState workState) {
        this.workState = workState;
    }

    public WorkState getInProcessState() {
        return inProcessState;
    }

    public void setInProcessState(WorkState inProcessState) {
        this.inProcessState = inProcessState;
    }

    public WorkState getOutProcessStae() {
        return outProcessStae;
    }

    public void setOutProcessStae(WorkState outProcessStae) {
        this.outProcessStae = outProcessStae;
    }
}
