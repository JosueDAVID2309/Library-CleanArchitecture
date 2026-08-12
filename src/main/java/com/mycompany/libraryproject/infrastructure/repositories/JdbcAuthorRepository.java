
package com.mycompany.libraryproject.infrastructure.repositories;

import com.mycompany.libraryproject.core.entities.Author;
import com.mycompany.libraryproject.core.repositories.AuthorRepository;
import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAuthorRepository implements AuthorRepository{
    
    private final DBConexion conexion;
    
    public JdbcAuthorRepository(DBConexion conexion){
        this.conexion = conexion;
    }

    @Override
    public void registerAuthor(Author author) {
        String sql = "INSERT INTO Author (name) VALUES (?)";
        try(Connection con = conexion.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);){
            
            stmt.setString(1, author.getName());
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public boolean existsById(int id) {

        String sql = "SELECT 1 FROM Author WHERE id = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT 1 FROM Author WHERE name = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
