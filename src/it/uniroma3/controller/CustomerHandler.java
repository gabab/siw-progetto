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
import java.util.List;

@ManagedBean(name = "customer")
@SessionScoped
public class CustomerHandler {
    @ManagedProperty(value = "#{login.customer}")
    private Customer currentCustomer;
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


    public String closeOrder(Long orderID) {
        Order o = this.orderFacade.getOrder(orderID);
        this.currentCustomer.getOrders().remove(o);
        return closeOrder(o);

    }


    private String closeOrder(Order o) {
        o.close();
        this.currentCustomer.addOrder(o);
        this.userFacade.updateUser(this.currentCustomer);
        refreshOrders();
        return "pretty:mypage";

    }

    private void refreshOrders() {
        getOpenOrders();
        getClosedOrders();
        getProcessedOrders();
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
        return "insertOrder";
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

    @PostConstruct
    private void forceGetC() {
        this.currentCustomer = login.getCustomer();
    }

    public String addToOrder() {
        Product p = this.productFacade.getProduct(productCode);
        if (p != null)
            this.currentOrder.addProduct(p, quantity);
        return "insertOrder";
    }

    public List getOpenOrders() {
        List orders = this.orderFacade.getOpenOrders(currentCustomer);
        //Collections.reverse(orders);
        return orders;
    }

    public List getClosedOrders() {
        List orders = this.orderFacade.getClosedOrders(currentCustomer);
        //Collections.reverse(orders);
        return orders;
    }

    public List getProcessedOrders() {
        List orders = this.orderFacade.getProcessedOrders(currentCustomer);
        //Collections.reverse(orders);
        return orders;
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
