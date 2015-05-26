package it.uniroma3.facades;

import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless(name = "customer")
public class CustomerFacade {

    @PersistenceContext(unitName = "siw-unit")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Customer getCustomer(Long id) {
        Customer customer = this.em.find(Customer.class, id);
        return customer;
    }

    public Customer getCustomer(String email) {
        Query q = this.em.createQuery("SELECT c FROM Customer c WHERE c.email = " + email);
        return (Customer) q.getSingleResult();
    }


    public List<Order> getOrders() {
        CriteriaQuery<Order> cq = em.getCriteriaBuilder().createQuery(Order.class);
        cq.select(cq.from(Order.class));
        List<Order> orders = em.createQuery(cq).getResultList();
        return orders;
    }

    public Order createOrder() {
        Order order = new Order();
        em.persist(order);
        return order;
    }

}
