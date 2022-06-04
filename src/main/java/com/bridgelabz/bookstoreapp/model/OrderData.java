package com.bridgelabz.bookstoreapp.model;

import java.time.LocalDate;

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

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name="order_table")
public class OrderData {
	
	

	

	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int orderID;
	
	@Column(name="date")
	public LocalDate date = LocalDate.now();
	
	@Column(name="price")
	public long price;
	
	@Column(name="quantity")
	public int quantity;
	
	@Column(name="address")
	public String address;
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	public UserData userID;
	
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="book_id")
	public BookData bookId;
	
	@Column(name="cancel")
	public boolean cancel;
	
	public OrderData(UserData userData, BookData bookData, OrderDTO orderDTO) {
		this.userID=userData;
		this.bookId=bookData;
		this.date=orderDTO.date;
		this.price=orderDTO.price;
		this.address =orderDTO.address;
		
	}
	public OrderData() {
		
	}
}
