package it.uniroma3.utils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@ManagedBean(name = "config", eager = true)
@ApplicationScoped
public class ConfigReader {

    private Properties properties;

    @PostConstruct
    private void readProperties(){
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        Properties p = new Properties();
        try {
            p.load(ex.getResourceAsStream("/WEB-INF/config.properties"));
            properties = p;
        } catch (Exception ignored) {}
    }

    private boolean imageExits(String path) {
        return FacesContext.getCurrentInstance().getApplication()
                .getResourceHandler().createResource(path) != null;
    }

    public String getImagePath(String code) {
        String extension = properties.getProperty("extension");
        String imagePath = properties.getProperty("imagePath");
        String defaultImage = properties.getProperty("defaultImage");
        String filepath = imagePath + code + extension;
        return imageExits(filepath) ? filepath : imagePath + defaultImage + extension;
    }

    public String getTaxes(float p) {
        p *= (Float.parseFloat(properties.getProperty("VAT")));
        return properties.getProperty("currency") + " " + String.format("%.2f", p);
    }

    public String getPrice(float p) {
        p *= (1 + Float.parseFloat(properties.getProperty("VAT")));
        return properties.getProperty("currency") + " " + String.format("%.2f", p);
    }

    public String getBrand() {
        return properties.getProperty("brand");
    }

    public String formatDate(Date date) {
        return new SimpleDateFormat(properties.getProperty("dateFormat")).format(date);
    }


    public int getItemsPerPage() {
        try {
            return Integer.parseInt(properties.getProperty("itemsPerPage"));
        } catch(Exception ignored) {}
        return 0;
    }
}