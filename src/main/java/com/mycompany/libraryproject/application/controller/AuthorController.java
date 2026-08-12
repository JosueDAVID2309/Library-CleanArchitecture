/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryproject.application.controller;

import com.mycompany.libraryproject.core.dto.NewAuthorDTO;
import com.mycompany.libraryproject.core.usecases.author.RegisterNewAuthorUseCase;

/**
 *
 * @author Josue
 */
public class AuthorController {
    private final RegisterNewAuthorUseCase registerNewAuthor;
   
    public AuthorController(RegisterNewAuthorUseCase registerNewAuthor){
        this.registerNewAuthor = registerNewAuthor;
    }
    
    public void addAuthor(NewAuthorDTO newAuthor){
        registerNewAuthor.execute(newAuthor);
    }
}
