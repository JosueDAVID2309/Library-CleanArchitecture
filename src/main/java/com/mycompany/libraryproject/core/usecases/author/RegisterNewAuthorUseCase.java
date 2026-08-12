
package com.mycompany.libraryproject.core.usecases.author;

import com.mycompany.libraryproject.core.dto.NewAuthorDTO;
import com.mycompany.libraryproject.core.entities.Author;
import com.mycompany.libraryproject.core.exceptions.AuthorAlreadyRegisteredException;
import com.mycompany.libraryproject.core.repositories.AuthorRepository;

public class RegisterNewAuthorUseCase {
    private final AuthorRepository authorRepository;
    
    public RegisterNewAuthorUseCase(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    
    public void execute(NewAuthorDTO newAuthor){
        if(isAuthorRegistered(newAuthor.getName())){
            throw new AuthorAlreadyRegisteredException();
        }
        Author author = new Author();
        author.setName(newAuthor.getName());
        
        authorRepository.registerAuthor(author);
    }
    
    private boolean isAuthorRegistered(String name){
        return authorRepository.existsByName(name);
    }
    
}
