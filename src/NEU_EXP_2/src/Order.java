/*
Class Order
The class Order maintains a list of order items. This class implements the interface Iterable<OrderItem> to being able to iterate through the items using the for-each loop.
*/
import java.util.*;
public class Order implements Iterable<OrderItem>{
    //items — An ArrayList collection that contains references to instances of class OrderItem.
    private ArrayList<OrderItem> items;
    //public Iterator<OrderItem> iterator() — Returns an iterator over the instances in the collection items.
    public Iterator<OrderItem> iterator(){
        return items.iterator();
    }
    //public Order() — Creates the collection items, which is initially empty.
    public Order(){
        items = new ArrayList<OrderItem>();
    }
    //public void addItem(OrderItem orderItem) — Adds the specified order item to the collection items.
    public void addItem(OrderItem orderItem){
        items.add(orderItem);
    }

    //public void removeItem(OrderItem orderItem) — Removes the specified order item from the collection items.
    public void removeItem(OrderItem orderItem){
        items.remove(orderItem);
    }

    //public OrderItem getItem(Product product) — Returns a reference to the OrderItem instance with the specified product. Returns null if there are no items in the order with the specified product.
    public OrderItem getItem(Product product){
        OrderItem FindItem;
        FindItem = null;
        for (OrderItem orderItem:items){
            if (orderItem.getProduct().getCode()==product.getCode()){
                FindItem = orderItem;
            }
        }
        return FindItem;
    }

    //public int getNumberOfItems() — Returns the number of instances in the collection items.
    public int getNumberOfItems(){
        return items.size();
    }

    //public double getTotalCost() — Returns the total cost of the order.
    public double getTotalCost(){
        Iterator iter;
        double cost;
        double TotalCost;
        iter=items.iterator();
        TotalCost=0;
        while(iter.hasNext()){
            OrderItem CostItem;
            CostItem=(OrderItem) iter.next();
            cost=CostItem.getQuantity()*CostItem.getProduct().getPrice();
            TotalCost +=cost;
        }
        return TotalCost;
    }
}
