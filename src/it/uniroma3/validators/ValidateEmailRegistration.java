package it.uniroma3.validators;

import it.uniroma3.facades.UserFacade;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class ValidateEmailRegistration implements Validator {

    @EJB(name = "user")
    private UserFacade uf;

    public UserFacade getUf() {
        return uf;
    }

    public void setUf(UserFacade uf) {
        this.uf = uf;
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {
        String email = (String) value;
        if (uf.findUser(email) != null) {
            throw new ValidatorException(new FacesMessage("Existing email"));
        }

        new ValidateEmail().validate(context, component, value);

    }

}
