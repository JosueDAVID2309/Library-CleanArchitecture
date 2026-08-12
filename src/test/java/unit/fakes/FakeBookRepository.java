/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.fakes;

import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.repositories.BookRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Josue
 */
public class FakeBookRepository implements BookRepository {
    
    private final Map<Integer, Book> books = new HashMap<>();

    public FakeBookRepository() {
        books.put(1, new Book(1, "Clean Architecture", 1));
        books.put(2, new Book(2, "Clean Code", 1));
        books.put(3, new Book(3, "Design Patterns", 2));
        books.put(4, new Book(4, "Refactoring", 3));
        books.put(5, new Book(5, "Domain-Driven Design", 4));
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    @Override
    public Optional<Book> findById(int id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public void registerBook(Book book) {
        int id =  this.books.size() + 1;
        
        book.setId(id);
        this.books.put(id, book); 
    }

}
