package com.vamsi.BookManagementSystem.service;

import com.vamsi.BookManagementSystem.entity.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    public static List<Book> books=new ArrayList<>();

    public BookService(){
        books.add(new Book(101,"The Journey","Thomas",900));
        books.add(new Book(102,"Failure Stories","Nelson",800));
        books.add(new Book(103,"Silence","Bose",1900));
    }

    public void addBook(Book book){
        books.add(book);
    }

    public List<Book> getAllBooks(){
        return books;
    }

    public Book getBook(int id){
        Book book=books.stream()
                .filter(b->b.getId()==id)
                .findFirst()
                .orElse(null);
        return book;
    }

    public boolean updateBook(int id,Book updateBook){
        for(Book book:books){
            if(book.getId()==id){
                book.setAuthor(updateBook.getAuthor());
                book.setTitle(updateBook.getTitle());
                book.setPrice(updateBook.getPrice());
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(int id){
        return books.removeIf(b->b.getId()==id);
    }

    public List<Book> searchBooks(String author){
        return books.stream()
                .filter(b->b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> bookLessThanPrice(double price){
        return books.stream()
                .filter(b->b.getPrice()<price)
                .toList();
    }

    public static void MostExpensiveBook(){
        System.out.println("Total No of Books: "+books.size());
        books.stream()
                .max(Comparator.comparingDouble(Book::getPrice))
                .ifPresentOrElse(
                        book -> System.out.println("Most Expensive Book: "+book),
                        ()-> System.out.println("No Books are Available")
                );
    }
}
