import java.util.LinkedList;
import java.util.List;

/**
 * Created by Drake on 2018/2/26.
 */
public class Person {
    private int mCash;
    private List<Stock> mStocks;
    private String mName;

    public int getmCash() {
        return mCash;
    }

    public void setmCash(int mCash) {
        this.mCash = mCash;
    }

    public List<Stock> getmStocks() {
        return mStocks;
    }

    public void setmStocks(List<Stock> mStocks) {
        this.mStocks = mStocks;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public Person(int mCash, String mName) {
        this.mCash = mCash;
        this.mName = mName;
        mStocks = new LinkedList<Stock>();
    }

    public boolean buyStock(Stock stock) {
        if (mCash < stock.getCurrValue()) {
            System.out.println("not enough money for stock: " + stock.getStockName());
            return false;
        }
        mCash = mCash - stock.getCurrValue();
        mStocks.add(stock);
        System.out.println("purchased stock: " + stock.getStockName() + " stockPrice:" + stock.getCurrValue() + " balance: " + this.mCash);
        return true;
    }

    public boolean sellStock(Stock stock) {
        mCash = mCash + stock.getCurrValue();
        mStocks.remove(stock);
        System.out.println("selled stock: " + stock.getStockName() + " stockPrice:" + stock.getCurrValue() + " balance: " + this.mCash);
        return true;
    }

    public void showWealth() {
        int balance = this.mCash;
        for (int i = 0; i < mStocks.size(); i++) {
            balance += mStocks.get(i).getCurrValue();
        }
        System.out.println(this.mName + " has: " + balance);
    }
}
