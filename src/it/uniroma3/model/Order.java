package it.uniroma3.model;

import it.uniroma3.model.enums.OrderState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {


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

    @OneToMany
    @JoinColumn(name = "orders_id")
    private List<OrderLine> orderlines;

    @Column(nullable = false)
    public OrderState state;


    public Order() {

    }

    public Order(Customer customer) {
        this.state = OrderState.OPENED;
        this.opened = new Date();
        this.customer = customer;
        this.orderlines = new ArrayList<>();
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

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

    public void setOrderlines(List<OrderLine> orderlines) {
        this.orderlines = orderlines;
    }

    
    
    public void addOrderLine(OrderLine ol){
    	this.orderlines.add(ol);
    }

}
