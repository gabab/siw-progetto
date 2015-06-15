package it.uniroma3.controller;

import it.uniroma3.facades.OrderFacade;
import it.uniroma3.model.Order;
import it.uniroma3.utils.Paginator;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "orderView")
@SessionScoped
public class ShowOrder extends Paginator {

    @EJB(beanName = "order")
    private OrderFacade of;

    private Order order;

    public String show(Long orderID){
        this.order = this.of.getOrder(orderID);
        return showOrder();
    }

    public String showOrder() {
        paginate(this.order.getItems(), 2);
        return "orderDetails";
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setOf(OrderFacade of) {
        this.of = of;
    }
}
