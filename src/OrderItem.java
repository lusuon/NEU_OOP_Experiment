public class OrderItem {
    private Product product;
    private int quantity;
    //Constructor Method
    OrderItem(Product initProduct,int initQuantity){
        Product product=initProduct;
        int quantity=initQuantity;
    }

    //Mutator Method
    public void setQuantity(int newQuantity){
        quantity=newQuantity;
    }

    //Accessor Method
    //public Product getProduct(). Returns the value of the instance variable product, a reference to a Product object.
    public Product getProduct(){
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    //public double getValue(). Returns the product of quantity and price.
    public double getValue(){
        return this.getQuantity() * this.getProduct().getPrice();
    }


    //toString
    //String toString().  Returns the string representation of an OrderItem object. The String representation has the following format:
    //quantity product-code product-price
    public String toString() {
        return "quantity:" + this.getQuantity() + " " + "product-code:" + this.getProduct().getCode() + " " + "product-price:" + this.getProduct().getPrice();
    }

    public static void main(String[] args){
        OrderItem TestItem;
        Product TestProduct;
        TestProduct = new Product("001","BalaBala",100.0);
        TestItem = new OrderItem(TestProduct,5);
        System.out.println(TestItem.getProduct());
        System.out.println(TestItem.getQuantity());
        //System.out.println(TestItem.getValue());
        //TestItem.toString();
    }
}
