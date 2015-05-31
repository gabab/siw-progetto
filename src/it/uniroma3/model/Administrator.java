package it.uniroma3.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMINISTRATOR")
public class Administrator extends User {


    public Administrator() {
        super();
    }
}
