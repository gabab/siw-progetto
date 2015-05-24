package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CreateProduct {

    private Product product;
    private String name;
    private Float price;
    private String description;
    private String code;

    @EJB(name = "product")
    private ProductFacade productFacade;

    public String createProduct() {
        this.product = this.productFacade.createProduct(name, code, price, description);
        return "product";
    }

}
