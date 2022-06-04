package com.bridgelabz.bookstoreapp.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.repo.BookRepo;


@Service
public class BookService {

	@Autowired
	BookRepo repo;
	
	public BookData createNewbook(BookDTO bookDTO) {
		BookData book = new BookData(bookDTO);
		return repo.save(book);
	}

	public List<BookData> getbookData() {
		
		return repo.findAll();
	}

	public BookData getBookDataById(int book_Id) {
		
		return repo.findByBookId(book_Id);
				 
	}

	public void deletebookById(int book_Id) {
		repo.deleteById(book_Id);
		
	}

	public Object updateBookById(int book_Id,  BookDTO bookDTO) {
		BookData newbook = this.getBookDataById(book_Id);
		BookData newcontact=new BookData(bookDTO);
		newcontact.setBookId(book_Id);
		BookData newcontact2=repo.save(newcontact);
		return newcontact2;
	}

	public List<BookData> sortbookDataByAsc() {
		
		return repo.sortBooksInAsc();
	}

	public List<BookData> sorBookDataByDesc() {

		return repo.sortBooksInDesc();
	}

	public BookData searchbookByName(String bookName) {
		return repo.findByBookName(bookName);
		
		
	}
	


	
		
		
	}



