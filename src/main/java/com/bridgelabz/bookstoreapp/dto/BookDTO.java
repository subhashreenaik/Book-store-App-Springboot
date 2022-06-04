package com.bridgelabz.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

	
	public String bookName;
	public String autherName;
	public String bookDescription;
	public String bookImg;
	public long price;
	public int quantity;
	
	
}
