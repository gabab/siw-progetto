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
import java.util.HashMap;
import java.util.Map;

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

    public Map<Long, Order> findOpenOrders() {
        return getOrdersState(OrderState.OPENED);
    }

    public Map<Long, Order> findClosedOrders() {
        return getOrdersState(OrderState.CLOSED);
    }

    public Map<Long, Order> findProcessedOrders() {
        return getOrdersState(OrderState.PROCESSED);
    }

    private Map<Long, Order> getOrdersState(OrderState state) {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = :state")
                .setParameter("state", state);
        Map<Long, Order> map = new HashMap<>();
        for (Object o : q.getResultList()) {
            Order order = (Order) o;
            map.put(order.getId(), order);
        }
        return map;
    }

    public Map<Long, Order> findOpenOrders(Customer c) {
        return getOrdersState(OrderState.OPENED, c);
    }

    public Map<Long, Order> findClosedOrders(Customer c) {
        return getOrdersState(OrderState.CLOSED, c);
    }

    public Map<Long, Order> findProcessedOrders(Customer c) {
        return getOrdersState(OrderState.PROCESSED, c);
    }

    private Map<Long, Order> getOrdersState(OrderState state, Customer c) {
        Map<Long, Order> filtered = new HashMap<>();
        for (Order o : c.getOrders())
            if (o.getState() == state)
                filtered.put(o.getId(), o);
        return filtered;
    }

    public Order findOrder(Long orderID) {
        return this.em.find(Order.class, orderID);
    }

    public Order updateOrder(Order o) {
        return this.em.merge(o);
    }

    public Order fulfillOrder(Long orderID) {
        Order o = this.findOrder(orderID);
        for (OrderLine ol : o.getOrderlines().values()) {
            Product p = ol.getProduct();
            if (ol.getQuantity() > p.getInStock()) {
                em.refresh(o);
                return null;
            }
            p.setInStock(p.getInStock() - ol.getQuantity());
        }
        o.fulfill();
        return em.merge(o);
    }

    public void deleteOrder(Order o) {
        em.remove(em.merge(o));
    }
}