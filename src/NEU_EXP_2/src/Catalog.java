import java.util.*;
//GourmetCoffee.java 需要该类可迭代，故需要：implements Iterable<Product>
public class Catalog implements Iterable<Product> {
    //Instance variables: products — An ArrayList collection that contains references to instances of class Product.
    private  ArrayList<Product> products;
    //public Iterator<Product> iterator() — Returns an iterator over the instances in the collection products.
    public Iterator<Product> iterator(){
        return products.iterator();
    }

    //public Catalog() — Creates the collection products, which is initially empty.
    public Catalog(){
        products = new ArrayList<Product>();
    }
    //public void addProduct(Product product) — Adds the specified product to the collection products.
    public void addProduct(Product AddProduct){
        products.add(AddProduct);
    }

    //public Product getProduct(String code) — Returns a reference to the Product instance with the specified code. Returns null if there are no products in the catalog with the specified code.
    public Product getProduct(String code) {
        Product FindProduct;
        FindProduct = null;
        for (Product product : products) {
            if (product.getCode() == code) {
                FindProduct = product;
            }
        }
        return FindProduct;
    }
    //public int getNumberOfProducts() — Returns the number of instances in the collection products.public int getNumberOfProducts()public int getNumberOfProducts()public int getNumberOfProducts()
   public int getNumberOfProducts(){
            return products.size();
    }

}
