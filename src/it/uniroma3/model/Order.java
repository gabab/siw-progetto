package it.uniroma3.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date opened;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closed;

    @Temporal(TemporalType.TIMESTAMP)
    private Date processed;

    private List<OrderLine> orderlines;

    public Order() {

    }

    public Order(Date opened, Customer customer) {
        this.opened = opened;
        this.customer = customer;
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

}
