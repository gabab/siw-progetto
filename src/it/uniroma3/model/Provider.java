package it.uniroma3.model;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
public class Provider {

    @Id
    private Long id;

    @Column(nullable = false)
    private String pIVA;

    private List<Product> products;

    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;
}
