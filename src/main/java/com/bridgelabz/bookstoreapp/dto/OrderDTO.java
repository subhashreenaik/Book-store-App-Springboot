package com.bridgelabz.bookstoreapp.dto;

import java.time.LocalDate;

import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.UserData;

import lombok.Data;

@Data
public class OrderDTO {
	public LocalDate date = LocalDate.now();
	public long price;
	public int quantity;
	public String address;
	public int userID;
	public int bookId;
	public boolean cancel;
}
