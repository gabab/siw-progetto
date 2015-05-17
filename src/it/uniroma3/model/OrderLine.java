package it.uniroma3.model;

import javax.persistence.*;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Float unitPrice;
    @Column(nullable = false)
    private Integer quantity;

    public OrderLine(Float unitPrice, Integer quantity) {
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public OrderLine() {

    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLine orderLine = (OrderLine) o;

        if (!id.equals(orderLine.id)) return false;
        if (!getUnitPrice().equals(orderLine.getUnitPrice())) return false;
        return getQuantity().equals(orderLine.getQuantity());

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + getUnitPrice().hashCode();
        result = 31 * result + getQuantity().hashCode();
        return result;
    }
}
