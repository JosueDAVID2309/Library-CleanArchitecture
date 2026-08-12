package integration.web.servlets;

import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorServletTest {
    DBConexion conexion;
    
    @BeforeEach
    void init(){
        conexion = new DBConexion();
        RestAssured.baseURI = "http://localhost:8080/LibraryProject";
    }
    
    @Test
    void shouldRegisterNewAuthor() throws SQLException{
        try{
            given().
                body("""
                     {
                        "name": "Robert Kiyosaki"
                     }
                     """)
                .contentType(ContentType.JSON)
                .when()
                .post("/author")
                .then()
                .statusCode(201)
                .body("success", equalTo(true))
                .body("data", nullValue())
                .body("message", equalTo("Author has been created"));
        }finally{
            try(Connection con = conexion.getConnection();
                    PreparedStatement stmt = con.prepareStatement("DELETE FROM Author WHERE name = ?")){
                stmt.setString(1, "Robert Kiyosaki");
                stmt.executeUpdate();
                
            }
        }
    }
    
    @Test 
    void shouldReturn400WhenAuthorNameAlreadyExists(){
        given().
                body("""
                     {
                        "name": "Robert C. Martin"
                     }
                     """)
                .contentType(ContentType.JSON)
                .when()
                .post("/author")
                .then()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("data", nullValue())
                .body("message", equalTo("That Author has already registered..."));
    }
}
