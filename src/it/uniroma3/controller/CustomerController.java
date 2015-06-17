package it.uniroma3.controller;


import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

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
    @EJB(name = "user")
    private UserFacade userFacade;
    private String productCode;
    private int quantity = 1;
    private List<Order> openOrders;
    private List<Order> closedOrders;
    private List<Order> processedOrders;

    public List getOpenOrders() {
        return openOrders;
    }

    public List getProcessedOrders() {
        return processedOrders;
    }

    public List getClosedOrders() {
        return closedOrders;
    }

    @PostConstruct
    public void refreshLists() {
        this.openOrders = (List<Order>) this.orderFacade.findOpenOrders(currentCustomer);
        this.closedOrders = (List<Order>) this.orderFacade.findClosedOrders(currentCustomer);
        this.processedOrders = (List<Order>) this.orderFacade.findProcessedOrders(currentCustomer);
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

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
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

    public String saveOrder() {
        if (orderFacade.findOrder(this.currentOrder.getId()) == null)
            this.orderFacade.insertOrder(this.currentOrder);
        else
            this.orderFacade.updateOrder(this.currentOrder);
        this.openOrders.add(this.currentOrder);
        this.currentOrder = null;
        return "pretty:mypage";
    }

    private String closeOrder(Order o) {
        if (o.getSize() > 0) {
            o.close();
            this.openOrders.remove(o);
            this.closedOrders.add(o);
            this.orderFacade.updateOrder(o);
        }
        return "pretty:mypage";
    }

    public String closeOrder() {
        return closeOrder(this.currentOrder);
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

    public boolean getExistsAddress() {
        return currentCustomer != null && currentCustomer.hasAddress();
    }


    public String addToOrder() {
        Product p = this.productFacade.getProduct(productCode);
        if (p != null)
            this.currentOrder.addProduct(p, quantity);
        resetData();
        return "pretty:neworder";
    }


    public String modifyOrder(Long orderID) {
        this.currentOrder = orderFacade.findOrder(orderID);
        resetData();
        return "pretty:neworder";
    }

    public String removeOrder(Long orderID) {
        Order o = orderFacade.findOrder(orderID);
        this.currentCustomer.removeOrder(o);
        this.openOrders.remove(o);
        this.orderFacade.deleteOrder(o);
        return "pretty:mypage";
    }

}
