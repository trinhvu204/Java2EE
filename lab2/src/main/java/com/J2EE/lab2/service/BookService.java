package com.J2EE.lab2.service;

import com.J2EE.lab2.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private List<Book> listBook = new ArrayList<>(List.of(
            new Book(1, "Lap trinh java", "NgocVu"),
            new Book(2, "Lap trinh Nodejs", "Kim Nghi"),
            new Book(3, "Lap trinh React", "Minh Vong")
    ));

    public List<Book> getAll() {
        return listBook;
    }

    public Book getBookById(int id) {
        return listBook.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Book updateBook(int id, Book updatedBook) {
        Book book = getBookById(id);
        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
        }
        return book;
    }
    public void addBook(Book book){
        listBook.add(book);
    }
    public void deletedBook(int id) {
        listBook.removeIf(book -> book.getId() == id);
    }
}
