package unit.usecases;

import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.exceptions.BookNotFoundException;
import com.mycompany.libraryproject.core.repositories.BookRepository;
import com.mycompany.libraryproject.core.usecases.book.ShowBookDetailsUseCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.fakes.FakeBookRepository;

public class ShowBookDetailsUseCaseTest {
    BookRepository bookRepository;
    ShowBookDetailsUseCase showBookDetails;
    
    @BeforeEach
    void init(){
        bookRepository = new FakeBookRepository();
        showBookDetails = new ShowBookDetailsUseCase(bookRepository);
    }
    
    @Test
    void shouldReturnBook(){
        Book book = showBookDetails.execute(1);
        
        assertAll(
                () -> assertEquals(book.getId(), 1),
                () -> assertEquals(book.getTitle(), "Clean Architecture"),
                () -> assertEquals(book.getAuthorId(), 1)
        );
    }
    
    @Test
    void shouldThrowExceptionWhenBookNotExists(){
        assertThrows(BookNotFoundException.class, () -> showBookDetails.execute(999));
    }
}
