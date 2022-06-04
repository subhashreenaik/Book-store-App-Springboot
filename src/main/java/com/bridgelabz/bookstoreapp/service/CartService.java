package com.bridgelabz.bookstoreapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.bookstoreapp.dto.CartDTO;
import com.bridgelabz.bookstoreapp.exceptions.BookStoreException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.CartData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repo.CartRepo;

@Service
public class CartService {

	@Autowired
	UserDataService userservice;
	
	@Autowired
	BookService bookservice;
	
	@Autowired
	CartRepo cartrepo;

	public CartData addToCart(CartDTO cartDTO) {
		 UserData userdata = userservice.getUserDataById(cartDTO.getUserID());
	        if (userdata != null) {
	            BookData bookData = bookservice.getBookDataById(cartDTO.getBookId());
	            CartData cartData = new CartData(userdata, bookData, cartDTO.quantity);
	            return cartrepo.save(cartData);
	        }
	        return null;
	}

	public Iterable<CartData> findAllCarts() {
		 return cartrepo.findAll();
    
	}

	public CartData getCartById(int cartId) {
		 return cartrepo.findById(cartId)
				 .orElseThrow(() -> new BookStoreException("Cart with id " + cartId + " not found"));
	 }

	public CartData updateCartQuantity(int cartId, int quantity) {
		CartData cartData = this.getCartById(cartId);
		
        cartData.setQuantity(quantity);
        return cartrepo.save(cartData);
    }

	public void deleteCart(int cartId) {
		CartData cartData = this.getCartById(cartId);
		cartrepo.delete(cartData);
    }

}
