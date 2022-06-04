package com.bridgelabz.bookstoreapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity
@Data
@Table(name="cart_table")
public class CartData {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cart_id")
	public int cartID;
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	public UserData userID;
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
	public BookData bookId;
	
	@Column(name = "quantity")
	public int quantity;
	 
	 public CartData(UserData userID, BookData bookId, int quantity) {
	        this.userID = userID;
	        this.bookId = bookId;
	        this.quantity = quantity;
	    }

	    public CartData() {
	    }
}
