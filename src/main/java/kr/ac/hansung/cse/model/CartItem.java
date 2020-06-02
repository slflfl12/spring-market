package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CartItem implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//Cart 와 CartItem이 1:N 관계이므로 CartItem 입장에서는 N:1
	@JoinColumn(name="cartId")
	@ManyToOne
	@JsonIgnore // cart에가면 CartItem이있고  cartItem에 가면 또 다시 cart가 있고 무한 반복하므로 -> Serialization할 때 cartItem에 갔을 경우에 cart는 무시해라
	private Cart cart;
	
	@JoinColumn(name="productId")
	@ManyToOne  
	private Product product;
	
	private int quantity;
	
	private double totalPrice;
	
	
}
