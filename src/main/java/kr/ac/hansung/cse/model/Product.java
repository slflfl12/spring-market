package kr.ac.hansung.cse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity // Hibernate = jpa + native
@Table(name="product")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -567117648902464025L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //IDENTITY는 auto_increment, AUTO는 TABLE, hibernate_sequence라는 테이블이 생긴다.
	@Column(name="product_id", nullable = false, updatable = false) //null이 되면 안되고, 수정되면 안된다.
	private int id;
	
	//@NotEmpty(message="The product name must not be null")
	private String name;
	
	private String category;
	
	//@Min(value=0, message="The product price must not be less than zero")
	private int price;
	
	private String manufacturer;
	
	private int unitInStock;
	
	private String description;
	
	@Transient //얘는 DB테이블에 저장 안됨
	private MultipartFile productImage; //여기는 FileName만 저장 (파일자체를 DB에 저장하는 것 보다는 별도의 Image Server, FileSystem에 저장하는게 바람직)
	
	private String imageFilename;
}
