/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.libraryproject.core.repositories;

/**
 *
 * @author Josue
 */
public interface BookStockRepository {
    
    void addStock(int bookId, int amount);

    void decreaseStock(int bookId);

    void increaseStock(int bookId);

    int getAmount(int bookId);
}
