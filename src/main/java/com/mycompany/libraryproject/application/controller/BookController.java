
package com.mycompany.libraryproject.application.controller;

import com.mycompany.libraryproject.core.dto.NewBookDTO;
import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.usecases.book.AddNewBookUseCase;
import com.mycompany.libraryproject.core.usecases.book.ShowAllBooksUseCase;
import com.mycompany.libraryproject.core.usecases.book.ShowBookDetailsUseCase;
import java.util.List;

public class BookController {
    private final AddNewBookUseCase addNewBook;
    private final ShowAllBooksUseCase showAllBooks;
    private final ShowBookDetailsUseCase showBookDetails;
    
    public BookController(
            AddNewBookUseCase addNewBook,
            ShowAllBooksUseCase showAllBooks,
            ShowBookDetailsUseCase showBookDetails){
        this.addNewBook = addNewBook;
        this.showAllBooks = showAllBooks;
        this.showBookDetails = showBookDetails;
    }
    
    public void addBook(NewBookDTO newBook){
        addNewBook.execute(newBook);
    };
    
    public List<Book> showBooks (){
        return showAllBooks.execute();
    }
    
    public Book showBook(int bookId){
        return showBookDetails.execute(bookId);
    }
}
