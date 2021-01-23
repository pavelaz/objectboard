package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.AssignmentsConsultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ConductServlet", urlPatterns = "/conduct")
public class ConductServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String user_board_role = (String)objSesion.getAttribute("userBoardRole");

        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }
        String column = "0";
        if(request.getParameter("p_column")!=null) {
            column = request.getParameter("p_column");
        }

        AssignmentsDAO cdo = new AssignmentsDAO();
        cdo.setDataPassword(data_pasword);
        cdo.setDataUser(data_user);
        ArrayList<AssignmentsConsultVO> surveysAssign = null;
        String none = "masterUser_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number);
        if (Long.parseLong(company_number) == 1)
            surveysAssign = cdo.getListAssignmentsConsult(none);
        else{
            if (user_board_role.equals("1") || user_board_role.equals("2")){
                surveysAssign = cdo.getListAssignmentsConsult(none);
            }else{
                none = "masterUser_bussinessUnit_bu_bis_code = " +
                        Long.parseLong(company_number) + " AND masterUser_mu_email = '" +
                        user_email + "'";
                surveysAssign = cdo.getListAssignmentsConsult(none);
                /*if (Long.parseLong(user_board_role) == 3){

                }else{
                    surveysAssign = cdo.getListAssignmentsConsult("masterUser_bussinessUnit_bu_bis_code = " + Long.parseLong(company_number) );
                }*/
            }
        }
        request.setAttribute("rq_surveysAssign", surveysAssign);

        if (acciones.equals("create")){
            if(request.getParameter("p_name")!=null) {
               none = request.getParameter("p_name");
            }
            request.setAttribute("rq_surveyName", none);

            if(request.getParameter("p_email")!=null) {
                none = request.getParameter("p_email");
            }
            request.setAttribute("rq_email", none);

            if(request.getParameter("p_audited")!=null) {
                none = request.getParameter("p_audited");
            }
            request.setAttribute("rq_audited", none);

            if(request.getParameter("p_code")!=null) {
                none = request.getParameter("p_code");
            }
            request.setAttribute("rq_code", none);

            MasterUserDAO mdo = new MasterUserDAO();
            ArrayList<MasterUserVO> musers = null;
            mdo.setDataPassword(data_pasword);
            mdo.setDataUser(data_user);
            musers = mdo.getListMasterUser("bussinessUnit_bu_bis_code = " + Long.parseLong(company_number));
            request.setAttribute("rq_musers", musers);
        }

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_column", column);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/conduct.jsp").forward(request, response);
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