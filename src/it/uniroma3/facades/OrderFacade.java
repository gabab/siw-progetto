package it.uniroma3.facades;

import it.uniroma3.model.Order;
import it.uniroma3.model.OrderLine;
import it.uniroma3.model.Product;
import it.uniroma3.model.enums.OrderState;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "order")
public class OrderFacade {

	@PersistenceContext(unitName = "siw-unit")
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List getClosedOrders() {
		Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = "
				+ OrderState.CLOSED);
		return q.getResultList();
	}

	public List getOrdersState(OrderState state, Long customerID) {
		Query q = this.em.createQuery("SELECT o FROM Order o WHERE o.state = "
				+ state + "AND o.customer = " + customerID);
		return q.getResultList();
	}

	public Order getOrder(Long orderID) {
		return this.em.find(Order.class, orderID);
	}
	
//	
//
//	public void addOrderLine(int quantity, Long idProduct, Long idOrder) {
//		Order order = em.find(Order.class, idOrder);
//		Product product = em.find(Product.class, idProduct);
//		Float price = product.getPrice();
//		order.addOrderLine(new OrderLine(product, price, quantity));
//		em.persist(order);
//	}
	
	public void updateOrder(Order o){
		this.em.merge(o);
	}

	public Order processOrder(Long orderID) {
		Order o = this.getOrder(orderID);
		for (OrderLine ol : o.getOrderlines()) {
			Product p = ol.getProduct();
			if (ol.getQuantity() > p.getInStock())
				return null;
			p.setInStock(p.getInStock() - ol.getQuantity());

		}

		o.setState(OrderState.PROCESSED);
		em.persist(o);
		return o;
	}
	
	 public Order createOrder(){
	     Order order = new Order();
	     em.persist(order);
	     return order;
	    }

}