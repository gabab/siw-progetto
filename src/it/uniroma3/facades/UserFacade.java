package it.uniroma3.facades;

import it.uniroma3.model.Administrator;
import it.uniroma3.model.Customer;
import it.uniroma3.model.User;
import it.uniroma3.model.enums.UserGroup;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless(name = "user")
public class UserFacade {

    @PersistenceContext(unitName = "products2-unit")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public User findUser(String email) {
        return (User) this.em.createQuery("SELECT u FROM User u WHERE u.email = '" + email + "'").getSingleResult();
    }

    public void updateInfo(User u) {
        this.em.merge(u);
    }

    public User findUser(String email, UserGroup group) {
        return (User) this.em.createQuery("SELECT u FROM User u WHERE " +
                "(u.email = '" + email + "' AND u.group = " + group + ")").getSingleResult();
    }


    public Customer findCustomer(String email) {
        return (Customer) findUser(email, UserGroup.CUSTOMER);
    }

    public Administrator findAdmin(String email) {
        return (Administrator) findUser(email, UserGroup.ADMINISTRATOR);
    }

    public void createCustomer(String email, String password, String name, String surname, Date birthDate) {
        Customer c = new Customer(email, password, name, surname, birthDate);
        c.setRegistrationDate(new Date());
        this.em.persist(c);
    }

}