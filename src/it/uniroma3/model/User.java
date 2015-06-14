package it.uniroma3.model;

import it.uniroma3.enums.UserGroup;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "usergroup")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, name = "usergroup")
    private UserGroup group;

    public User(String email, String password, String name, String surname) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }


    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserGroup getGroup() {
        return group;
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return name + " " + surname;
    }

    public boolean isCustomer() {
        return group == UserGroup.CUSTOMER;
    }

    public boolean isAdmin() {
        return group == UserGroup.ADMINISTRATOR;
    }
}
