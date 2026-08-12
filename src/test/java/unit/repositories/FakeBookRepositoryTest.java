package unit.repositories;

import com.mycompany.libraryproject.core.entities.Book;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.fakes.FakeBookRepository;

/**
 *
 * @author Josue
 */
public class FakeBookRepositoryTest {
    private FakeBookRepository repository;

    @BeforeEach
    void setUp() {
        repository = new FakeBookRepository();
    }

    @Test
    void shouldReturnAllBooks() {

        List<Book> books = repository.getAllBooks();

        assertEquals(5, books.size());
        assertEquals("Clean Architecture", books.get(0).getTitle());
    }

    @Test
    void shouldReturnBookWhenIdExists() {

        Optional<Book> book = repository.findById(1);

        assertTrue(book.isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenBookDoesNotExist() {

        Optional<Book> book = repository.findById(100);
        assertFalse(book.isPresent());
    }
    
    @Test
    void shouldRegisterNewBook(){
        
        Book newBook = new Book();
        newBook.setTitle("The Clean Coder");
        newBook.setAuthorId(1);
        repository.registerBook(newBook);
        
        Optional<Book> bookAdded = repository.findById(6);
        
        assertTrue(bookAdded.isPresent());
        
        Book book = bookAdded.get();
        
        assertEquals(repository.getAllBooks().size(), 6);
        assertEquals(book.getId(), 6);
        assertEquals(book.getTitle(), "The Clean Coder");
        assertEquals(book.getAuthorId(), 1);
    }
}