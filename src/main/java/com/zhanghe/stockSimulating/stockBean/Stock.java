package com.zhanghe.stockSimulating.stockBean;

/**
 * Created by Drake on 2018/2/26.
 */
public class Stock {
    static int id = 0;
    private String stockName;
    private int currPrice;


    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getCurrPrice() {
        return currPrice;
    }

    public void setCurrPrice(int currPrice) {
        this.currPrice = currPrice;
    }

    public Stock(String stockName, int currPrice) {
        this.stockName = stockName;
        this.currPrice = currPrice;
        id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        Stock.id = id;
    }

    public void changePrice(int i) {
        this.currPrice += i;
    }
}
