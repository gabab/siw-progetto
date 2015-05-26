package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@ManagedBean
@SessionScoped
public class CreateProduct {

    private Product product;
    private String name;
    private Float price;
    private String description;
    private String code;
    @EJB(name = "product")
    private ProductFacade productFacade;

    private UploadedFile image;


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String insertProduct() {
        String imageName = getFilePath();
        this.product = this.productFacade.createProduct(name, code, price, description, imageName);
        return "product";
    }

    private String getFilePath() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String directory = externalContext.getInitParameter("imagesDirectory");
        String imagePath = directory + "/default.jpg";
        try {
            InputStream input = image.getInputstream();
            imagePath = code + ".jpg";
            Path file = Paths.get(directory + "/" + imagePath);
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imagePath;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }
}
