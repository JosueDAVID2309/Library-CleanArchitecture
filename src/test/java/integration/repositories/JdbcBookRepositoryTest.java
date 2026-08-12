
package integration.repositories;

import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import com.mycompany.libraryproject.infrastructure.repositories.JdbcBookRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JdbcBookRepositoryTest {
    JdbcBookRepository repository;
    DBConexion con;
    
    @BeforeEach
    void setUp(){
        con = new DBConexion();
        repository = new JdbcBookRepository(con);
    }
    
    @Test
    void shouldReturnAllBooks(){
        List<Book> books = repository.getAllBooks();
        
        assertFalse(books.isEmpty());
        assertEquals(books.size(), 5);
        
        Book book = books.get(0);
        
        assertEquals(book.getId(), 1);
        assertEquals(book.getTitle(), "Clean Architecture" );
        assertEquals(book.getAuthorId(), 1);
    }
    
    @Test
    void shouldReturnBook(){
        Optional<Book> result = repository.findById(1);
        
        assertTrue(result.isPresent());
        
        Book nbook = result.get();
        
        assertEquals(nbook.getId(), 1);
        assertEquals(nbook.getTitle(), "Clean Architecture" );
        assertEquals(nbook.getAuthorId(), 1);
    }
    
    @Test
    void shouldRegisterNewBook() throws SQLException {
        try {
            Book book = new Book();
            book.setTitle("The Clean Coder");
            book.setAuthorId(1);

            repository.registerBook(book);

            List<Book> books = repository.getAllBooks();

            assertEquals(6, books.size());
        } finally {

            try (Connection conn = con.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM Book WHERE title = ?")) {
                stmt.setString(1, "The Clean Coder");
                stmt.executeUpdate();
            }
        }
    }
}
