package it.uniroma3.utils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@ManagedBean(name = "config", eager = true)
@ApplicationScoped
public class ConfigReader {

    private Properties properties;

    @PostConstruct
    private void readProperties() {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        Properties p = new Properties();
        try {
            p.load(ex.getResourceAsStream("/WEB-INF/config.properties"));
            properties = p;
        } catch (Exception ignored) {
        }
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
        return date == null ? "" : new SimpleDateFormat(properties.getProperty("dateFormat")).format(date);
    }


    public String getDesc(String desc) {
        int maxlen = Integer.parseInt(properties.getProperty("descLen"));
        if (desc == null || desc.length() <= maxlen)
            return desc;
        else
            return desc.substring(0, maxlen) + "...";
    }


    public int getOrderlinesPerPage() {
        return Integer.parseInt(properties.getProperty("orderlinesPerPage"));
    }

    public int getItemsPerPage() {
        return Integer.parseInt(properties.getProperty("itemsPerPage"));
    }

    public List getLastN(List items) {
        int from = items.size() - Integer.parseInt(properties.getProperty("quickViewMaxItems"));
        if (from <= 0)
            return items;
        return items.subList(from, items.size());
    }
}
