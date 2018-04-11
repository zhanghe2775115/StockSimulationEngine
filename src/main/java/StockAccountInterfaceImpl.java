import java.util.List;

/**
 * Created by Drake on 2018/3/11.
 */
public class StockAccountInterfaceImpl implements StockAccountInterface {
    private StockExchangeCentor stockExchangeCentor;

    public StockAccountInterfaceImpl() {
        this.stockExchangeCentor = StockExchangeCentor.getInstance();
    }

    public StockExchangeCentor getStockExchangeCentor() {
        return stockExchangeCentor;
    }

    public void setStockExchangeCentor(StockExchangeCentor stockExchangeCentor) {
        this.stockExchangeCentor = stockExchangeCentor;
    }

    public boolean pushOrder(Order order) {
        return stockExchangeCentor.pushOrder(order);
    }

    public List<Stock> getAllStock() {
        return stockExchangeCentor.getStocks();
    }

    public Stock getStock(int id) {
        return stockExchangeCentor.getStock(id);
    }

}
