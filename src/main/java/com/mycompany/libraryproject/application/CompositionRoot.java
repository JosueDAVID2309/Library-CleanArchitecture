package com.mycompany.libraryproject.application;
import com.mycompany.libraryproject.application.controller.AuthorController;
import com.mycompany.libraryproject.application.controller.BookController;
import com.mycompany.libraryproject.application.controller.MemberController;
import com.mycompany.libraryproject.core.repositories.*;
import com.mycompany.libraryproject.core.usecases.author.*;
import com.mycompany.libraryproject.core.usecases.book.*;
import com.mycompany.libraryproject.core.usecases.member.RegisterNewMemberUseCase;
import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import com.mycompany.libraryproject.infrastructure.repositories.*;
public class CompositionRoot {
    
    //Database Conexion
    private final DBConexion conexion = new DBConexion();
    
    //Repositories
    private final BookRepository bookRepository = new JdbcBookRepository(conexion);
    private final AuthorRepository authorRepository = new JdbcAuthorRepository(conexion);
    private final MemberRepository memberRepository = new JdbcMemberRepository(conexion);
    
    //Use Cases
    private final AddNewBookUseCase addNewBook =
        new AddNewBookUseCase(bookRepository, authorRepository);

    private final ShowAllBooksUseCase showAllBooks =
            new ShowAllBooksUseCase(bookRepository);

    private final ShowBookDetailsUseCase showBookDetails =
            new ShowBookDetailsUseCase(bookRepository);

    private final RegisterNewAuthorUseCase registerNewAuthor =
            new RegisterNewAuthorUseCase(authorRepository);
    
    private final RegisterNewMemberUseCase registerNewMember= new RegisterNewMemberUseCase(memberRepository);
    
    //Controllers
    public BookController bookController() {
        return new BookController(
                addNewBook,
                showAllBooks,
                showBookDetails
        );
    }
    
    public AuthorController authorController(){
        return new AuthorController(
                registerNewAuthor
        );
    }
    
    public MemberController memberController(){
        return new MemberController(
                registerNewMember
        );
    }
    
}
