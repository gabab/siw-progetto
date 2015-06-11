package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class SearchBean {
    @ManagedProperty(value = "#{userController}")
    private UserController uc;

    @EJB(beanName = "product")
    private ProductFacade productFacade;
    private String searchterm;

    public UserController getUc() {
        return uc;
    }

    public void setUc(UserController uc) {
        this.uc = uc;
    }

    public ProductFacade getProductFacade() {
        return productFacade;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public String searchProducts() {
        if (searchterm.equals(""))
            return uc.listProducts();
        List results = this.productFacade.searchProducts(searchterm);
        System.out.println(searchterm);
        String title = "Search results for \"" + searchterm + "\"";
        if (results.size() == 1) {
            uc.setProduct((Product) results.get(0));
            return "product";
        }
        uc.setProductsViewTitle(title);
        uc.setProducts(results);
        return "products";
    }
}
