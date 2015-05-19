package it.uniroma3.facades;

import it.uniroma3.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "product")
public class ProductFacade {

    @PersistenceContext(unitName = "products2-unit")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Product getProduct(Long id){
        return this.em.find(Product.class, id);
    }

    public List<Product> getAllProducts() {
        List<Product> products = (List<Product>) this.em.createQuery("SELECT p FROM Product p").getResultList();
        return products;
    }

    public void updateProduct(Product p){
        this.em.merge(p);
    }

    public List<Product> searchProducts(String searchterm) {
        String id = "";
        searchterm = searchterm.toLowerCase();
        try {
            Long.parseLong(searchterm);
            id = "OR p.id = " + searchterm;
        } catch (NumberFormatException ignored) {}
        Query q = this.em.createQuery(
                "SELECT p FROM Product p WHERE (" +
                        "LOWER(p.name) LIKE '%" + searchterm + "%' " +
                        "OR LOWER(p.code) LIKE '%" + searchterm + "%'" +
                        "OR LOWER(p.description) LIKE '%" + searchterm + "%'" + id + ")");
        return q.getResultList();
    }

    public Product createProduct(String name, Float price, String code, String description){
        Product p = new Product(name, code, description, price);
        this.em.persist(p);
        return p;
    }
}
