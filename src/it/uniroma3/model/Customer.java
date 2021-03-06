package it.uniroma3.model;

import it.uniroma3.enums.UserGroup;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "CUSTOMER")
public class Customer extends User {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Customer(String email, String password, String name, String surname, Date birthDate, UserGroup group) {
        super(email, password, name, surname);
        this.setGroup(group);
        this.birthDate = birthDate;
        this.registrationDate = new Date();
        this.orders = new ArrayList<>();
    }

    public Customer() {
        super();
    }

    public void addOrder(Order o) {
        this.orders.add(o);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + this.getName() + '\'' +
                ", surname='" + this.getSurname() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", address=" + this.address +
                ", registrationDate=" + this.registrationDate +
                ", birthDate=" + this.birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!getName().equals(customer.getName())) return false;
        if (!getSurname().equals(customer.getSurname())) return false;
        if (!getEmail().equals(customer.getEmail())) return false;
        if (!getPassword().equals(customer.getPassword())) return false;
        return getBirthDate().equals(customer.getBirthDate());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getBirthDate().hashCode();
        return result;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public boolean hasAddress() {
        return address != null;
    }

    public void removeOrder(Order o) {
        this.orders.remove(o);
    }
}


