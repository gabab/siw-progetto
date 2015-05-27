package it.uniroma3.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    private String imagePath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("imagesDirectory");
    private String imageExt = ".jpg";

    public InputStream getImage(String pcode) throws FileNotFoundException {
        String filename = pcode + imageExt;
        filename = (Files.exists(Paths.get(imagePath, filename))) ? filename : "default" + imageExt;
        return new FileInputStream(new File(imagePath, filename));
    }


    public String getPath(String pcode) throws FileNotFoundException {
        String filename = pcode + imageExt;
        filename = (Files.exists(Paths.get(imagePath, filename))) ? filename : "default" + imageExt;
        return imagePath + filename;
    }



}
