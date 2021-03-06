public class OrderItem {
    private Product product;
    private int quantity;
    //Constructor Method 不用加修饰符
    public OrderItem(Product initProduct,int initQuantity){
        product=initProduct;
        quantity=initQuantity;
    }

    //Mutator Method
    public void setQuantity(int newQuantity){
        quantity=newQuantity;
    }

    //Accessor Method
    //public Product getProduct(). Returns the value of the instance variable product, a reference to a Product object.
    public Product getProduct(){
        return this.product;
    }
    public int getQuantity() {
        return this.quantity;
    }
    //public double getValue(). Returns the product of quantity and price.
    public double getValue(){
        return this.getQuantity() * this.getProduct().getPrice();
    }


    //toString
    //String toString().  Returns the string representation of an OrderItem object. The String representation has the following format:
    //quantity product-code product-price
    public String toString() {
        return this.getQuantity() + " " + this.getProduct().getCode() + " " + this.getProduct().getPrice();
    }

}
