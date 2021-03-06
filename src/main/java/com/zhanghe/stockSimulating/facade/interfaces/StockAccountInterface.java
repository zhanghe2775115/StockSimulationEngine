package com.zhanghe.stockSimulating.facade.interfaces;

import com.zhanghe.stockSimulating.facade.bean.Order;
import com.zhanghe.stockSimulating.facade.bean.Stock;

import java.util.List;

/**
 * Created by Drake on 2018/3/11.
 */
public interface StockAccountInterface {
    public boolean pushOrder(Order order);

    public List<Stock> getAllStock();


    public Stock getStock(int id);
}
