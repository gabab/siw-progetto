package it.uniroma3.model;

import it.uniroma3.model.enums.OrderState;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {

	@Column(nullable = false)
	public OrderState state;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date opened;
	@Temporal(TemporalType.TIMESTAMP)
	private Date closed;
	@Temporal(TemporalType.TIMESTAMP)
	private Date processed;
	@ManyToOne
	private Customer customer;
	@OneToMany
	@JoinColumn(name = "orders_id")
	private Map<Long, OrderLine> orderlines;

	public Order() {

	}

	public Order(Customer customer) {
		this.state = OrderState.OPENED;
		this.opened = new Date();
		this.customer = customer;
		this.orderlines = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public void addProductToOrder(Product p, int quantity) {
		OrderLine a = orderlines.get(p.getId());
		if (a == null)
			a = orderlines.put(p.getId(), new OrderLine(p, 0));
		a.setQuantity(a.getQuantity() + quantity);	
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOpened() {
		return opened;
	}

	public void setOpened(Date opened) {
		this.opened = opened;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {
		this.closed = closed;
	}

	public Date getProcessed() {
		return processed;
	}

	public void setProcessed(Date processed) {
		this.processed = processed;
	}

	public Map<Long, OrderLine> getOrderlines() {
		return orderlines;
	}

	public void setOrderlines(Map<Long, OrderLine> orderlines) {
		this.orderlines = orderlines;
	}

}
