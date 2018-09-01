package com.zhanghe.stockSimulating.Institution.exchangeCentor.OrderQueue;

import com.zhanghe.stockSimulating.Institution.exchangeCentor.StockExchangeCentor;
import com.zhanghe.stockSimulating.Util.Enum.OrderEnum;



public class OrderQueueManager {
    private static SortedOrderQueue[] waitedOrders;
    private static OrderQueueManager instance= null;
    private static final int BuyInto_ID = OrderEnum.BUYINTO.getId();
    private static final int SELL_ID = OrderEnum.SELL.getId();

    public  OrderQueueManager() {
        waitedOrders = new SortedOrderQueue[OrderEnum.MAX.getId()];
        waitedOrders[BuyInto_ID] = new SortedOrderQueue(OrderEnum.BUYINTO);
        waitedOrders[SELL_ID] = new SortedOrderQueue(OrderEnum.SELL);
    }
    public  SortedOrderQueue[]  getWaitedOrdersQueue(){
        return waitedOrders;
    }
    public  SortedOrderQueue getWaitedBuyOrdersQueue(){
        return waitedOrders[BuyInto_ID];
    }
    public  SortedOrderQueue  getWaitedSellOrdersQueue(){
        return waitedOrders[SELL_ID];
    }
    public  static OrderQueueManager getInstance(){
        if (instance == null) {
            synchronized (StockExchangeCentor.class) {
                instance = new OrderQueueManager();
            }
        }
        return instance;
    }
}
