package it.uniroma3.facades;

import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.enums.OrderState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "order")
public class OrderFacade {

    @PersistenceContext(unitName = "products2-unit")
    private EntityManager em;


    public EntityManager getEm() {
        return em;
    }


    public void setEm(EntityManager em) {
        this.em = em;
    }


    public List getOpenOrders() {
        Query q = this.em.createQuery("SELECT o FROM orders o WHERE o.state = " + OrderState.CLOSED);
        return q.getResultList();
    }


    public Order getOrder(Long orderID) {
        return this.em.find(Order.class, orderID);
    }
}