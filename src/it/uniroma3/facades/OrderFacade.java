package it.uniroma3.facades;

import it.uniroma3.enums.OrderState;
import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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

    public List findOpenOrders() {
        return getOrdersState(OrderState.OPENED);
    }

    public List findClosedOrders() {
        return getOrdersState(OrderState.CLOSED);
    }

    public List findProcessedOrders() {
        return getOrdersState(OrderState.PROCESSED);
    }

    private List getOrdersState(OrderState state) {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = :state")
                .setParameter("state", state);
        return q.getResultList();
    }

    public List findOpenOrders(Customer c) {
        return getOrdersState(OrderState.OPENED, c);
    }

    public List findClosedOrders(Customer c) {
        return getOrdersState(OrderState.CLOSED, c);
    }

    public List findProcessedOrders(Customer c) {
        return getOrdersState(OrderState.PROCESSED, c);
    }

    private List getOrdersState(OrderState state, Customer c) {
        List<Order> filtered = new ArrayList<>();
        for (Order o : c.getOrders())
            if (o.getState() == state)
                filtered.add(o);
        return filtered;
    }

    public Order findOrder(Long orderID) {
        return this.em.find(Order.class, orderID);
    }

    public void updateOrder(Order o) {
        this.em.merge(o);
    }

    public void insertOrder(Order o) {
        this.em.persist(o);
    }

    public Order processOrder(Long orderID) {
        Order o = this.findOrder(orderID);
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

    public void deleteOrder(Order o) {
        em.remove(em.merge(o));
    }
}