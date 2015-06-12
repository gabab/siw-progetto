package it.uniroma3.validators;

import it.uniroma3.facades.UserFacade;
import org.apache.commons.validator.routines.EmailValidator;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("EmailValidator")
public class ValidateEmail implements Validator {

    @EJB(name = "user")
    private UserFacade uf;

    public UserFacade getUf() {
        return uf;
    }

    public void setUf(UserFacade uf) {
        this.uf = uf;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid((String) value)) {
            throw new ValidatorException(new FacesMessage("Not a valid email"));
        }
        if (uf.findUser((String) value) != null) {
            throw new ValidatorException(new FacesMessage("Existing email"));
        }


    }

}
