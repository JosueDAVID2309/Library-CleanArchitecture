package integration.web.servlets;

import com.mycompany.libraryproject.infrastructure.configuration.DBConexion;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;


public class MemberApiIT {
    
    DBConexion conexion;
    
    @BeforeEach
    void init(){
        conexion = new DBConexion();
        RestAssured.baseURI = "http://localhost:8080/LibraryProject";
    }
    
    @Test
    void shouldRegisterNewMember() throws SQLException{
        try{
            given()
                .body("""
                      {
                        "name": "RandomName",
                        "email": "random.email@example.com"
                      }
                      """)
                .contentType(ContentType.JSON)
                .when()
                .post("/member")
                .then()
                .statusCode(201)
                .body("success", equalTo(true))
                .body("data", equalTo(null))
                .body("message", equalTo("A new member has been registered..!"));
            
        }finally{
            try(Connection con = conexion.getConnection();
                    PreparedStatement stmt = con.prepareStatement("DELETE FROM Member WHERE name = ?")){
                stmt.setString(1, "RandomName");
                stmt.executeUpdate();
            }
        }
    }
    
    @Test
    void shouldReturn409WhenMemberAlreadyExists(){
        given()
                .body("""
                      {
                        "name": "Robert C. Martin",
                        "email": "robert.martin@example.com"
                      }
                      """)
                .contentType(ContentType.JSON)
                .when()
                .post("/member")
                .then()
                .statusCode(409)
                .body("success", equalTo(false))
                .body("data", equalTo(null))
                .body("message", equalTo("This member has already registered"));
    }
    
    @Test
    void shouldReturn400WhenEmailIsInvalid(){
        given()
                .body("""
                      {
                        "name": "Robert C. Martin",
                        "email": "robertmartinexamplecom"
                      }
                      """)
                .contentType(ContentType.JSON)
                .when()
                .post("/member")
                .then()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("data", equalTo(null))
                .body("message", equalTo("This email is not valid, please try again...."));
    }
    
}
