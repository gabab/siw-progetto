package it.uniroma3.facades;

import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;
import it.uniroma3.model.enums.OrderState;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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


    public List getOpenOrders() {
        Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = " + OrderState.CLOSED);
        return q.getResultList();
    }


    public Order getOrder(Long orderID) {
        return this.em.find(Order.class, orderID);
    }


    public void addOrderLine(int quantity, Long idProduct, Long idOrder) {
        Order order = em.find(Order.class, idOrder);
        Product product = em.find(Product.class, idProduct);
        Float price = product.getPrice();
        order.addOrderLine(new OrderLine(product, price, quantity));
        em.persist(order);
    }
}