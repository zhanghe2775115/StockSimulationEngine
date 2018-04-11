import java.util.List;

/**
 * Created by Drake on 2018/3/11.
 */
public interface StockAccountInterface {
    public boolean pushOrder(Order order);

    public List<Stock> getAllStock();

    public Stock getStock(int id );
}
