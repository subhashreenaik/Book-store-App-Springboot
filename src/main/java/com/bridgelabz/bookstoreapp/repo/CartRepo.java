package com.bridgelabz.bookstoreapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.bookstoreapp.model.CartData;

@Repository
public interface CartRepo extends JpaRepository<CartData, Integer> {

}
