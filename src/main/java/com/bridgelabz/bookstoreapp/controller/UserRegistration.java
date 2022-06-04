package com.bridgelabz.bookstoreapp.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.ForgotPwdDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserdataDTO;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.service.UserDataService;


@RestController
@RequestMapping("/user")
public class UserRegistration {

	@Autowired
	UserDataService service;
	
	
	
	@GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll(){
        return service.getAll();
   }

   @PostMapping("/register")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody UserdataDTO userDto){
     return service.createAccount(userDto);
    }

   @GetMapping("/getById/{id}")
    public UserData getById(@PathVariable int id){
        return service.getUserDataById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDto){
       return service.loginUser(loginDto);

  }

    @PostMapping("/forgot")
   public ResponseEntity<ResponseDTO> forgotPwd( @RequestBody ForgotPwdDTO forgotPWDDto){
   return service.forgotPwd(forgotPWDDto);
   }
   @GetMapping("/verify")
    public ResponseEntity<ResponseDTO> verifyUser(@RequestHeader("token") String token) {
       return service.verify(token);
    }
}
