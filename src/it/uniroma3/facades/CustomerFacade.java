package it.uniroma3.facades;

import it.uniroma3.model.Customer;
import it.uniroma3.model.Order;
import it.uniroma3.model.enums.UserGroup;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
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
        return null;
    }

    public Customer getCustomer(String email) {
        return null;
    }



    public List<Order> getOrders() {
        return null;
    }
  
    public Order createOrder(){
     Order order = new Order();
     em.persist(order);
     return order;
    }

}
