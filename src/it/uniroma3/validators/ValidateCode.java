package it.uniroma3.validators;

import it.uniroma3.facades.ProductFacade;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator(value = "CodeValidator")
public class ValidateCode implements Validator {

    @EJB(name = "product")
    private ProductFacade pf;

    public ProductFacade getPf() {
        return pf;
    }

    public void setPf(ProductFacade pf) {
        this.pf = pf;
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String code = (String) value;
        // resettiamo il valore nel caso il codice non sia valido
        ((UIInput) component).setSubmittedValue("");
        if (code.length() != 10)
            throw new ValidatorException(new FacesMessage("Code must be 10 characters long"));
        if (!Pattern.matches("^[a-zA-Z0-9]{10}$", code))
            throw new ValidatorException(new FacesMessage("Only alphanumeric characters allowed"));
        if (pf.getProduct(code.toUpperCase()) != null) {
            throw new ValidatorException(new FacesMessage("Existing code"));
        }
        ((UIInput) component).setSubmittedValue(code);
    }
}
