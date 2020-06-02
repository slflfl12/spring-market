package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7296960050350583877L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Cart 와 CartItem이 1:N 관계이므로 CartItem 입장에서는 N:1
	@JoinColumn(name="cartId")
	@ManyToOne  
	private Cart cart;
	
	@JoinColumn(name="productId")
	@ManyToOne  
	private Product product;
	
	private int quantity;
	
	private double totalPrice;
	
	
}
