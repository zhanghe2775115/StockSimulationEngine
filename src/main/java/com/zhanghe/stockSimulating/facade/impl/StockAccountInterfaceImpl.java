package com.zhanghe.stockSimulating.facade.impl;

import com.zhanghe.stockSimulating.Institution.exchangeCentor.StockExchangeCentor;
import com.zhanghe.stockSimulating.facade.bean.Order;
import com.zhanghe.stockSimulating.facade.bean.Stock;
import com.zhanghe.stockSimulating.facade.interfaces.StockAccountInterface;

import java.util.List;

/**
 * Created by Drake on 2018/3/11.
 */
public class StockAccountInterfaceImpl implements StockAccountInterface {
    private StockExchangeCentor stockExchangeCentor;

    private int orderIndex;

    public StockAccountInterfaceImpl() {
        orderIndex = 0;
        this.stockExchangeCentor = StockExchangeCentor.getInstance();
    }

    public StockExchangeCentor getStockExchangeCentor() {
        return stockExchangeCentor;
    }

    public void setStockExchangeCentor(StockExchangeCentor stockExchangeCentor) {
        this.stockExchangeCentor = stockExchangeCentor;
    }

    public boolean pushOrder(Order order) {
        order.setOrderID(order.getOrderID() + "-" + orderIndex++ + "-");
        return stockExchangeCentor.pushOrder(order);
    }

    public List<Stock> getAllStock() {
        return stockExchangeCentor.getStocks();
    }

    public Stock getStock(int id) {
        return stockExchangeCentor.getStock(id);
    }

}
