package it.uniroma3.model;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
