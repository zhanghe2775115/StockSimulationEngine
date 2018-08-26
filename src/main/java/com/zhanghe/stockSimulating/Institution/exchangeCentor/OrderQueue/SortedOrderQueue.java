package com.zhanghe.stockSimulating.Institution.exchangeCentor.OrderQueue;

import com.zhanghe.stockSimulating.Util.Enum.OrderEnum;
import com.zhanghe.stockSimulating.facade.bean.Order;

import java.util.LinkedList;
import java.util.List;

public class SortedOrderQueue {
    final private List<Order> mList = new LinkedList<>();
    final OrderEnum queueType;

    public SortedOrderQueue(OrderEnum queueType) {
        this.queueType = queueType;
    }

    public boolean pushOrder(Order order){
        return mList.add(order);
    }
    public Order getAndRemoveTopOrder() {
        return mList.remove(0);
    }
}
