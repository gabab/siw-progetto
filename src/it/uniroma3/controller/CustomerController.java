package it.uniroma3.controller;


import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.Product;

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
        Order o = this.orderFacade.getOrder(orderID);
        this.currentCustomer.getOrders().remove(o);
        return closeOrder(o);
    }

    public void saveOrder() {
        this.orderFacade.updateOrder(this.currentOrder);
        refreshOrders();
    }

    private String closeOrder(Order o) {
        o.close();
        this.currentCustomer.addOrder(o);
        this.userFacade.updateUser(this.currentCustomer);
        refreshOrders();
        return "pretty:mypage";
    }

    private void refreshOrders() {
        this.userFacade.refresh(currentCustomer);
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


    public String viewCart() {
        return null;
    }

    public String createOrder() {
        this.currentOrder = new Order(currentCustomer);
        return "pretty:customer-order";
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
        return "insertOrder";
    }

    public List getOpenOrders() {
        return this.orderFacade.getOpenOrders(currentCustomer);
    }

    public List getClosedOrders() {
        return this.orderFacade.getClosedOrders(currentCustomer);
    }

    public List getProcessedOrders() {
        return this.orderFacade.getProcessedOrders(currentCustomer);
    }

    public String modifyOrder(Long orderID) {
        this.currentOrder = orderFacade.getOrder(orderID);
        return "insertOrder";
    }

    public String removeOrder(Long orderID) {
        this.currentCustomer.getOrders().remove(orderFacade.getOrder(orderID));
        this.userFacade.updateCustomer(currentCustomer);
        return "insertOrder";
    }
}
