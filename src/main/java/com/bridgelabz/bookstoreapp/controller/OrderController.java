package com.bridgelabz.bookstoreapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exceptions.UserRegistrationException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.service.OrderService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderservice;
	
	@Autowired
	TokenUtil tokenutil;
	
	@PostMapping("/create_order")
    public ResponseEntity<ResponseDTO> createNewOrder(@RequestBody OrderDTO orderDTO){
		ResponseEntity<ResponseDTO> orderData = orderservice.createNewOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Product going to be placed ", orderData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);

      }
	
	@GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAllOrders(){
		ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", orderservice.getAllOrders());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
	
	@GetMapping("/get/{token}")
	public ResponseEntity<ResponseDTO> getOrderDataById(@PathVariable("order_Id") String token){
		int order_Id= tokenutil.decodeToken(token);
		Optional<OrderData> orderData=orderservice.getOrderDataById(order_Id);
	    ResponseDTO responseDTO = new ResponseDTO("Get Call Success for id:", orderData);
	    return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}

	@PutMapping("/cancelOrder/{token}/{userId}")
	public ResponseEntity<ResponseDTO> cancelOrder(@PathVariable("token") String token,@PathVariable("userId") int userId) throws UserRegistrationException{
	    int orderId = tokenutil.decodeToken(token);
	    return orderservice.cancelOrder(orderId,userId);
	
}
}