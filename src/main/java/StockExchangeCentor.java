import java.util.List;
import java.util.Queue;

/**
 * Created by Drake on 2018/3/11.
 */
 public  class  StockExchangeCentor implements  Runnable {
    private  String exChangeCentorName;
    private List<Stock> stocks;
    private static StockExchangeCentor stockExchangeCentor;
    private Queue<Order> queue;

    public synchronized boolean pushOrder(Order order){
        queue.add(order);
        return  true;
    }
    public void run() {

    }

    public StockExchangeCentor getInstance(){
        if(stockExchangeCentor == null)
        synchronized (stockExchangeCentor){
            stockExchangeCentor = new StockExchangeCentor();
        }
        return stockExchangeCentor;
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

}
