/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit.fakes;

import com.mycompany.libraryproject.core.entities.Author;
import com.mycompany.libraryproject.core.repositories.AuthorRepository;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Josue
 */
public class FakeAuthorRepository implements AuthorRepository {
    
    private final Map<Integer, Author> authors = new HashMap<>();
    
    public FakeAuthorRepository(){
        authors.put(1, new Author(1, "Robert C. Martin"));
        authors.put(2, new Author(2, "Erich Gamma"));
        authors.put(3, new Author(3, "Martin Fowler"));
        authors.put(4, new Author(4, "Eric Evans"));
    }

    @Override
    public void registerAuthor(Author author) {
        int id = this.authors.size()+1;
        
        author.setId(id);
        this.authors.put(id, author);
    }

    @Override
    public boolean existsById(int id) {
        for(Author author: this.authors.values()){
            if(author.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByName(String name) {
        for(Author author: this.authors.values()){
            if(author.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
}
