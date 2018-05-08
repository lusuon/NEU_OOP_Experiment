/*
The class Catalog models a product catalog. This class implements the interface Iterable<Product> to being able to iterate through the products using the for-each loop.
        Instance variables:
            products — An ArrayList collection that contains references to instances of class Product.
*/

//public Iterator<Product> iterator() — Returns an iterator over the instances in the collection products.

import java.util.ArrayList;

public Iterator<Product> iterator()


public class Catalog {
    ArrayList products;

    //public Catalog() — Creates the collection products, which is initially empty.
    public Catalog(){
        products = new Product[]
    }

    //public void addProduct(Product product) — Adds the specified product to the collection products.
    public void addProduct(Product){

    }

    //public Product getProduct(String code) — Returns a reference to the Product instance with the specified code. Returns null if there are no products in the catalog with the specified code.
    public Product getProduct(String code){

    }

    //public int getNumberOfProducts() — Returns the number of instances in the collection products.
    public int getNumberofProduct(){

    }
}
