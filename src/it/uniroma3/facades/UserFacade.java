package it.uniroma3.facades;

import it.uniroma3.enums.UserGroup;
import it.uniroma3.model.Administrator;
import it.uniroma3.model.Customer;
import it.uniroma3.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Stateless(name = "user")
public class UserFacade {

    @PersistenceContext(unitName = "siw-unit")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public User findUser(Long id) {
        return this.em.find(User.class, id);
    }

    public Customer findCustomer(Long id) {
        return this.em.find(Customer.class, id);
    }

    public void updateUser(User u) {
        this.em.merge(u);
    }

    public User findUser(String email, UserGroup group) {
        String subQuery = (group == null) ? "" : " AND u.group = " + group;
        User u = null;
        try {
            u = (User) this.em.createQuery("SELECT u FROM User u WHERE " +
                    "u.email = :email" + subQuery)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (Exception ignored) {
        }
        return u;
    }


    public User findUser(String email) {
        return findUser(email, null);
    }

    public Customer findCustomer(String email) {
        return (Customer) findUser(email, UserGroup.CUSTOMER);
    }

    public Administrator findAdmin(String email) {
        return (Administrator) findUser(email, UserGroup.ADMINISTRATOR);
    }

    public void createCustomer(String email, String password, String name, String surname, Date birthDate) {
        Customer c = new Customer(email, password, name, surname, birthDate, UserGroup.CUSTOMER);
        c.setRegistrationDate(new Date());
        this.em.persist(c);
    }

    public void updateCustomer(Customer c) {
        this.em.merge(c);
    }
}