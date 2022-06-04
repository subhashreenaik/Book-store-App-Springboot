package com.bridgelabz.bookstoreapp.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.BookDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.BookData;
import com.bridgelabz.bookstoreapp.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService  bookservice;
	
	@PostMapping("/create")
    public ResponseEntity<ResponseDTO> createNewbook(@RequestBody BookDTO bookDTO){
		BookData newbook = bookservice.createNewbook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", newbook);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }
	
	@GetMapping(value= {"/getAll"})
	public ResponseEntity<ResponseDTO> getbookData(){
	    List<BookData> bookList =bookservice.getbookData();
	    ResponseDTO responseDTO = new ResponseDTO("Get Call Success",bookList);
	    return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}

	@GetMapping("/get/{book_Id}")
	public ResponseEntity<ResponseDTO> getBookDataById(@PathVariable("book_Id") int book_Id){
		BookData bookData=bookservice.getBookDataById(book_Id);
	    ResponseDTO responseDTO = new ResponseDTO("Get Call Success for id:", bookData);
	    return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{book_Id}")
	public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable("book_Id") int book_Id){
		bookservice.deletebookById(book_Id);
	    ResponseDTO responseDTO = new ResponseDTO("selected id is deleted:", "book id is"+book_Id);
	    return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
	}
	
	@PutMapping("/update/{book_Id}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable int book_Id,@RequestBody BookDTO bookDTO){
        ResponseDTO responseDTO = new ResponseDTO("Get Call Success", bookservice.updateBookById(book_Id,bookDTO));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
	
	@GetMapping("/sortedbook")
    public ResponseEntity<ResponseDTO> sorBookDataByAsc() {

        List<BookData> booklist =  bookservice.sortbookDataByAsc();
        ResponseDTO response = new ResponseDTO("Get Call for sortedcity Successful", booklist);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
	@GetMapping("/sortedbookDesc")
    public ResponseEntity<ResponseDTO> sorBookDataByDesc() {

        List<BookData> booklist =  bookservice.sorBookDataByDesc();
        ResponseDTO response = new ResponseDTO("Get Call for sortedcity Successful", booklist);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
	
	@GetMapping("/search/{bookName}")
	public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable("bookName") String bookName){
		BookData bookname= bookservice.searchbookByName(bookName);
		ResponseDTO response = new ResponseDTO("selected book is found Successful", bookname);
	    return new ResponseEntity<ResponseDTO>(response,HttpStatus.OK);
	}
	
}
