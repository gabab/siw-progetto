package it.uniroma3.facades;

import it.uniroma3.model.Customer;




import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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



    
  
   
}
