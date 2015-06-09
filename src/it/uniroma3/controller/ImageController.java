package it.uniroma3.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@ManagedBean
@ApplicationScoped
public class ImageController {

    private String getDefaultPath() {
        return getPath("default");
    }

    public static String getPath(String name) {
        ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
        String directory = ex.getInitParameter("imagesDirectory");
        String ext = ".jpg";
        return directory + "/" + name + ext;
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
