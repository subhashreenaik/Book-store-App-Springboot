package com.bridgelabz.bookstoreapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.UserdataDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name="book_table")
@NoArgsConstructor
@AllArgsConstructor
public class BookData {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int bookId;
	
	@Column(name="book_name")
	public String bookName;
	
	@Column(name="auther_name")
	public String autherName;
	
	@Column(name="book_description")
	public String bookDescription;
	
	@Column(name="book_img")
	public String bookImg;
	
	@Column(name="price")
	public long price;
	
	@Column(name="quantity")
	public int quantity;
	
	
	public BookData(BookDTO dto) {
		this.bookName = dto.bookName;
		this.autherName = dto.autherName;
		this.bookDescription = dto.bookDescription;
		this.bookImg = dto.bookImg;
		this.price = dto.price;
		this.quantity=dto.quantity;
	}


	
	
	
}
