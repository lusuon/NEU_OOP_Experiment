/*
The class Catalog models a product catalog. This class implements the interface Iterable<Product> to being able to iterate through the products using the for-each loop.
        Instance variables:
            products — An ArrayList collection that contains references to instances of class Product.
*/

//public Iterator<Product> iterator() — Returns an iterator over the instances in the collection products.

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Catalog {
    private  Collection<Product> products;
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
        //迭代器与for-each循环实现方式相同
        //Iterator<Product> iter = products.iterator();

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

    /*
    public static void main (String args[]){
        String t="test";
    }
    */
}
