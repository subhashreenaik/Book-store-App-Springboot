package com.bridgelabz.bookstoreapp.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserdataDTO {

	
	@NotEmpty(message="FirstName canot be null")
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="person name is invalid")
	public String firstName;
	
	@NotEmpty(message="LastName canot be null")
	@Pattern(regexp="^[A-Z]{1}[a-zA-Z\\s]{2,}$",message="person name is invalid")
	public String lastName;
	
	@Pattern(regexp = "^[a-zA-Z-9]+([._+-][0-9A-Za-z]+)@[a-zA-Z0-9]+.[a-zA-Z]{2,4}([.][a-z]{2,4})?$",message = "email not valid")
    @NotEmpty(message = "Employee Name can't be null")
    public String email;
	
	@Pattern(regexp = "^[A-Za-z,.0-9]{3,}$", message = "Address is Invalid! ")
    @NotEmpty(message = "Address cannot be null")
    public String address;
	
	
	public String password;

	public boolean verified;
	
	
	public UserdataDTO(String firstName, String lastName, String email, String password, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.verified=false;
    }
}
