package com.bridgelabz.bookstoreapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.service.CartService;
import com.bridgelabz.bookstoreapp.util.TokenUtil;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartservice;
	
	@Autowired
	TokenUtil tokenutil;
	
	@PostMapping("/add_cart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        CartData cartData = cartservice.addToCart(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Product Added To Cart ", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }
	
	@GetMapping(value = {"", "/"})
    public ResponseEntity<ResponseDTO> findAllCarts() {
        Iterable<CartData> allCarts = cartservice.findAllCarts();
        ResponseDTO responseDTO = new ResponseDTO("All Items in Carts", allCarts);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
	
	@GetMapping("/getbyid/{token}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable("cartId") String token) {
		int cartId= tokenutil.decodeToken(token);
        CartData cartData = cartservice.getCartById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Get Cart call Success for Id", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
	@PutMapping("/update_quantity/{cartId}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable("cartId") int cartId,
                                                          @RequestParam(value = "quantity") int quantity) {
        CartData cartData = cartservice.updateCartQuantity(cartId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Update quantity call success for Id", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
	@DeleteMapping("/delete_cart/{token}")
    public ResponseEntity<ResponseDTO> deleteCart(@PathVariable("cartId") String token) {
		int cartId= tokenutil.decodeToken(token);
		cartservice.deleteCart(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Delete call success for Id", "Deleted cart id : " + cartId);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
