package com.mycompany.libraryproject.core.usecases.book;

import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.repositories.BookRepository;
import java.util.List;

/**
 *
 * @author Josue
 */
public class ShowAllBooksUseCase {

    private final BookRepository repository;

    public ShowAllBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> execute() {
        return repository.getAllBooks();
    }
}
