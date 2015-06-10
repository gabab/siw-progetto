package it.uniroma3.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Properties;

@ManagedBean(name = "utils")
@ApplicationScoped
public class UtilsBean {

    private static Properties getProperties() throws IOException {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        Properties properties = new Properties();
        properties.load(ex.getResourceAsStream("/WEB-INF/config.properties"));
        return properties;
    }

    public String getPrice(float p) {
        try {
            Properties prop = getProperties();
            p = p * (1 + Float.parseFloat(prop.getProperty("VAT")));
            return prop.getProperty("currency") + " " + String.format("%.2f", p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
