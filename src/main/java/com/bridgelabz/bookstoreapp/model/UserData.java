package com.bridgelabz.bookstoreapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstoreapp.dto.UserdataDTO;

import lombok.Data;

@Entity
@Data
@Table(name="registration_table")
public class UserData {
	
	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userid")
	public int userID;
	
	@Column(name="first_name")
	public String firstName;
	
	@Column(name="last_name")
	public String lastName;
	
	@Column(name="email")
	public String email;
	
	@Column(name="address")
	public String address;
	
	@Column(name="password")
	public String password;
	
	public boolean verified;
	
	public UserData(UserdataDTO dto) {
		this.firstName = dto.firstName;
		this.lastName = dto.lastName;
		this.email = dto.email;
		this.address = dto.address;
		this.password = dto.password;
		this.verified=dto.verified;
	}
	public UserData() {
		
	}
	public void setVerified(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
