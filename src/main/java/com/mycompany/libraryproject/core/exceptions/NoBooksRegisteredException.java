/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryproject.core.exceptions;

/**
 *
 * @author Josue
 */
public class NoBooksRegisteredException extends RuntimeException{
    public NoBooksRegisteredException(){
        super("There is no books registered...");
    }
}
