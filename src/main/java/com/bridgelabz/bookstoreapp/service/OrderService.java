package com.bridgelabz.bookstoreapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.OrderDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.exceptions.UserRegistrationException;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.model.Email;
import com.bridgelabz.bookstoreapp.model.OrderData;
import com.bridgelabz.bookstoreapp.model.UserData;
import com.bridgelabz.bookstoreapp.repo.BookRepo;
import com.bridgelabz.bookstoreapp.repo.OrderRepo;
import com.bridgelabz.bookstoreapp.repo.UserDataRepo;
import com.bridgelabz.bookstoreapp.util.TokenUtil;


@Service
public class OrderService {

	@Autowired
	UserDataRepo  userrepo;
	
	@Autowired
	BookRepo bookrepo;
	
	@Autowired
	OrderRepo  orderrepo;
	
	@Autowired
	TokenUtil tokenUtil;
	
	@Autowired
	EmailService emailservice;
	
	public ResponseEntity<ResponseDTO> createNewOrder(OrderDTO orderDTO) {
		 Optional<UserData> userdata = userrepo.findById(orderDTO.getUserID());
		 Optional<BookData> bookdata = bookrepo.findById(orderDTO.getBookId());
		
		 long totalPrice=orderDTO.getQuantity() * bookdata.get().getPrice();
		 System.out.println("total price"+totalPrice);
		    if (userdata.isPresent() && bookdata.isPresent()) {
		        OrderData order = new OrderData(userdata.get(),bookdata.get(),orderDTO);
		        order.setPrice(totalPrice);

		        OrderData orderData= orderrepo.save(order);
		        
		        String token = tokenUtil.createToken(orderData.getOrderID());
		        Email email = new Email(userdata.get().getEmail(),"Order placed Successfully",userdata.get().getFirstName() + "=>" + emailservice.getLink(token));
		        emailservice.sendMail(email);
		        ResponseDTO responseDTO = new ResponseDTO("Order Placed", userdata.get().getUserID(),token);

		        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
		    }
		    return null;
		    }

	public Object getAllOrders() {
		
		return orderrepo.findAll();
	}

	public Optional<OrderData> getOrderDataById(int order_Id) {
		
		return orderrepo.findById(order_Id);
	}

	

	public ResponseEntity<ResponseDTO> cancelOrder(int orderId, int userId) throws UserRegistrationException  {
		Optional<OrderData> order = orderrepo.findById(orderId);
        Optional<UserData> userData=userrepo.findById(userId);
        System.out.println("user Data"+userData);
        String token=tokenUtil.createToken(orderId);
        if (order.isPresent()) {
            order.get().setCancel(true);
            orderrepo.save(order.get());
            System.out.println("token"+token);
            Email email = new Email(userData.get().getEmail(),"Order Canceled Successfully",userData.get().getFirstName() + "=>" +"Your Order has been Cancelled");
            emailservice.sendMail(email);

            ResponseDTO responseDto = new ResponseDTO("Order Canceled", userData.get().getUserID(),token);

            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }
        else{
            throw new UserRegistrationException("data not found");
        }
}
	}
	
	
	
	



