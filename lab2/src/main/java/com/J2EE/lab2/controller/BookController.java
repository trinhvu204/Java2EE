package com.J2EE.lab2.controller;

import com.J2EE.lab2.model.Book;
import com.J2EE.lab2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public String addBook(@RequestBody Book book){
        bookService.addBook(book);
        return "book add successful";
    }

    @PutMapping("/{id}")
    public String updateBook (@PathVariable int id,@RequestBody Book updateBook){
        bookService.updateBook(id, updateBook);
        return "Update thanh cong";
    }

    @DeleteMapping("/{id}")
    public String deletedBook(@PathVariable int id ){
        bookService.deletedBook(id);
        return "delet thanh cong";
    }
}
