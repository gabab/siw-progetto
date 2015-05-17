package it.uniroma3.facades;

import it.uniroma3.model.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        List<Product> products = this.em.createQuery("SELECT p FROM Product p").getResultList();
        return products;
    }

}
