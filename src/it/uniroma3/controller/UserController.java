package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserController {
    private String code;
    private Product product;
    private List products;

    private String email;
    private String password;
    @EJB(beanName = "product")
    private ProductFacade productFacade;

    public UserController() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String listProducts() {
        this.products = this.productFacade.getAllProducts();
        return "products";
    }

    public String findProduct() {
        return findProduct(code);
    }

    public String findProduct(String code) {
        this.product = this.productFacade.getProduct(code);
        return "product";
        //return (this.product != null) ? "product" : "notFound";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}