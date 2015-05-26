package it.uniroma3.controller;


import it.uniroma3.facades.OrderFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.enums.OrderState;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.List;

@ManagedBean
@SessionScoped
public class CustomerHandler {

    private Customer currentCustomer;
    private List<Order> orders;
    private Order order;
   
    @EJB(name = "order")
    private OrderFacade orderFacade;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
       public String getOrders() {
    	
        this.orders = this.orderFacade.getOrdersState(OrderState.OPENED, this.currentCustomer.getId());
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
