public class OrderItem {
    //声明变量、数组
    private Product product;
    private int quantity;
    private double[] value;
    //Constructor Method 构造方法
    OrderItem(Product initProduct,int initQuantity){
        Product product=initProduct;
        int quantity=initQuantity;
    }

    //Mutator Method  赋值方法
    public void setQuantity(int newQuantity){
        quantity=newQuantity;
    }

    //Accessor Method  访问方法
    //public Product getProduct(). Returns the value of the instance variable product, a reference to a Product object.目前不确定代码是否正确
    public Product getProduct(){
        return product;
    }
    public int getQuantity() {
        return quantity;
    }
    //public double getValue(). Returns the product of quantity and price.
    public double getValue(){
        double[] value ={};
        value[0]=this.quantity;
        value[1]=this.getProduct().getPrice();
        return value;
    }
}

    //toString
    //String toString().  Returns the string representation of an OrderItem object. The String representation has the following format:
    //quantity product-code product-price
    //想输出空格时是否能像python一样使用逗号分割？
    @Override
    public String toString() {
        return "quantity:" + this.getQuantity() + " " + "product-code:" + this.getProduct().getCode() + " " + "product-price:" + this.getProduct().getPrice();
    }

    public static void main(String[] args){
        OrderItem TestItem;
        Product TestProduct;
        TestProduct = new Product("001","BalaBala",100);
        TestItem = new OrderItem(TestProduct,5);
        TestItem.getProduct();
        TestItem.getQuantity();
        TestItem.getValue();
        TestItem.toString();
    }
}
