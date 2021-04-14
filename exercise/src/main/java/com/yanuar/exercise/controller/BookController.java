package com.yanuar.exercise.controller;

import com.yanuar.exercise.exception.ResourceNotFoundException;
import com.yanuar.exercise.model.Book;
import com.yanuar.exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public ResponseEntity<Object> getAllBooks(Pageable page) {
        Page<Book> books = bookRepository.findAll(page);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getSingleBook(@PathVariable Long id) {
        Book book_detail = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Book not found")));
        return new ResponseEntity<>(book_detail, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Object> createBooks(@RequestBody Book book) {
        return new ResponseEntity<>(bookRepository.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Object> updateBooks(@PathVariable Long id, @RequestBody Book book) {
        Book book_detail = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Book not found")));
        book_detail.setBookName(book.getBookName());
        book_detail.setBookAuthor(book.getBookAuthor());
        bookRepository.save(book_detail);
        return new ResponseEntity<>(book_detail, HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Object> deleteBooks(@PathVariable Long id) {
        Book book_detail = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(("Book not found")));
        bookRepository.delete(book_detail);
        return new ResponseEntity<>(book_detail, HttpStatus.OK);
    }
}
