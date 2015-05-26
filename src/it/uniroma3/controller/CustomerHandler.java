package it.uniroma3.controller;


import it.uniroma3.facades.OrderFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class CustomerHandler {

    //@ManagedProperty(value = "#{login.getCustomer}")
    private Customer currentCustomer;
    private List<Order> orders;
    private Order order;
    @EJB(name = "order")
    private OrderFacade orderFacade;

    public OrderFacade getOrderFacade() {
        return orderFacade;
    }

    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getOrders() {
        this.orders = this.currentCustomer.getOrders();
        return "myOrders";
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String findOrder(Long orderID) {
        this.order = this.orderFacade.getOrder(orderID);
        return "orderDetail";
    }
}
