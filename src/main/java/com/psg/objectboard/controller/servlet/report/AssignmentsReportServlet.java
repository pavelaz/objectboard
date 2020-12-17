package com.psg.objectboard.controller.servlet.report;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AdditionalDataReportVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.report.AssignmentReportVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AssignmentsReportServlet", urlPatterns = "/assignmentsreport")
public class AssignmentsReportServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_password = (String)objSesion.getAttribute("dataPassword");

        String path_logo = "src/main/webapp/complements/img/logos/02/";
        String data_logo = path_logo + "logo-green-1x.png";
        String title_report = "Assignment Report";
        String caption_table = "Information";
        String footer_table = "Assignment processes pending to be executed.";

        AdditionalDataReportVO additionalData = new AdditionalDataReportVO(company_name, user_name, user_email ,data_logo, title_report, caption_table, footer_table);

        AssignmentsDAO cod = new AssignmentsDAO();
        ArrayList<AssignmentsConsultVO> asigna = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_password);

        asigna = cod.getListAssignmentsConsult("masterUser_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number));

        AssignmentReportVO assignmentsReport = new AssignmentReportVO(additionalData, asigna);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        /*ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(assignmentsReport));

        String json = objectMapper.writeValueAsString(assignmentsReport);

        request.setAttribute("rq_assignmentReport", objectMapper.writeValueAsString(assignmentsReport));
        request.setAttribute("rq_reportNumber", 1);*/

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_report.jsp").forward(request, response);
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
