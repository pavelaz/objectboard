package com.psg.objectboard.controller.insertAdjancentHTML;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="insertNewTr", urlPatterns = "/insertnewtr")
public class insertNewTr extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/jsp/insertAdjacentHTML/insertNewTr.jsp").forward(request, response);
    }
}
