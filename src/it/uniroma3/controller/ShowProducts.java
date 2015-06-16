package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;
import it.uniroma3.utils.Paginator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "products")
@SessionScoped
public class ShowProducts extends Paginator {

    @EJB(beanName = "product")
    private ProductFacade productFacade;

    private String code;

    private List products;
    private Product product;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List getProducts() {
        return products;
    }

    public void setProducts(List products) {
        this.products = products;
    }

    public String viewCatalog() {
        this.products = this.productFacade.getAllProducts();
        this.setPageTitle("Products Catalog");
        return listProducts(null);
    }

    public String findProduct() {
        this.product = this.productFacade.getProduct(code);
        return "pretty:product";

    }

    public void loadProduct() {
        if (this.product == null || !this.product.getCode().equals(code)) {
            this.product = this.productFacade.getProduct(code);
        }
    }

    public String findProduct(String code) {
        this.code = code;
        return findProduct();
    }

    public void listProducts() {
        this.paginate(products);
    }

    public String listProducts(String s) {
        this.paginate(products);
        return s;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List getLastProducts() {
        return this.productFacade.getLastProducts(6);
    }

}