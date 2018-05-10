/*
Class Order
The class Order maintains a list of order items. This class implements the interface Iterable<OrderItem> to being able to iterate through the items using the for-each loop.
Constructor and public methods:
public Iterator<OrderItem> iterator() — Returns an iterator over the instances in the collection items.
public double getTotalCost() — Returns the total cost of the order.
 */
import java.util.ArrayList;
import java.util.Collection;

public class Order {
    //items — An ArrayList collection that contains references to instances of class OrderItem.
    private Collection<OrderItem> items;
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
        //是否再需要迭代器？
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

}
