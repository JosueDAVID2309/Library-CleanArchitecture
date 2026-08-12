/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.usecases;

import com.mycompany.libraryproject.core.dto.NewAuthorDTO;
import com.mycompany.libraryproject.core.exceptions.AuthorAlreadyRegisteredException;
import com.mycompany.libraryproject.core.repositories.AuthorRepository;
import com.mycompany.libraryproject.core.usecases.author.RegisterNewAuthorUseCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unit.fakes.FakeAuthorRepository;

/**
 *
 * @author Josue
 */
public class RegisterNewAuthorUseCaseTest {
    AuthorRepository authorRepository;
    RegisterNewAuthorUseCase registerNewAuthor;
    
    @BeforeEach
    void setUp(){
        authorRepository = new FakeAuthorRepository();
        registerNewAuthor = new RegisterNewAuthorUseCase(authorRepository);
    }
    
    @Test
    void shouldRegisterNewAuthor(){

        NewAuthorDTO author = new NewAuthorDTO();
        author.setName("New Author");
       
        registerNewAuthor.execute(author);
        
        assertTrue(authorRepository.existsByName("New Author"));
    }
    
    @Test
    void shouldThrowExceptionWhenAuthorExists(){
        NewAuthorDTO author = new NewAuthorDTO();
        author.setName("Robert C. Martin");
        
        assertThrows(AuthorAlreadyRegisteredException.class, () -> registerNewAuthor.execute(author));
    }
}
