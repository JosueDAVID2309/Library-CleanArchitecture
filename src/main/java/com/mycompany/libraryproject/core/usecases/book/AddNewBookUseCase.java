package com.mycompany.libraryproject.core.usecases.book;

import com.mycompany.libraryproject.core.dto.NewBookDTO;
import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.exceptions.AuthorNotRegisteredException;
import com.mycompany.libraryproject.core.repositories.AuthorRepository;
import com.mycompany.libraryproject.core.repositories.BookRepository;

public class AddNewBookUseCase {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    
    public AddNewBookUseCase(BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }
    
    public void execute(NewBookDTO newBook){
        
        if(!isAuthorRegistered(newBook.getAuthorId())){
            throw new AuthorNotRegisteredException();
        }
        
        Book book = new Book();
        book.setTitle(newBook.getTitle());
        book.setAuthorId(newBook.getAuthorId());
        
        bookRepository.registerBook(book);
    }
    
    private boolean isAuthorRegistered(int idAuthor){
        return authorRepository.existsById(idAuthor);
    }
    
}
