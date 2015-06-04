package it.uniroma3.model;

import javax.persistence.*;

@Embeddable
public class OrderLine {

    @Column(nullable = false)
    private Float unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Product product;

    public OrderLine(Product product, Integer quantity) {
        this.product = product;
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
    }

    public OrderLine() {

    }

    public float getSubTotal() {
        return unitPrice * quantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
