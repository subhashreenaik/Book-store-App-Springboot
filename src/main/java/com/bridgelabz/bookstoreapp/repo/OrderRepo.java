package com.bridgelabz.bookstoreapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.bookstoreapp.model.OrderData;

public interface OrderRepo extends JpaRepository<OrderData, Integer>  {

}
