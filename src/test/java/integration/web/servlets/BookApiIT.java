/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

public class BookApiIT {

    DBConexion conexion;
    
    @BeforeEach
    void init() {
        RestAssured.baseURI = "http://localhost:8080/LibraryProject";
        conexion = new DBConexion();
    }

    @Test
    void shouldReturnListBooks() {
        given()
        .when()
            .get("/book")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("success", equalTo(true))
            .body("data", notNullValue())
            .body("data.size()", greaterThan(0))
            .body("message", equalTo("Books returned successfully."));
    }

    @Test
    void shouldReturnBook() {
        given()
        .when()
            .get("/book/1")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("success", equalTo(true))
            .body("message", equalTo("Book returned successfully."))
            .rootPath("data")
            .body("id", equalTo(1))
            .body("title", equalTo("Clean Architecture"))
            .body("authorId", equalTo(1));
    }

    @Test
    void shouldReturn404WhenBookDoesNotExist() {
        given()
        .when()
            .get("/book/9999")
        .then()
            .statusCode(404)
            .contentType(ContentType.JSON)
            .body("success", equalTo(false))
            .body("data", nullValue());
    }
    
    @Test
    void shouldRegisterNewBook() throws SQLException{  
        
        try{
            given()
                    .body("""
                        {
                            "title": "La Odisea",
                            "authorId": 26
                        }
                    """)
                    .contentType(ContentType.JSON)
                    .when()
                    .post("/book")
                    .then()
                    .statusCode(201)
                    .contentType(ContentType.JSON)
                    .body("success", equalTo(true))
                    .body("data", nullValue())
                    .body("message", equalTo("A new Book has been registered successfully."));
        }finally{
            try(Connection con = conexion.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Book WHERE title = ?")){
                
                stmt.setString(1, "La Odisea");
                stmt.executeUpdate();
            }
        }
    }
    
    @Test
    void shouldReturn400WhenAuthorDontExists(){
        given()
                .body("""
                      {
                        "title": "La Odisea",
                        "authorId": 999
                      }
                      """)
                .contentType(ContentType.JSON)
                .when()
                .post("/book")
                .then()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("data", nullValue())
                .body("message", equalTo("Unregistered Author on database..."));
    }
}
