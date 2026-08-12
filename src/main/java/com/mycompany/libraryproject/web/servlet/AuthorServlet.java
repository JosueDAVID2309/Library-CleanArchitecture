package com.mycompany.libraryproject.web.servlet;

import com.mycompany.libraryproject.application.CompositionRoot;
import com.mycompany.libraryproject.application.controller.AuthorController;
import com.mycompany.libraryproject.core.dto.NewAuthorDTO;
import com.mycompany.libraryproject.core.exceptions.AuthorAlreadyRegisteredException;
import com.mycompany.libraryproject.web.response.ApiResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AuthorServlet", urlPatterns = {"/author"})
public class AuthorServlet extends BaseServlet {
    
    private final AuthorController controller = new CompositionRoot().authorController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            NewAuthorDTO dto = readJson(request, NewAuthorDTO.class);
            controller.addAuthor(dto);
            response.setStatus(HttpServletResponse.SC_CREATED);
            writeJson(response, new ApiResponse<>(true, null, "Author has been created"));
        }catch(AuthorAlreadyRegisteredException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJson(response, new ApiResponse<>(false, null, e.getMessage()));
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
