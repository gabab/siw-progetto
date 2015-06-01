package it.uniroma3.validators;

import it.uniroma3.facades.ProductFacade;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

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
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {
        String code = (String) value;
        if (pf.getProduct(code) != null) {
            throw new ValidatorException(new FacesMessage("Existing code"));
        }
    }
}
