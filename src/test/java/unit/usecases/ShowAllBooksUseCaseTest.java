/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.usecases;

import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.repositories.BookRepository;
import com.mycompany.libraryproject.core.usecases.book.ShowAllBooksUseCase;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import unit.fakes.FakeBookRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Josue
 */
public class ShowAllBooksUseCaseTest {
    BookRepository bookRepository;
    ShowAllBooksUseCase showAllBooks;
    
    @BeforeEach
    void init(){
        bookRepository = new FakeBookRepository();
        showAllBooks = new ShowAllBooksUseCase(bookRepository);
    }
    
    @Test
    void shouldReturnAllRegisteredBooks() {

        List<Book> books = showAllBooks.execute();

        assertAll(
            () -> assertEquals(5, books.size()),
            () -> assertEquals("Clean Architecture", books.get(0).getTitle()),
            () -> assertEquals("Domain-Driven Design", books.get(4).getTitle())
        );
    }
}
