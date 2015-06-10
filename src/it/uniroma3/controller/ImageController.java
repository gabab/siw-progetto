package it.uniroma3.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@ManagedBean
@ApplicationScoped
public class ImageController {

    private String getDefaultPath() {
        return getPath(getProperty("defaultImage"));
    }

    public static String getPath(String name) {
        String directory = getProperty("imagePath");
        String ext = getProperty("extension");
        return directory + "/" + name + ext;
    }

    private static String getProperty(String s) {
        String result = null;
        try {
            result = getProperties().getProperty(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Properties getProperties() throws IOException {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        Properties properties = new Properties();
        properties.load(ex.getResourceAsStream("/WEB-INF/config.properties"));
        return properties;
    }

    public static boolean existsFile(String filepath){
        return Files.exists(Paths.get(filepath));
    }


    public InputStream getImage(String code) throws FileNotFoundException {
        String filepath = getPath(code);
        boolean exists = Files.exists(Paths.get(filepath));
        filepath = (exists) ? filepath : getDefaultPath();
        return new FileInputStream(new File(filepath));
    }
}
