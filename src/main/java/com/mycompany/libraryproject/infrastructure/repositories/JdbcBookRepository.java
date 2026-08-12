
package com.mycompany.libraryproject.infrastructure.repositories;

import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.core.repositories.BookRepository;
import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JdbcBookRepository implements BookRepository {

    private final DBConexion conexion;
    
    public JdbcBookRepository(DBConexion conexion){
        this.conexion = conexion;
    }
    
    @Override
    public List<Book> getAllBooks() {
        
        String sql = "SELECT * FROM Book";
        try(Connection con = conexion.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            try(ResultSet rs = stmt.executeQuery()){
                List<Book> books = new ArrayList<>();
                while(rs.next()){
                    Book book = new Book();
                    
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthorId(rs.getInt("authorId"));
                    
                    books.add(book);
                }
                return books;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Optional<Book> findById(int id) {
        
        String sql = "SELECT * FROM Book WHERE id = ? ";
        
        try(Connection con = conexion.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthorId(rs.getInt("authorId"));
                    return Optional.of(book);
                }
            }
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        return Optional.empty();
    }

    @Override
    public void registerBook(Book book) {
        
        String sql = "INSERT INTO Book(title, authorId) VALUES (?,?)";
        
        try(Connection con = conexion.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthorId());
            
            stmt.executeUpdate();
            
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }        
    }
    
}
