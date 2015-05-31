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

    public void updateUser(User u) {
        this.em.merge(u);
    }

    public User findUser(String email, UserGroup group) {
        String subQuery = (group == null) ? "'" + email + "'" : " AND u.group = " + group;
        User u = null;
        try {
            u = (User) this.em.createQuery("SELECT u FROM User u WHERE " +
                    "u.email = " + subQuery).getSingleResult();
        } catch (Exception ignored) {
        }
        return u;
    }


    public User findUser(String email) {
        return (User) this.em.createQuery("SELECT u FROM User u WHERE " +
                "u.email = '" + email + "'").getSingleResult();
        //findUser(email, null);
    }

    public Customer findCustomer(String email) {
        return (Customer) findUser(email, UserGroup.CUSTOMER);
    }

    public Administrator findAdmin(String email) {
        return (Administrator) findUser(email, UserGroup.ADMINISTRATOR);
    }

    public void createCustomer(String email, String password, String name, String surname, Date birthDate, UserGroup group) {
        Customer c = new Customer(email, password, name, surname, birthDate, group);
        c.setRegistrationDate(new Date());
        this.em.persist(c);
    }

    public void createPendingCustomer(String email, String password, String name, String surname, Date birthDate) {
        createCustomer(email, password, name, surname, birthDate, UserGroup.CUSTOMER);
    }

    public void createRegisteredCustomer(String email, String password, String name, String surname, Date birthDate) {
        createCustomer(email, password, name, surname, birthDate, UserGroup.CUSTOMER);
    }
}