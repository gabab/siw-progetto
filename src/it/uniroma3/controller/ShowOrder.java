package it.uniroma3.controller;

import it.uniroma3.facades.OrderFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.utils.Paginator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "orderView")
@SessionScoped
public class ShowOrder extends Paginator {

    @EJB(beanName = "order")
    private OrderFacade of;

    private Customer customer;
    private Order order;
    private List orders;

    public void setOrders(List orders) {
        this.orders = orders;
    }

    public void setOf(OrderFacade of) {
        this.of = of;
    }

    public String show(Long orderID) {
        this.order = this.of.getOrder(orderID);
        return showOrder();
    }

    public String showOrder() {
        paginate(this.order.getItems(), 2);
        return "orderDetails";
    }

    public String getPlacedOrders() {
        this.orders = this.of.getClosedOrders();
        return listOrders("pretty:orders", "Closed Orders");
    }

    public String getPlacedOrders(Customer c) {
        this.orders = this.of.getClosedOrders();
        return listOrders("pretty:orders", "Closed Orders");
    }

    private String listOrders(String page, String title) {
        paginate(this.orders);
        setPageTitle(title);
        return page;
    }

    public String getProcessedOrders() {
        this.orders = this.of.getProcessedOrders();
        return "pretty:orders";
    }

    public String getProcessedOrders(Customer c) {
        this.orders = this.of.getProcessedOrders();
        return "pretty:orders";
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
