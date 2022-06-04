package com.bridgelabz.bookstoreapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.ForgotPwdDTO;
import com.bridgelabz.bookstoreapp.dto.LoginDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.UserdataDTO;
import com.bridgelabz.bookstoreapp.exceptions.BookStoreException;
import com.bridgelabz.bookstoreapp.model.Email;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repo.UserDataRepo;
import com.bridgelabz.bookstoreapp.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class UserDataService {
    
	@Autowired
	UserDataRepo repo;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	EmailService emailService;



	    
	    public ResponseEntity<ResponseDTO> createAccount(UserdataDTO userDto) {

	        UserData user= repo.save(new UserData(userDto));

	        String token = tokenUtil.createToken(user.getUserID());

	        Email email = new Email(user.getEmail()," user is registered",user.getFirstName() + "=>" + emailService.getLink(token));
	        emailService.sendMail(email);
	        ResponseDTO responseDto = new ResponseDTO("User is created", user, token);
	        return new ResponseEntity<>(responseDto,HttpStatus.OK);
	    }

	    
	    public ResponseEntity<ResponseDTO> getAll() {
	        log.info(" we featching all user deatils");
	        List<UserData> userList= repo.findAll();
	        ResponseDTO responseDto=new ResponseDTO(" all user deatils",userList,null);
	        return new ResponseEntity<>(responseDto,HttpStatus.OK);
	    }

	    
	    public UserData getUserDataById(int id) {
	        return repo.findById(id).
	        		orElseThrow(() -> new BookStoreException("selected id is"+id+ "not found "));
	    }

	    
	    public ResponseEntity<ResponseDTO> forgotPwd(ForgotPwdDTO forgotPWDDto) {
	        Optional<UserData> user=repo.getUserByEmail(forgotPWDDto.getEmail());
	            //String body="http://localhost:4200/resetpassword/"+tokenUtil.createToken(user.get().getUserID());
	            Email email= new Email(user.get().getEmail(), "verification mail", user.get().getFirstName());
	            return  emailService.sendMail(email);
	    }




	    
	    public ResponseEntity<ResponseDTO> verify(String token) {
	    	
	        Optional<UserData> user=repo.findById(Math.toIntExact(tokenUtil.decodeToken(token)));
	        
	        if (user.isEmpty()) {
	        	ResponseDTO responseDTO = new ResponseDTO("ERROR: Invalid token", null, token);
	            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
	        }
	        user.get().setVerified(true);
	        repo.save(user.get());
	        ResponseDTO responseDTO = new ResponseDTO(" The user has been verified ", user, token);
	        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
	    }

	    
	    public ResponseEntity<ResponseDTO> loginUser(LoginDTO dto){
	       Optional<UserData> user=repo.findByEmail(dto.getEmail());
	        
	       log.info("passwor  is      "+user.get().getPassword());
	        if(!user.get().getPassword().equals(dto.getPassword())){
	        	
	        	ResponseDTO responseDto=new ResponseDTO("login failed",null,null);
	                return new ResponseEntity<ResponseDTO>(responseDto,HttpStatus.UNAUTHORIZED);
	        }
	        else{
	        	ResponseDTO responseDto=new ResponseDTO(" Login Sucessfully",user,null);
	             return new ResponseEntity<>(responseDto,HttpStatus.OK);
	        }

	    }


	}

