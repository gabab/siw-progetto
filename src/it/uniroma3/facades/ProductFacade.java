package it.uniroma3.facades;

import it.uniroma3.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "product")
public class ProductFacade {

    @PersistenceContext(unitName = "siw-unit")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Product getProduct(Long id) {
        return this.em.find(Product.class, id);
    }

    public List getAllProducts() {
        return this.em.createQuery("SELECT p FROM Product p").getResultList();
    }

    public void updateProduct(Product p) {
        this.em.merge(p);
    }

    public List searchProducts(String searchterm) {
        searchterm = searchterm.toLowerCase();
        Query q = this.em.createQuery(
                "SELECT p FROM Product p WHERE (" +
                        "LOWER(p.name) LIKE '%" + searchterm + "%' " +
                        "OR LOWER(p.code) LIKE '%" + searchterm + "%'" +
                        "OR LOWER(p.description) LIKE '%" + searchterm + "%')");
        return q.getResultList();
    }
     

    public Product createProduct(String name, String code, Float price, String description) {
        Product p = new Product(name, code, price, description);
        this.em.persist(p);
        return p;
    }


}
