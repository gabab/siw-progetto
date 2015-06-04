package it.uniroma3.model;


public class Cart extends Order{

    public void addProduct(Product p) {
        super.addProduct(p, 1);
    }

    public Cart(Customer customer) {
        super(customer);
    }
}
