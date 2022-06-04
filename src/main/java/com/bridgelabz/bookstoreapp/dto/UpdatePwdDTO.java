package com.bridgelabz.bookstoreapp.dto;

public class UpdatePwdDTO {
	 private String newPassword;
	    private String conformPassword;

	    public UpdatePwdDTO(String newPassword, String conformPassword) {
	        this.newPassword = newPassword;
	        this.conformPassword = conformPassword;
	    }
}
