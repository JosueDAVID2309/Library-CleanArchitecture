package com.mycompany.libraryproject.web.servlet;

import com.mycompany.libraryproject.application.controller.BookController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.libraryproject.application.CompositionRoot;
import com.mycompany.libraryproject.core.entities.Book;
import com.mycompany.libraryproject.web.response.ApiResponse;
import com.mycompany.libraryproject.core.dto.NewBookDTO;
import com.mycompany.libraryproject.core.exceptions.AuthorNotRegisteredException;
import com.mycompany.libraryproject.core.exceptions.BookNotFoundException;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = {"/book/*"})
public class BookServlet extends BaseServlet {
    
    private final BookController controller = new CompositionRoot().bookController();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            String bookIdParam = request.getPathInfo();
            if (bookIdParam == null) {

                ApiResponse<List<Book>> apiResponse = new ApiResponse<>(true, controller.showBooks(), "Books returned successfully.");
                response.setStatus(HttpServletResponse.SC_OK);
                writeJson(response, apiResponse);
                return;
            }

            int bookId = Integer.parseInt(bookIdParam.substring(1));
            
            ApiResponse<Book> apiResponse = new ApiResponse<>(true, controller.showBook(bookId), "Book returned successfully.");
            
            response.setStatus(HttpServletResponse.SC_OK);
            
            writeJson(response, apiResponse);

        } catch (BookNotFoundException e) {

            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            writeJson(response, new ApiResponse<>(false, null, e.getMessage()));

        } catch (NumberFormatException e) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJson(response, new ApiResponse<>(false, null, "Parameter 'bookId' must be a valid integer."));

        }catch(Exception e){
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeJson(response,new ApiResponse<>(false, null, "Server Internal Error"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            NewBookDTO dto = readJson(request, NewBookDTO.class);
            controller.addBook(dto);
            response.setStatus(HttpServletResponse.SC_CREATED);
            writeJson(response, new ApiResponse<>(true, null, "A new Book has been registered successfully."));
            
        }catch(AuthorNotRegisteredException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJson(response, new ApiResponse<>(false, null, e.getMessage()));
        }catch(Exception e){
            
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeJson(response,new ApiResponse<>(false, null, "Server Internal Error"));
        }
    }

    @Override
    public String getServletInfo() {
        return "This is my BookServlet.";
    }

}
