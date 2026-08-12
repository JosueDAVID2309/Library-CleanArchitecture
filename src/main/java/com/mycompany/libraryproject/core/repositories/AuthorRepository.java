package com.mycompany.libraryproject.core.repositories;

import com.mycompany.libraryproject.core.entities.Author;

public interface AuthorRepository {
    
    void registerAuthor(Author author);
    
    boolean existsById(int id);
    
    boolean existsByName(String name);
    
}
