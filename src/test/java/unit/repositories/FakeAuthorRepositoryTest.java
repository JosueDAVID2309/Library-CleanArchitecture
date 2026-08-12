/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.repositories;

import com.mycompany.libraryproject.core.entities.Author;
import org.junit.jupiter.api.*;
import unit.fakes.FakeAuthorRepository;

/**
 *
 * @author Josue
 */
public class FakeAuthorRepositoryTest {
    private FakeAuthorRepository repository;
    
    @BeforeEach
    void setUp(){
        repository = new FakeAuthorRepository();
    }
    
    @Test
    void shouldRegisterAuthor(){
        Author newAuthor = new Author();
        newAuthor.setName("Homero");
        
        repository.registerAuthor(newAuthor);
        
        
    }
}
