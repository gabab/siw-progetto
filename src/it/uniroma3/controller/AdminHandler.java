package it.uniroma3.controller;

import it.uniroma3.facades.CustomerFacade;
import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;
import it.uniroma3.model.enums.OrderState;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class AdminHandler {

    private Order order;
    private List orders;
    private Customer customer;
    @EJB(beanName = "user")
    private UserFacade uf;
    @EJB(beanName = "product")
    private ProductFacade pf;
    @EJB(beanName = "orders")
    private OrderFacade of;

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


    public String getClosedOrders() {
        this.orders = this.of.getClosedOrders();
        return "openOrders";
    }

    public String getCustomerInfo(Long orderID) {
        this.customer = this.of.getOrder(orderID).getCustomer();
        return "customerDetails";
    }

    public String viewOrderDetails(Long orderID) {
        this.order = this.of.getOrder(orderID);
        return "orderDetails";
    }

   //TODO: da sistemare
    public String processOrder(Long orderID) {
        Order current = this.of.getOrder(orderID);
        for (OrderLine ol : current.getOrderlines()) {
            Product p = ol.getProduct();
            if (ol.getQuantity() > p.getInStock())
                return "error";
            p.setInStock(p.getInStock() - ol.getQuantity());
            this.pf.updateProduct(p);
        }
        current.setState(OrderState.PROCESSED);
        return "success";
    }




}
