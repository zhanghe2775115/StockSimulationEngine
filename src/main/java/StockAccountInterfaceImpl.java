/**
 * Created by Drake on 2018/3/11.
 */
public class StockAccountInterfaceImpl implements StockAccountInterface {
    private StockExchangeCentor stockExchangeCentor;

    public StockExchangeCentor getStockExchangeCentor() {
        return stockExchangeCentor;
    }

    public void setStockExchangeCentor(StockExchangeCentor stockExchangeCentor) {
        this.stockExchangeCentor = stockExchangeCentor;
    }
    public boolean pushOrder(Order order) {
        return stockExchangeCentor.pushOrder(order);
    }
}
