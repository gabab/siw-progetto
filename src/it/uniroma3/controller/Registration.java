package it.uniroma3.controller;

import it.uniroma3.facades.UserFacade;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;

@ManagedBean
@SessionScoped
public class Registration {
    @EJB(name = "user")
    private UserFacade uf;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    //TODO: verificare unicità email;
    public String requestRegistration() {
        this.uf.createCustomer(email, password, name, surname, birthDate);
        return "confirmation";
    }


}
