package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "search")
@SessionScoped
public class Search extends Paginator {

    @ManagedProperty(value = "#{utils.itemsPerPage}")
    private int itemsPerPage;

    @ManagedProperty(value = "#{userController}")
    private UserController uc;


    @EJB(beanName = "product")
    private ProductFacade productFacade;
    private String searchterm;
    private List results;
    private String searchTitle;

    public List getResults() {
        return results;
    }

    public void setResults(List results) {
        this.results = results;
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
        //TODO: vedere bene gli scope in modo da levare il metodo
        super.reset();
        results = this.productFacade.searchProducts(searchterm);
        if (results.size() == 1) {
            uc.setProduct((Product) this.results.get(0));
            return "pretty:product";
        }
        searchTitle = searchterm;
        searchterm = null;
        super.paginate(results, itemsPerPage);
        return "pretty:search";
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getSearchTitle() {
        return searchTitle;
    }

    public void setUc(UserController uc) {
        this.uc = uc;
    }
}
