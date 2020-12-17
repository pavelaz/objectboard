package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.controller.common.FilesController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet (name="PruebaServlet", urlPatterns = "/pruebaServlet")
public class PruebaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (request.getMethod().equals("POST")) {//EL METODO OPCIONS, FUNCIONA PARA ENVIOS DE FORMULARIOS CON ARCHIVOS Y PARA EL ESTADO DE (MODIFICAR).
            /*Start*********************AddPhoto to Object master_user_dto *********/
            FilesController filesController = new FilesController();
            //String photo = filesController.updateFile(request, (1024 * 1024 * 1)); // 1024 * 1024 * 1,= 1 MB
            /*End*********************AddPhoto to Object master_user_dto *********/
        }
        request.getRequestDispatcher("WEB-INF/pages/jsp/master/prueba.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
