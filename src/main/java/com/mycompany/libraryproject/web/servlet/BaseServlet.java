package com.mycompany.libraryproject.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected void writeJson(HttpServletResponse response, Object body) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), body);
    }
    
    protected <T> T readJson(HttpServletRequest request, Class<T> clazz) throws IOException {

        return objectMapper.readValue(request.getInputStream(), clazz);
    }
}