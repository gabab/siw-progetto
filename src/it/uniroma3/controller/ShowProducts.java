package it.uniroma3.controller;

import it.uniroma3.utils.Paginator;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
        return listProducts("catalog");
    }

    public String findProduct() {
        this.product = this.productFacade.getProduct(code);
        return "product";
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

}