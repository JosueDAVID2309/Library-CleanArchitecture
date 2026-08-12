/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.libraryproject.core.repositories;

import com.mycompany.libraryproject.core.entities.Book;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Josue
 */
public interface BookRepository {
    
    List<Book> getAllBooks();
    
    Optional<Book> findById(int id);
    
    void registerBook(Book book);
    
}
