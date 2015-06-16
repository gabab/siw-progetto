package it.uniroma3.controller;

import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.Address;
import it.uniroma3.model.Customer;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean(name = "address")
@RequestScoped
public class AddressController {
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    @EJB(beanName = "user")
    private UserFacade uf;

    public String insertAddress(Customer c) {
        if (c != null) {
            Address a = new Address(street, city, state, zipcode, country);
            c.setAddress(a);
            uf.updateUser(c);
        }
        return "pretty:address";
    }

    public void loadAddress(Address a) {
        this.street = a.getStreet();
        this.city = a.getCity();
        this.state = a.getState();
        this.zipcode = a.getZipcode();
        this.country = a.getCountry();
    }

    public String modifyAddress(Customer c) {
        loadAddress(c.getAddress());
        c.setAddress(null);
        return "pretty:address";

    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
