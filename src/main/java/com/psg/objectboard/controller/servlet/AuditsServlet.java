package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.HeaderConductSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeaderConductSurveyVO;
import com.psg.objectboard.model.service.Other.DateFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AuditsServlet", urlPatterns = "/audits")
public class AuditsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        HeaderConductSurveyDAO cod= new HeaderConductSurveyDAO();
        ArrayList<HeaderConductSurveyVO> polls = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        String condicion = "headersSurvey_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                        " AND user_auditor = '" + user_email + "' AND auditable = 'T' AND to_audited = 'T'";

        polls = cod.getListHeaderConductSurvey(condicion);
        if (polls.size() !=0){
            DateFunctions df = new DateFunctions();
            for (int x=0; x < polls.size(); x++){
                polls.get(x).setConduct_date(df.parseFecha_2(polls.get(x).getConduct_date()) + polls.get(x).getConduct_date().substring(10,19));
            }
        }

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", polls.size());
        request.setAttribute("rq_companyNumber", company_number);
        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.setAttribute("rq_polls", polls);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/audits.jsp").forward(request, response);
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