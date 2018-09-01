package com.zhanghe.stockSimulating.Institution.exchangeCentor.OrderQueue;

import com.zhanghe.stockSimulating.Util.Enum.OrderEnum;
import com.zhanghe.stockSimulating.facade.bean.Order;

import java.util.LinkedList;
import java.util.List;

public class SortedOrderQueue {
    final private List<Order> mList = new LinkedList<>();
    final OrderEnum queueType;
    private int queueSize = 0;
    public SortedOrderQueue(OrderEnum queueType) {
        this.queueType = queueType;
    }

    public boolean pushOrder(Order order){

        mList.add(order);
        queueSize++;
        return  true;
    }
    public Order getAndRemoveTopOrder() {
        if (mList.size() ==0)
            return null;
        return mList.remove(0);
    }
}
