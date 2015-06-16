package it.uniroma3.controller;

import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.Administrator;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "admin")
@SessionScoped
public class AdminHandler {

    private Order order;
    private List orders;
    private Customer customer;
    @EJB(beanName = "user")
    private UserFacade uf;
    @EJB(beanName = "product")
    private ProductFacade pf;
    @EJB(beanName = "order")
    private OrderFacade of;

    @ManagedProperty(value = "#{login.administrator}")
    private Administrator admin;
    private String alert;
    private String message;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List getOrders() {
        return orders;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public String getCustomerInfo(Long orderID) {
        this.customer = this.of.getOrder(orderID).getCustomer();
        return "customerDetails";
    }

    public String viewOrderDetails(Long orderID) {
        this.order = this.of.getOrder(orderID);
        return "orderDetails";
    }

    public String processOrder(Long orderID) {
        Order o = this.of.processOrder(orderID);
        if (o != null)
            return "pretty:admin";
        else {
            this.alert = "Not enough items in stock";
            return "pretty:adminorder";
        }
    }

}
