package it.uniroma3.controller;

import it.uniroma3.facades.OrderFacade;
import it.uniroma3.model.Administrator;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "admin")
@SessionScoped
public class AdminController {

    private Long orderID;

    private Customer customer;

    private List closedOrders;

    @EJB(beanName = "order")
    private OrderFacade of;

    @ManagedProperty(value = "#{login.administrator}")
    private Administrator admin;

    private String alert;


    public String processOrder(Long orderID) {
        Order o = this.of.processOrder(orderID);
        this.alert = null;
        if (o == null) {
            this.alert = "Order no." + orderID + " : not enough items in stock";
            return "pretty:admin-detail";
        }
        return "pretty:admin";
    }

    public String findCustomerInfo() {
        Order o = this.of.findOrder(orderID);
        this.customer = (o == null) ? null : o.getCustomer();
        return "pretty:admin-address";
    }

    @PostConstruct
    public void loadClosedOrders() {
        this.closedOrders = this.of.findClosedOrders();
    }

    public String getAlert() {
        return alert;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public List getClosedOrders() {
        return closedOrders;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }
}
