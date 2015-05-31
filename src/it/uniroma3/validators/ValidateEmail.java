package it.uniroma3.validators;

import org.apache.commons.validator.routines.EmailValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("ValidateEmail")
public class ValidateEmail implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid((String) value)) {
            throw new ValidatorException(new FacesMessage("Not a valid email"));
        }
    }

}


