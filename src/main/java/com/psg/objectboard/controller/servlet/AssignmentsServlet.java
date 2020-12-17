package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.HeadersSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.*;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.*;
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

@WebServlet(name = "AssignmentsServlet", urlPatterns = "/assignments")
public class AssignmentsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String condicion =  null;
        String acciones = "consult";
        String email = null,
                survey = "0";
        //         frequency = null;
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }
        String column = "0";
        if(request.getParameter("p_column")!=null) {
            column = request.getParameter("p_column");
        }

        if (acciones.equals("create") || acciones.equals("save")) {
            MasterUserDAO mud= new MasterUserDAO();
            ArrayList<MasterUserVO> users = null;
            mud.setDataUser(data_user);
            mud.setDataPassword(data_pasword);
            condicion = "bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number);
            users = mud.getListMasterUser(condicion);
            request.setAttribute("rq_users", users);

            HeadersSurveyDAO hsd = new HeadersSurveyDAO();
            ArrayList<HeadersSurveyVO> survs = null;
            hsd.setDataUser(data_user);
            hsd.setDataPassword(data_pasword);
            condicion = "bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                        " AND surveyStatus = 'F'";
            survs = hsd.getListHeadersSurvey(condicion);
            request.setAttribute("rq_survs", survs);

            FrecuencyDAO frd = new FrecuencyDAO();
            ArrayList<FrecuencyVO> frecu = null;
            frecu = frd.getFrecuency();
            request.setAttribute("rq_frecu", frecu);

            ArrayList<CommentsLegendsVO> legends = null;
            ArrayList<CommentsExceptionsVO> exceptions = null;
            ArrayList<CommentsNotesVO> notes = null;
            ArrayList<CommentsTermsVO> terms = null;

            CommentsNotesDAO conod = new CommentsNotesDAO();
            condicion = "bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                    " AND comment_type = '3'";
            conod.setDataUser(data_user);
            conod.setDataPassword(data_pasword);
            notes = conod.getListComments(condicion);
            request.setAttribute("rq_notes", notes);

            CommentsLegendsDAO coled = new CommentsLegendsDAO();
            condicion = "bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                    " AND comment_type = '1'";
            coled.setDataUser(data_user);
            coled.setDataPassword(data_pasword);
            legends = coled.getListComments(condicion);
            request.setAttribute("rq_legends", legends);

            CommentsExceptionsDAO coexd = new CommentsExceptionsDAO();
            condicion = "bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                    " AND comment_type = '2'";
            coexd.setDataUser(data_user);
            coexd.setDataPassword(data_pasword);
            exceptions = coexd.getListComments(condicion);
            request.setAttribute("rq_exceptions", exceptions);

            CommentsTermsDAO coted = new CommentsTermsDAO();
            condicion = "bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                    " AND comment_type = '4'";
            coted.setDataUser(data_user);
            coted.setDataPassword(data_pasword);
            terms = coted.getListComments(condicion);
            request.setAttribute("rq_terms", terms);

            if(request.getParameter("p_email")!=null) {
                email = request.getParameter("p_email");
            }
            request.setAttribute("rq_email", email);
            if(request.getParameter("p_survey")!=null) {
                survey = request.getParameter("p_survey");
            }
            request.setAttribute("rq_survey", survey);
        }

        AssignmentsDAO cod= new AssignmentsDAO();
        ArrayList<AssignmentsConsultVO> asigna = null;
        ArrayList<AssignmentsConsultVO> puntual = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        if (acciones.equals("save")) {
            condicion = "masterUser_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                    " AND masterUser_mu_email = '" + email + "' AND headersSurvey_survey_code = " +
                    Integer.parseInt(survey);
            puntual = cod.getListAssignmentsConsult(condicion);
            request.setAttribute("rq_puntual", puntual);

            condicion = "masterUser_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number) +
                    " AND masterUser_mu_email = '" + email + "' AND headersSurvey_survey_code <> " +
                    Integer.parseInt(survey);
        }else
            condicion = "masterUser_bussinessUnit_bu_bis_code = " + Integer.parseInt(company_number);
        asigna = cod.getListAssignmentsConsult(condicion);

        if (asigna.size() !=0){
            DateFunctions df = new DateFunctions();
            for (int x=0; x < asigna.size(); x++){
                asigna.get(x).setDateStart(df.parseFecha_2(asigna.get(x).getDateStart()));
            }
        }
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_asigna", asigna);
        request.setAttribute("rq_column", column);
        request.setAttribute("rq_companyLogoName", company_logo_name);
        request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/assignments.jsp").forward(request, response);
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