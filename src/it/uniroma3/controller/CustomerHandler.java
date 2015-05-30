package it.uniroma3.controller;


import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.Product;
import it.uniroma3.model.enums.OrderState;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.List;

@ManagedBean
@SessionScoped
public class CustomerHandler {
    private Customer currentCustomer;
    private List<Order> orders;
    private Order order;
    private Order currentOrder;
    @EJB(name = "order")
    private OrderFacade orderFacade;
    @EJB(name = "product")
    private ProductFacade productFacade;
    @EJB(name = "user")
    private UserFacade userFacade;
    private int quantity;

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public OrderFacade getOrderFacade() {
        return orderFacade;
    }

    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    public String closeOrder() {
        this.currentOrder.setState(OrderState.CLOSED);
        this.currentOrder.setClosed(new Date());
        this.currentCustomer.addOrder(this.currentOrder);
        this.userFacade.updateUser(this.currentCustomer);
        return "confirmation";
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addToCart(String productCode) {
        if (currentOrder == null)
            this.currentOrder = new Order(currentCustomer);
        Product p = this.productFacade.getProduct(productCode);
        this.currentOrder.addProductToOrder(p, quantity);
    }
}
