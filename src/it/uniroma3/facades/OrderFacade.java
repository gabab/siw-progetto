package it.uniroma3.facades;

import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;
import it.uniroma3.model.enums.OrderState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless(name = "order")
public class OrderFacade {

    @PersistenceContext(unitName = "siw-unit")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List getClosedOrders() {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = " + OrderState.CLOSED);
        return q.getResultList();
    }

    public List getClosedOrders(Customer c) {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = " + OrderState.CLOSED);
        return getOrdersState(OrderState.CLOSED, c);
    }

    public List getOrdersState(OrderState state, Customer customer) {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = " + state + " AND o.customer = " + customer);
        return q.getResultList();
    }

    public Order getOrder(Long orderID) {
        return this.em.find(Order.class, orderID);
    }

    public void updateOrder(Order o) {
        this.em.merge(o);
    }

    public Order processOrder(Long orderID) {
        Order o = this.getOrder(orderID);
        for (OrderLine ol : o.getOrderlines().values()) {
            Product p = ol.getProduct();
            if (ol.getQuantity() > p.getInStock()) {
                em.refresh(o);
                return null;
            }
            p.setInStock(p.getInStock() - ol.getQuantity());
        }
        o.setState(OrderState.PROCESSED);
        o.setClosed(new Date());
        em.merge(o);
        return o;
    }

    public Order createOrder() {
        Order order = new Order();
        em.persist(order);
        return order;
    }

}