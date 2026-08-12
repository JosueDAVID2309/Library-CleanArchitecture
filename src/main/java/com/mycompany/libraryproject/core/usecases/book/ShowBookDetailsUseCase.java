/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryproject.core.usecases.book;

import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.exceptions.BookNotFoundException;
import com.mycompany.libraryproject.core.repositories.BookRepository;

/**
 *
 * @author Josue
 */
public class ShowBookDetailsUseCase {
    
    private final BookRepository repo;
    
    public ShowBookDetailsUseCase(BookRepository repo){
        this.repo = repo;
    }
    
    public Book execute(int id){
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException());
    }
}
