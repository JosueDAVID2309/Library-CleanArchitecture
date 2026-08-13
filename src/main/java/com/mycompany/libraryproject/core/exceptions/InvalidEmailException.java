/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryproject.core.exceptions;

/**
 *
 * @author Josue
 */
public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException() {
        super("This email is not valid, please try again....");
    }
    
}
