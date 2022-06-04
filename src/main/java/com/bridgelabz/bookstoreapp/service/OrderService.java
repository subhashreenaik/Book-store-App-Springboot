package com.bridgelabz.bookstoreapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repo.BookRepo;
import com.bridgelabz.bookstoreapp.repo.OrderRepo;
import com.bridgelabz.bookstoreapp.repo.UserDataRepo;


@Service
public class OrderService {

	@Autowired
	UserDataRepo  userrepo;
	
	@Autowired
	BookRepo bookrepo;
	
	@Autowired
	OrderRepo  orderrepo;
	
	public OrderData createNewOrder(OrderDTO orderDTO) {
		 Optional<UserData> userdata = userrepo.findById(orderDTO.getUserID());
		 Optional<BookData> bookdata = bookrepo.findById(orderDTO.getBookId());
		
		 long totalPrice=orderDTO.getQuantity() * bookdata.get().getPrice();
		 System.out.println("total price"+totalPrice);
		    if (userdata.isPresent() && bookdata.isPresent()) {
		        OrderData order = new OrderData(userdata.get(),bookdata.get(),orderDTO);
		        order.setPrice(totalPrice);

		        OrderData orderData= orderrepo.save(order);
		        return orderData;
		
	}
			return null;
		    
	}

	public Object getAllOrders() {
		
		return orderrepo.findAll();
	}
	
	
}


