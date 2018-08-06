import java.util.*;

//The class Sales maintains a list of the orders that have been completed. This class implements the interface Iterable<Order> to being able to iterate through the orders using the for-each loop.

public class Sales implements Iterable<Order> {
    //Instance variables: orders — An ArrayList collection that contains references to instances of class Order.
    private ArrayList<Order> orders;
    //Constructor:  public Sales() — Creates the collection orders, which is initially empty.
    public Sales(){
    orders =new ArrayList<Order>();
    }
    //public Iterator<Order> iterator() — Returns an iterator over the instances in the collection orders.
    public Iterator<Order> iterator(){
        return orders.iterator();
    }
    //public void addOrder(Order order) — Adds the specified order to the collection orders.
    public void addOrder(Order order){
        orders.add(order);
    }
    //public int getNumberOfOrders() — Returns the number of instances in the collection orders.
    public int getNumberOfOrders(){
        return orders.size();
    }

}
