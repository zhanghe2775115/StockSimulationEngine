import java.util.Date;

/**
 * Created by Drake on 2018/3/11.
 */
public class Order {
    private int amount;
    private Stock stock;
    private int price;
    private StockAccountInterface owner;
    private Date date;
    private OrderEnum orderType;

    public Order() {
        this.date = new Date();
    }

    public Order(int amount, Stock stock, int price, StockAccountInterface owner, Date date, OrderEnum orderType) {
        this.amount = amount;
        this.stock = stock;
        this.price = price;
        this.owner = owner;
        this.date = date;
        this.orderType = orderType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public StockAccountInterface getOwner() {
        return owner;
    }

    public void setOwner(StockAccountInterface owner) {
        this.owner = owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderEnum getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderEnum orderType) {
        this.orderType = orderType;
    }
}