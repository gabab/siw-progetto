package it.uniroma3.model;


public class Cart extends Order {

    public Cart(Customer customer) {
        super(customer);
    }

    public void addProduct(Product p) {
        super.addProduct(p, 1);
    }
}
