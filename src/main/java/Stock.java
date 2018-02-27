/**
 * Created by Drake on 2018/2/26.
 */
public class Stock {
    static private int gId = 0;
    private String stockName;
    private int currValue;
    private int mId;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public int getCurrValue() {
        return currValue;
    }

    public void setCurrValue(int currValue) {
        this.currValue = currValue;
    }

    public Stock(String stockName, int currValue) {
        this.stockName = stockName;
        this.currValue = currValue;
        gId++;
        this.mId = gId;
    }

    public static int getgId() {
        return gId;
    }

    public static void setgId(int gId) {
        Stock.gId = gId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void changePrice(int i) {
        this.currValue += i;
    }
}
