package com.vamsi.BookManagementSystem.controller;

import com.vamsi.BookManagementSystem.entity.Book;
import com.vamsi.BookManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        bookService.addBook(book);
        return new ResponseEntity<>("Successfully Added!", HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> bookList=bookService.getAllBooks();
        if(bookList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }

    @GetMapping("books/{id}")
    public ResponseEntity<Object> getBook(@PathVariable int id){
        Book b=bookService.getBook(id);
        if(b==null){
            return new ResponseEntity<>("No Book found with id: "+id,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(b,HttpStatus.OK);
    }

    @PutMapping("books/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id,@RequestBody Book updateBook){
        boolean updated=bookService.updateBook(id,updateBook);
        if(updated){
            return new ResponseEntity<>("Successfully Updated!",HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        boolean deleted=bookService.deleteBook(id);
        if(deleted){
            return new ResponseEntity<>("Successfully Deleted! ",HttpStatus.OK);
        }
        return new ResponseEntity<>("No Book Found with id:"+id,HttpStatus.NOT_FOUND);
    }

    @GetMapping("books/search")
    public ResponseEntity<?> searchBooks(@RequestParam("author") String author){
        List<Book> books=bookService.searchBooks(author);
        if(books.isEmpty()){
            return new ResponseEntity<>("No Books found for author: "+author,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("books/filter")
    public ResponseEntity<?> BookLessThanPrice(@RequestParam("price") double price){
        List<Book> books=bookService.bookLessThanPrice(price);
        if(books.isEmpty()){
            return new ResponseEntity<>("No Books found for less than price: "+price,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books,HttpStatus.OK);
    }


}
