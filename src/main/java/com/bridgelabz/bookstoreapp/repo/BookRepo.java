package com.bridgelabz.bookstoreapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.model.BookData;

@Repository
public interface BookRepo extends JpaRepository<BookData, Integer> {

	BookData findByBookId(int book_Id);

	@Query(value = "select * from book_table order by book_name ASC  ", nativeQuery = true)
	List<BookData> sortBooksInAsc();

	@Query(value = "select * from book_table order by book_name DSC  ", nativeQuery = true)
	List<BookData> sortBooksInDesc();

	
	@Query(value = "select * from book_table where book_name=:bookName  ", nativeQuery = true)
	BookData findByBookName(String bookName);

}
