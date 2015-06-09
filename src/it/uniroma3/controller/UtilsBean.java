package it.uniroma3.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "utils")
@ApplicationScoped
public class UtilsBean {

    private String currency = "EUR";

    private float VAT = (float) 0.22;

    public String getPrice(float p) {
        p = p * (1 + VAT);
        return currency + " " + String.format("%.2f", p);
    }
}
