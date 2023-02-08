package testproject.testprojectbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.server.ResponseStatusException;
import testproject.testprojectbackend.model.Book;
import testproject.testprojectbackend.repository.BookRepository;
import testproject.testprojectbackend.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {


    @Autowired
    private BookService bookService;



    @GetMapping("/books/all")
    public ResponseEntity<?> getAllBooks() {
        if (bookService.getAllBooks().isEmpty() == false){
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No books stored", HttpStatus.OK);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Optional<Book> resultBook = bookService.getBookByID(id);
        if (resultBook.isPresent())
            return new ResponseEntity<>(resultBook, HttpStatus.OK);
        else {
            return new ResponseEntity<>("No book found!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create_new_book")
    public String createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/update_book/{id}")
    public String updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/delete_book/{id}")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }
}
