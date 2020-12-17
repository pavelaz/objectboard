package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SignInServlet", urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        String data_user = "boarduser",
               data_password = "1#Object5Board*%";
        try {
            BussinessUnitDAO sud = new BussinessUnitDAO();
            sud.setDataUser(data_user);
            sud.setDataPassword(data_password);
            ArrayList<BussinessUnitVO> unit;
            unit = sud.getListBussinessUnit("");
            //request.setAttribute("rq_logoName", unit.get(0).getBuLogoName());
            request.setAttribute("rq_unit", unit);
            request.setAttribute("rq_largo", unit.size());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/pages/sign_in.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }
}
