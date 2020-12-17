package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classViewDAO.StateCountryDAO;
import com.psg.objectboard.model.own.ownsEntity.classViewVO.StateCountryVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "StatesServlet", urlPatterns = "/states")
public class StatesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

        StateCountryDAO cod= new StateCountryDAO();
        ArrayList<StateCountryVO> states = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        int num_filas = 0;

        states= cod.getListStateCountry();

        num_filas = states.size();

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", num_filas);
        request.setAttribute("rq_states", states);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/states.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
