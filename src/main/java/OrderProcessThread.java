public class OrderProcessThread implements Runnable {

    Order order;
    @Override
    public void run() {
        if(!process(order)){
            System.out.println("process fail");
        }
    }
    protected boolean process(Order order) {
        System.out.println("stock processing"+order.getDate().toString()+"price:"+order.getPrice());
        return true;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
