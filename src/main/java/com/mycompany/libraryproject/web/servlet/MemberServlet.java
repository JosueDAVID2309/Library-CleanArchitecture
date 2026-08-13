/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.libraryproject.web.servlet;

import com.mycompany.libraryproject.application.CompositionRoot;
import com.mycompany.libraryproject.application.controller.MemberController;
import com.mycompany.libraryproject.core.dto.NewMemberDTO;
import com.mycompany.libraryproject.core.exceptions.InvalidEmailException;
import com.mycompany.libraryproject.core.exceptions.MemberAlreadyRegisteredException;
import com.mycompany.libraryproject.web.response.ApiResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns={"/member"})
public class MemberServlet extends BaseServlet{
    
    private final MemberController controller = new CompositionRoot().memberController();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            NewMemberDTO newMember = readJson(request, NewMemberDTO.class); 
            controller.addMember(newMember);
            response.setStatus(HttpServletResponse.SC_CREATED);
            writeJson(response, new ApiResponse<>(true, null, "A new member has been registered..!"));
            
        }catch(MemberAlreadyRegisteredException ex){
            
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            writeJson(response, new ApiResponse<>(false, null, ex.getMessage()));
            
        }catch(InvalidEmailException ex){
            
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writeJson(response, new ApiResponse<>(false, null, ex.getMessage()));
            
        }catch(Exception e){
            
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writeJson(response,new ApiResponse<>(false, null, "Server Internal Error"));
        }
    }
}
