package com.bridgelabz.bookstoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderservice;
	
	@PostMapping("/create_order")
    public ResponseEntity<ResponseDTO> createNewOrder(@RequestBody OrderDTO orderDTO){
		OrderData orderData = orderservice.createNewOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Product going to be placed ", orderData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);

      }
	
	@GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAllOrders(){
		ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", orderservice.getAllOrders());
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
}