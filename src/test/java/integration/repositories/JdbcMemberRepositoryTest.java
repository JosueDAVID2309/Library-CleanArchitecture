/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integration.repositories;

import com.mycompany.libraryproject.core.entities.Member;
import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import com.mycompany.libraryproject.infrastructure.repositories.JdbcMemberRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Josue
 */
public class JdbcMemberRepositoryTest {

    private DBConexion conexion;
    private JdbcMemberRepository repository;

    @BeforeEach
    void setUp() {
        conexion = new DBConexion();
        repository = new JdbcMemberRepository(conexion);
    }

    @Test
    void shouldVerifyMemberExistsByEmail() {

        assertTrue(
            repository.existsByEmail("robert.martin@example.com")
        );
    }

    @Test
    void shouldReturnFalseWhenMemberDoesNotExistByEmail() {

        assertFalse(
            repository.existsByEmail("unknown@example.com")
        );
    }

    @Test
    void shouldAddNewMember() throws SQLException {

        String email = "new.member@example.com";

        try {
            Member member = new Member();
            member.setName("New Member");
            member.setEmail(email);

            repository.addMember(member);

            assertTrue(repository.existsByEmail(email));

        } finally {
            try (Connection con = conexion.getConnection();
                 PreparedStatement stmt = con.prepareStatement(
                     "DELETE FROM Member WHERE email = ?"
                 )) {

                stmt.setString(1, email);
                stmt.executeUpdate();
            }
        }
    }
}
