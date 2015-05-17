package it.uniroma3.facades;

import it.uniroma3.model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "customer")
public class CustomerFacade {

    @PersistenceContext(unitName = "products2-unit")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Order> getOrders(){
        return null;
    }


}
