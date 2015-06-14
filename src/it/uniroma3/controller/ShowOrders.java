package it.uniroma3.controller;

import it.uniroma3.utils.Paginator;
import it.uniroma3.facades.OrderFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "orders")
@SessionScoped
public class ShowOrders extends Paginator {

    public List orders;

    public ShowOrder order;


    @EJB(beanName = "order")
    private OrderFacade of;


    public String getClosedOrders() {
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

}