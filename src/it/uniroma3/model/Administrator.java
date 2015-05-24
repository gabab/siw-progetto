package it.uniroma3.model;

import it.uniroma3.model.enums.UserGroup;

import javax.persistence.*;

@Entity
public class Administrator extends User {


    public Administrator() {
        super();
        this.setGroup(UserGroup.ADMINISTRATOR);
    }
}
