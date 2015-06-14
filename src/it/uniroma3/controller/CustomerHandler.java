package it.uniroma3.controller;


import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.Cart;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="customer")
@SessionScoped
public class CustomerHandler {
    @ManagedProperty(value = "#{login.customer}")
    private Customer currentCustomer;
    private List<Order> orders;
    private Order order;
    private Cart cart;
    private Order currentOrder;
    @EJB(name = "order")
    private OrderFacade orderFacade;
    @EJB(name = "product")
    private ProductFacade productFacade;
    @EJB(name = "user")
    private UserFacade userFacade;
    private String productCode;
    private int quantity;

    private boolean existsAddress;
    @ManagedProperty(value = "#{login}")
    private Login login;


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getCartSize() {
        return (cart == null || cart.getSize() == 0) ? "" : "(" + cart.getSize() + ")";
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

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
//        if (!this.currentCustomer.hasAddress())

        this.currentOrder.close();
        this.currentCustomer.addOrder(this.currentOrder);
        this.userFacade.updateUser(this.currentCustomer);
        return "pretty:home";
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

    public String createOrder() {
        this.currentOrder = new Order(currentCustomer);
        return "insertOrder";
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean getExistsAddress(){
        return currentCustomer != null && currentCustomer.hasAddress();
    }


    public String addToCart(String productCode) {
        if (currentCustomer == null)
            return "pretty:login";
        if (cart == null)
            this.cart = new Cart(currentCustomer);
        Product p = this.productFacade.getProduct(productCode);
        this.cart.addProduct(p);
        return "pretty:";
    }

    public void closeCart() {
        this.currentOrder = cart;
        closeOrder();
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
}
