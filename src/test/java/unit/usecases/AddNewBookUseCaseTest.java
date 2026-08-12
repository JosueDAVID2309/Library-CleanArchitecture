/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.usecases;

import com.mycompany.libraryproject.core.dto.NewBookDTO;
import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.exceptions.AuthorNotRegisteredException;
import com.mycompany.libraryproject.core.repositories.AuthorRepository;
import com.mycompany.libraryproject.core.repositories.BookRepository;
import com.mycompany.libraryproject.core.usecases.book.AddNewBookUseCase;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.fakes.FakeAuthorRepository;
import unit.fakes.FakeBookRepository;

public class AddNewBookUseCaseTest {
    
    BookRepository bookRepository;
    AuthorRepository authorRepository;
    AddNewBookUseCase addNewBook;
    
    @BeforeEach
    void init(){
        bookRepository = new FakeBookRepository();
        authorRepository = new FakeAuthorRepository();
        addNewBook = new AddNewBookUseCase(bookRepository, authorRepository);
    }
        
    @Test
    void shouldAddNewBook(){
        NewBookDTO newBook = new NewBookDTO();
        newBook.setTitle("The Clean Coder");
        newBook.setAuthorId(1);

        addNewBook.execute(newBook);

        assertEquals(bookRepository.getAllBooks().size(), 6);
        Optional<Book> result = bookRepository.findById(6);
        Book book  = result.get();

        assertEquals(book.getId(), 6);
        assertEquals(book.getTitle(), "The Clean Coder");
        assertEquals(book.getAuthorId(), 1);
    }

    @Test 
    void shouldThrowExceptionWhenAuthorNoExists(){
        NewBookDTO newBook = new NewBookDTO();
        
        newBook.setTitle("The Clean Coder");
        newBook.setAuthorId(999);

        assertThrows(AuthorNotRegisteredException.class, () -> addNewBook.execute(newBook));
        }
}
