package it.uniroma3.controller;


import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "customer")
@SessionScoped
public class CustomerController {
    @ManagedProperty(value = "#{login.customer}")
    private Customer currentCustomer;
    private Order order;
    private Order currentOrder;
    @EJB(name = "order")
    private OrderFacade orderFacade;
    @EJB(name = "product")
    private ProductFacade productFacade;
    private String productCode;
    private int quantity = 1;
    private Map<Long, Order> openOrders;
    private Map<Long, Order> closedOrders;
    private Map<Long, Order> processedOrders;
    private String error;

    public List getOpenOrders() {
        return new ArrayList<>(openOrders.values());
    }

    public List getProcessedOrders() {
        return new ArrayList<>(processedOrders.values());
    }

    public List getClosedOrders() {
        return new ArrayList<>(closedOrders.values());
    }


    @PostConstruct
    public void init() {
        this.openOrders = this.orderFacade.findOpenOrders(currentCustomer);
        this.closedOrders = this.orderFacade.findClosedOrders(currentCustomer);
        this.processedOrders = this.orderFacade.findProcessedOrders(currentCustomer);
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }


    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void setOrderFacade(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }


    public String closeOrder(Long orderID) {
        Order o = this.orderFacade.findOrder(orderID);
        return closeOrder(o);
    }

    private String closeOrder(Order o) {
        this.openOrders.remove(o.getId());
        if (o.getSize() > 0) {
            o.close();
            o = this.orderFacade.updateOrder(o);
            this.closedOrders.put(o.getId(), o);
        }

        return "pretty:mypage";
    }

    public String closeOrder() {
        return closeOrder(this.currentOrder);
    }


    public String saveOrder() {
        if (this.currentOrder.getSize() > 0) {
            this.currentOrder = this.orderFacade.updateOrder(this.currentOrder);
            this.openOrders.put(this.currentOrder.getId(), this.currentOrder);
        }
        this.currentOrder = null;
        return "pretty:mypage";
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


    public void resetData() {
        this.quantity = 1;
        this.productCode = null;
        this.error = null;
    }

    public String createOrder() {
        this.currentOrder = new Order(currentCustomer);
        resetData();
        return "pretty:neworder";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String addToOrder() {
        Product p = this.productFacade.getProduct(productCode);
        resetData();
        if (p != null)
            this.currentOrder.addProduct(p, quantity);
        else
            this.error = "Invalid Code";
        return "pretty:neworder";
    }


    public String modifyOrder(Long orderID) {
        this.currentOrder = orderFacade.findOrder(orderID);
        resetData();
        return "pretty:neworder";
    }

    public String removeOrder(Long orderID) {
        Order o = orderFacade.findOrder(orderID);
        this.openOrders.remove(o.getId());
        this.orderFacade.deleteOrder(o);
        return "pretty:mypage";
    }

    public String getError() {
        return error;
    }
}
