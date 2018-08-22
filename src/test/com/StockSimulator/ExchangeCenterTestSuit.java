package com.StockSimulator;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.StockSimulator.Entities.*;
import org.junit.jupiter.api.Test;

/**
 * Created by Drake on 2018/2/26.
 */

public class ExchangeCenterTestSuit {
    private static long TRADETIMES = 10000;
    private static int INITIALCASH = 1000;
    private static int STOCKCOUNT = 4;

    @Test
    public void ExchangeCenterTest() {
        String[] stockNamePreFix = {"Beer", "WATER", "GAME", "DRUG", "MOVIE"};
        Person tom = new Person(INITIALCASH, "TOM");
        Person jerry = new Person(INITIALCASH, "JERRY");
        Person hans = new Person(INITIALCASH, "HANS");
        Person bob = new Person(INITIALCASH, "BOB");
//        List<com.StockSimulator.Person> peopleList = new ArrayList<com.StockSimulator.Person>();
//        peopleList.add(tom);
//        peopleList.add(jerry);
//        peopleList.add(hans);
//        peopleList.add(bob);
        List<Stock> stocks = new ArrayList<Stock>();
        Random random = new Random();
        for (int i = 0; i < STOCKCOUNT; i++) {
            String prefix = stockNamePreFix[random.nextInt(stockNamePreFix.length)];
            String name = prefix + String.valueOf(i);
            stocks.add(new Stock(name, random.nextInt(100)));
//            int buyer = random.nextInt(peopleList.size());
//            while (!peopleList.get(buyer).buyStock(stocks.get(i))) {
//                buyer = random.nextInt(peopleList.size());
//            }
        }
        StockExchangeCentor.getInstance().setStocks(stocks);
        tom.setState(true);
        tom.setStockAccountInterface(new StockAccountInterfaceImpl());
        jerry.setState(true);
        jerry.setStockAccountInterface(new StockAccountInterfaceImpl());
        hans.setState(true);
        hans.setStockAccountInterface(new StockAccountInterfaceImpl());
        bob.setState(true);
        bob.setStockAccountInterface(new StockAccountInterfaceImpl());
        new Thread(tom).start();
        new Thread(jerry).start();
        new Thread(hans).start();
        new Thread(bob).start();
        StockExchangeCentor.start();

        return;
//
//        long count = TRADETIMES;
//        while (--count > 0) {
//            for (int i = 0; i < STOCKCOUNT; i = i + 2) {
//                if (random.nextBoolean()) {
//                    double priceChange = Math.sqrt(5) * random.nextGaussian();
//
//                    stocks.get(i).changePrice((int) priceChange);
//                    stocks.get(i + 1).changePrice((int) priceChange * -1);
//                }
//            }
//            int seller;
//            com.StockSimulator.Person sellMan = null;
//            do {
//                seller = random.nextInt(peopleList.size());
//                sellMan = peopleList.get(seller);
//            } while (sellMan.getmStocks().isEmpty());
//            int buyer;
//            do {
//                buyer = random.nextInt(peopleList.size());
//            } while (buyer == seller);
//            com.StockSimulator.Person buyMan = peopleList.get(buyer);
//            int stockId = random.nextInt(sellMan.getmStocks().size());
//            com.StockSimulator.Stock stock = sellMan.getmStocks().get(stockId);
//            if (buyMan.buyStock(stock)) {
//                sellMan.sellStock(stock);
//            }
//            System.out.println("rount:" + count);
//        }
//        tom.showWealth();
//        bob.showWealth();
//        jerry.showWealth();
//        hans.showWealth();
    }
}
