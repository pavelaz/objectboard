package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.TypifiedDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.TypifiedVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TypifiedsServlet", urlPatterns = "/typifieds")
public class TypifiedsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }

        if (acciones.equals("save")){
            String ctypified_code1 = null;
            if(request.getParameter("p_levels1")!=null) {
                ctypified_code1 = request.getParameter("p_levels1");
            }
            String ctypified_code2 = null;
            if(request.getParameter("p_levels2")!=null) {
                ctypified_code2 = request.getParameter("p_levels2");
            }
            String ctypified_code3 = null;
            if(request.getParameter("p_levels3")!=null) {
                ctypified_code3 = request.getParameter("p_levels3");
            }

            request.setAttribute("rq_level1", ctypified_code1);
            request.setAttribute("rq_level2", ctypified_code2);
            request.setAttribute("rq_level3", ctypified_code3);
        }
        TypifiedDAO cod= new TypifiedDAO();
        ArrayList<TypifiedVO> type = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        type = cod.getListTypified("bussinessUnit_bu_bis_code = "+company_number);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", type.size());
        request.setAttribute("rq_acciones", acciones);

        request.setAttribute("rq_orga", type);
        request.setAttribute("rq_companyNumber", company_number);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);
        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/typifieds.jsp").forward(request, response);
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
