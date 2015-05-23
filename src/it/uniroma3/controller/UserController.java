package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserController {
    private static final long serialVersionUID = 1L;
    private Long productID;
    private String name;
    private Float price;
    private String description;
    private String productCode;
    private Product product;
    private List<Product> products;

    private String email;

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

    private String password;

    @EJB(beanName = "product")
    private ProductFacade productFacade;
    private String searchterm;


    public UserController() {

    }


    public String listProducts() {
        this.products = this.productFacade.getAllProducts();
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
        this.products = this.productFacade.searchProducts(searchterm);
        this.searchterm = null;
        if (this.products.size() == 1){
            this.product = this.products.get(0);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String userLogin() {
        return "prova";
    }
}