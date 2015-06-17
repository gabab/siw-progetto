package it.uniroma3.model;

import it.uniroma3.enums.OrderState;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OrderState state;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date opened;
    @Temporal(TemporalType.TIMESTAMP)
    private Date closed;
    @Temporal(TemporalType.TIMESTAMP)
    private Date processed;
    @ManyToOne
    private Customer customer;
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "product_code")
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "orders_id"))
    private Map<String, OrderLine> orderlines;

    public Order() {

    }

    public Order(Customer customer) {
        this.state = OrderState.OPENED;
        this.opened = new Date();
        this.customer = customer;
        this.orderlines = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void addProduct(Product p, int quantity) {
        OrderLine a = orderlines.get(p.getCode());
        if (a == null)
            a = new OrderLine(p, 0);
        int s = a.getQuantity();
        a.setQuantity(s + quantity);
        orderlines.put(p.getCode(), a);
    }


    public List<OrderLine> getItems() {
        return new ArrayList<>(orderlines.values());
    }

    public float getTotal() {
        float total = 0;
        for (OrderLine ol : orderlines.values())
            total += ol.getSubTotal();
        return total;
    }

    public void close() {
        this.closed = new Date();
        this.state = OrderState.CLOSED;
    }

    public void process() {
        this.processed = new Date();
        this.state = OrderState.PROCESSED;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOpened() {
        return opened;
    }

    public void setOpened(Date opened) {
        this.opened = opened;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public Date getProcessed() {
        return processed;
    }

    public void setProcessed(Date processed) {
        this.processed = processed;
    }

    public Map<String, OrderLine> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(Map<String, OrderLine> orderlines) {
        this.orderlines = orderlines;
    }

    public int getSize() {
        return this.orderlines.size();
    }
}
