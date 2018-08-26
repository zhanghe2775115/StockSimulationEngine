package com.zhanghe.stockSimulating.Institution.exchangeCentor.OrderQueue;

import com.zhanghe.stockSimulating.Util.Enum.OrderEnum;

public class OrderQueueManager {
    private static SortedOrderQueue[] waitedOrders;
    private final int PURCHASE_ID = OrderEnum.PURCHASE.getId();
    private final int SELL_ID = OrderEnum.SELL.getId();
    public OrderQueueManager() {
        waitedOrders = new SortedOrderQueue[OrderEnum.MAX.getId()];
        waitedOrders[PURCHASE_ID] = new SortedOrderQueue(OrderEnum.PURCHASE);
        waitedOrders[SELL_ID] = new SortedOrderQueue(OrderEnum.SELL);
    }
    public static  SortedOrderQueue[]  getWaitedOrdersQueue(){
        return waitedOrders;
    }

}
