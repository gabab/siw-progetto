package it.uniroma3.facades;

import it.uniroma3.model.User;

import javax.ejb.Stateless;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public User findUser(String email){
        List results = this.em.createQuery("SELECT u FROM users u WHERE u.email = " + email).getResultList();
        return (results.size() == 0) ? null : (User) results.get(0);
    }
}