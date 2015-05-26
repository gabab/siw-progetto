package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserController {
    private static final long serialVersionUID = 1L;
    private Long productID;
    private Product product;
    private List products;

    private String email;
    private String password;
    @EJB(beanName = "product")
    private ProductFacade productFacade;
    private String searchterm;
    private String productsViewTitle;

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
        this.productsViewTitle = "Products Catalog";
        return "products";
    }

    public String findProduct() {
        this.product = this.productFacade.getProduct(productID);
        return "product";
    }

    public String findProduct(Long id) {
        this.product = this.productFacade.getProduct(id);
        return "product";
    }

    public String searchProducts() {
        if (searchterm.equals(""))
            return listProducts();
        this.products = this.productFacade.searchProducts(searchterm);
        this.productsViewTitle = "Search results for \"" + searchterm + "\"";
        this.searchterm = null;
        if (this.products.size() == 1) {
            this.product = (Product) this.products.get(0);
            return "product";
        }
        return "products";
    }



    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String userLogin() {
        return "prova";
    }

    public String getProductsViewTitle() {
        return productsViewTitle;
    }
}