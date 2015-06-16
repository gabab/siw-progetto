package it.uniroma3.controller;

import it.uniroma3.facades.OrderFacade;
import it.uniroma3.facades.ProductFacade;
import it.uniroma3.utils.Paginator;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "createOrder")
@ViewScoped
public class CreateOrder extends Paginator {

    private String productCode;

    @EJB(name = "product")
    private ProductFacade pf;

    @EJB(name = "order")
    private OrderFacade of;


    public OrderFacade getOf() {
        return of;
    }

    public void setOf(OrderFacade of) {
        this.of = of;
    }

    public ProductFacade getPf() {
        return pf;
    }

    public void setPf(ProductFacade pf) {
        this.pf = pf;
    }

    public String getProductCode() {
        return productCode;
    }

//    public String closeOrder(Long orderID) {
//        Order o = this.of.getOrder(orderID);
//
//    }

//
//    private String closeOrder(Order o, Customer c) {
//        o.close();
//        c.addOrder(o);
//        this.userFacade.updateUser(c);
//        refreshOrders();
//        return "pretty:mypage";
//
//    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
