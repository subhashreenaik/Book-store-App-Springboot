package com.bridgelabz.bookstoreapp.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.model.UserData;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, Integer>{
//	 @Query(value = " select  * from registration_table where id=?1", nativeQuery = true)
//     UserData getById(String id);
	 
//	 @Query(value = "select * from registration_table where email=?1 password = ?2", nativeQuery = true)
//     Long findByPassword(String email, String password);
//	 
	 Optional<UserData> findByEmail(String email);
     Optional<UserData> getUserByEmail(String email);

}
