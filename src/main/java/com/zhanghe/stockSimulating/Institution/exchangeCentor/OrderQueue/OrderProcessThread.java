package com.zhanghe.stockSimulating.Institution.exchangeCentor.OrderQueue;

import com.zhanghe.stockSimulating.facade.bean.Order;

public class OrderProcessThread implements Runnable {

    SortedOrderQueue mBuyintoQueue = null;
    SortedOrderQueue mSellQueue = null;

    public OrderProcessThread(SortedOrderQueue mBuyintoQueue, SortedOrderQueue mSellQueue) {
        this.mBuyintoQueue = mBuyintoQueue;
        this.mSellQueue = mSellQueue;
    }

    @Override
    public void run() {
        Order buyOrder = null;
        Order sellOrder = null;
        while (true) {
            buyOrder = mBuyintoQueue.getAndRemoveTopOrder();
            sellOrder = mSellQueue.getAndRemoveTopOrder();
            if (buyOrder == null || sellOrder == null){
                continue;
            }
            System.out.println("stock processing buyOrder" + buyOrder.getOrderID() + " sellOrder price:" + sellOrder.getOrderID());
        }
    }

    protected boolean process(Order order) {
        System.out.println("stock processing" + order.getOrderID() + "price:" + order.getPrice());

        return true;
    }
}
