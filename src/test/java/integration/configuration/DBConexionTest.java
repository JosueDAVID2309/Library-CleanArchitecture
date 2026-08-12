package integration.configuration;

import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBConexionTest {
    
    @Test
    void shouldConnectToDB() throws SQLException{
        DBConexion db = new DBConexion();
        
        try (Connection con = db.getConnection()) {
            assertNotNull(con);
            assertFalse(con.isClosed());
        }
    }
}
