package it.uniroma3.controller;

import it.uniroma3.facades.UserFacade;
import it.uniroma3.model.User;
import it.uniroma3.model.enums.UserGroup;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class Login {

    @EJB(name = "user")
    private UserFacade userFacade;

    private User user;

    private String errorMessage;

    private String email;
    private String password;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public User getUser() {
        return user;
    }


    public boolean isAdmin() {
        return user.getGroup() == UserGroup.ADMINISTRATOR;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public String login() {
        User u = this.userFacade.findUser(email);
        if (u == null) {
            this.errorMessage = "Invalid email";
            return "login";
        } else if (!this.password.equals(u.getPassword())){
            this.errorMessage = "Invalid password";
            return "login";
        }
        this.user = u;
        return "index";
    }

    public void logout() {
        user = null;
    }
}