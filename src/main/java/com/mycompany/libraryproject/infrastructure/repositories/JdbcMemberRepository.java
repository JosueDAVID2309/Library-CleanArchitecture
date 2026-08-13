package com.mycompany.libraryproject.infrastructure.repositories;

import com.mycompany.libraryproject.core.entities.Member;
import com.mycompany.libraryproject.core.repositories.MemberRepository;
import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcMemberRepository implements MemberRepository {

    private final DBConexion conexion;

    public JdbcMemberRepository(DBConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void addMember(Member member) {
        
        String sql = "INSERT INTO Member(name, email) VALUES (?,?)";
        
        try(Connection con = conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            
            stmt.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT id, name, email FROM Member WHERE email = ?";
        try(Connection con = conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)){
            
            stmt.setString(1, email);
            
            try(ResultSet rs = stmt.executeQuery();){
                return rs.next();
            }
            
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    } 
}
