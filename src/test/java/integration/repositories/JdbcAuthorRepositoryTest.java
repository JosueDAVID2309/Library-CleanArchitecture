package integration.repositories;

import com.mycompany.libraryproject.core.entities.Author;
import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import com.mycompany.libraryproject.infrastructure.repositories.JdbcAuthorRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class JdbcAuthorRepositoryTest {
  
    DBConexion conexion;
    JdbcAuthorRepository repository;
    
    @BeforeEach
    void setUp(){
       conexion = new DBConexion();
       repository = new JdbcAuthorRepository(conexion);
    }
    
    @Test
    void shouldVerifyAuthorExistsById(){
        assertTrue(repository.existsById(1));
    }
    
    @Test
    void shouldReturnFalseWhenAuthorDoesNotExist() {
        assertFalse(repository.existsById(999));
    }
    
    @Test
    void shouldVerifyAuthorExistsByName(){
        assertTrue(repository.existsByName("Robert C. Martin"));
    }
    
    @Test
    void shouldReturnFalseWhenAuthorNameDoesNotExist() {
        assertFalse(repository.existsByName("Autor inexistente"));
    }
    
    @Test
    void shouldRegisterNewAuthor() throws SQLException{
        
        try{
            Author author = new Author();
            author.setName("James F. Kurose");
            repository.registerAuthor(author);
            assertTrue(repository.existsByName("James F. Kurose"));     
        }finally{
            try(Connection con = conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Author WHERE name = ?")){
                
                stmt.setString(1, "James F. Kurose");
                stmt.executeUpdate();
            }
        }
    }
}
