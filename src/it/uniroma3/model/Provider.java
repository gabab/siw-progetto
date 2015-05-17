package it.uniroma3.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String pIVA;
    @ManyToMany
    private List<Product> products;
    @OneToOne
    private Address address;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;

    public Provider(String pIVA, Address address, String phone, String email) {
        this.pIVA = pIVA;
        this.products = new ArrayList<>();
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Provider() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", pIVA='" + pIVA + '\'' +
                ", products=" + products +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Provider provider = (Provider) o;

        if (!getId().equals(provider.getId())) return false;
        if (!getpIVA().equals(provider.getpIVA())) return false;
        if (!getAddress().equals(provider.getAddress())) return false;
        if (!getPhone().equals(provider.getPhone())) return false;
        return getEmail().equals(provider.getEmail());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getpIVA().hashCode();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getPhone().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
