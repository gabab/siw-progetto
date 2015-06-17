package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "insertProduct")
@RequestScoped
public class CreateProduct {

    private Product product;
    private String name;
    private Float price;
    private String description;
    private String code;
    private String message;
    @EJB(name = "product")
    private ProductFacade productFacade;
    @ManagedProperty(value = "#{products}")
    private ProductsView sp;

    public void setSp(ProductsView sp) {
        this.sp = sp;
    }

    public void setProductFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void resetData() {
        name = null;
        price = null;
        description = null;
        code = null;
    }

    public void insertProduct() {
        this.product = this.productFacade.createProduct(name, code, price, description);
        sp.setCode(code);
        sp.setProduct(product);
        this.message = name + " successfully inserted";
        resetData();
    }

    public String getMessage() {
        return message;
    }
}