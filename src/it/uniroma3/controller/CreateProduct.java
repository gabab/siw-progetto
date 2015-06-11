package it.uniroma3.controller;

import it.uniroma3.facades.ProductFacade;
import it.uniroma3.model.Product;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@ManagedBean
@RequestScoped
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
        this.code = code.toUpperCase();
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


    //TODO: validazione codice prodotto
    public String insertProduct() {
        this.saveImage();
        this.product = this.productFacade.createProduct(name, code, price, description);
        return "product";
    }

    private void saveImage() {
        try {
            InputStream input = image.getInputstream();
            Path file = Paths.get(ImageController.getPath(code));
            Files.copy(input, file, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }
}
