import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by Drake on 2018/3/11.
 */
 public  class  StockExchangeCentor  {

    private final static int CORE_POOL_SIZE = 2;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 50;
    // 消息缓冲队列
    Queue<Order> cacheQueue = new LinkedList<Order>();


    private String exChangeCentorName;
    private List<Stock> stocks;
    private static StockExchangeCentor stockExchangeCentor;
    private Queue<Order> queue;
    private Queue<Order> qProcess;
    private Queue<Order> tempQueue;
    private WorkState inProcessState;
    private WorkState outProcessStae;
    private WorkState workState;
    private InProcessThread inProcessThread;
    private OutProcessThread outProcessThread;


    final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("太忙了,把该订单交给调度线程池逐一处理");
            cacheQueue.offer(((OrderProcessThread)r).getOrder());
        }
    };
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
            TimeUnit.SECONDS, new ArrayBlockingQueue(WORK_QUEUE_SIZE), this.handler);



    static {
        stockExchangeCentor = new StockExchangeCentor("NewYork");
    }
    public StockExchangeCentor(String exChangeCentorName) {
        queue = new LinkedList<Order>();
        qProcess = new LinkedList<Order>();
        inProcessThread = new InProcessThread();
        outProcessThread = new OutProcessThread();
        this.exChangeCentorName = exChangeCentorName;
    }

    public  boolean pushOrder(Order order){
        synchronized (queue) {
            queue.add(order);
        }
        return  true;
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
            tempQueue = new LinkedList<Order>();
            try {
                while (inProcessState == WorkState.Working) {
                    if (!queue.isEmpty()) {
                        synchronized (queue) {
                            while (!queue.isEmpty()) {
                                tempQueue.add(queue.remove());
                               // System.out.println("inProcessThread queue size"+queue.size());
                            }
                         //   notify();

                        }
                        while (!tempQueue.isEmpty()) {
                            //System.out.println("current tempQueue size:"+tempQueue.size());
                            OrderProcessThread orderProcessThread = new OrderProcessThread();
                            orderProcessThread.setOrder(tempQueue.poll());
                            threadPool.execute(orderProcessThread);
                        }
                    }
                    synchronized (qProcess){
                        if(qProcess.isEmpty()){
                            while (!tempQueue.isEmpty()){
                                qProcess.add(tempQueue.remove());
                                //System.out.println("inProcessThread qProcess size"+queue.size());
                            }
                           // notify();
                        }
                    }
                    //wait();
                }
            } catch (Exception e) {

            }


        }
    }
    private class OutProcessThread extends Thread {
        public void run() {
            System.out.println("OutProcessThread start");
            Order order;
            try {
                while (outProcessStae == WorkState.Working) {
                    if (!qProcess.isEmpty()) {
                        synchronized (qProcess) {
                            while (!qProcess.isEmpty()) {
                                System.out.println("OutProcessThread: "+queue.remove());
                            }

                        }


                    }
                   // wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    public static void start(){
        StockExchangeCentor stockExchangeCentor = getInstance();


        stockExchangeCentor.setWorkState( WorkState.Working);
        stockExchangeCentor.setInProcessState( WorkState.Working);
        stockExchangeCentor.setOutProcessStae( WorkState.Working);
        //stockExchangeCentor.outProcessThread.start();
        stockExchangeCentor.inProcessThread.start();
    }
    public static  StockExchangeCentor getInstance(){
        if(stockExchangeCentor == null) {
            synchronized (StockExchangeCentor.class) {
                stockExchangeCentor = new StockExchangeCentor("NewYork");
            }
        }
        return stockExchangeCentor;
    }
    public Stock getStock(int id){
        for (Stock s:stocks
             ) {
            if(s.getId() == id)
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
    public void setQueue(Queue<Order> queue) {
        this.queue = queue;
    }
    public Queue<Order> getQueue() {
        return queue;
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
