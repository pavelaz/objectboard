package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classViewDAO.BussinessUnitCoStCiDAO;
import com.psg.objectboard.model.own.ownsEntity.classViewVO.BussinessUnitCoStCiVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "BussinesUnitsServlet", urlPatterns = "/bussinessunits")
public class BussinesUnitsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

        BussinessUnitCoStCiDAO cod= new BussinessUnitCoStCiDAO();
        ArrayList<BussinessUnitCoStCiVO> unidad = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);
        unidad = cod.getListBussinessUnitCoStCi();

        Integer num_filas = unidad.size();

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", num_filas);
        request.setAttribute("rq_unidad", unidad);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/bussinessUnits.jsp").forward(request, response);
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

    public void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
