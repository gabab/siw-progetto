package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "search")
@RequestScoped
public class Search {

    @ManagedProperty(value = "#{products}")
    private ShowProducts sp;

    @EJB(beanName = "product")
    private ProductFacade productFacade;
    private String searchterm;

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
        List results = this.productFacade.searchProducts(searchterm);
        sp.setPageTitle(searchterm);
        sp.setProducts(results);
        return sp.listProducts("pretty:search");
    }

    public void setSp(ShowProducts sp) {
        this.sp = sp;
    }
}
