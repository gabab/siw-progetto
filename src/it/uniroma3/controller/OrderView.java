package it.uniroma3.controller;

import it.uniroma3.facades.OrderFacade;
import it.uniroma3.model.Order;
import it.uniroma3.utils.Paginator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "orderView")
@SessionScoped
public class OrderView extends Paginator {

    @EJB(beanName = "order")
    private OrderFacade of;

    @ManagedProperty(value = "#{config.orderlinesPerPage}")
    private int orderlinesPerPage;

    private Order order;
    private List orders;

    public void setOrders(List orders) {
        this.orders = orders;
    }

    public void setOf(OrderFacade of) {
        this.of = of;
    }

    public String show(Long orderID) {
        this.order = this.of.findOrder(orderID);
        return showOrder();
    }

    public String showOrder() {
        paginate(this.order.getItems(), orderlinesPerPage);
        return "pretty:orderdetail";
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOrderlinesPerPage(int orderlinesPerPage) {
        this.orderlinesPerPage = orderlinesPerPage;
    }
}
