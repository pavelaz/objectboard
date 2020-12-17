package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RenewPasswordServlet", urlPatterns = "/renewpassword")
public class RenewPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String company_number=null;
        if(request.getParameter("p_company_number")!=null){
            company_number=request.getParameter("p_company_number");
        }
        String company_name=null;
        if(request.getParameter("p_company_name")!=null){
            company_name=request.getParameter("p_company_name");
        }
        String user_email=null;
        if(request.getParameter("p_email")!=null){
            user_email=request.getParameter("p_email");
        }
        String data_user = "boarduser",
               data_password = "1#Object5Board*%";

        MasterUserVO muv = null;
        MasterUserDAO mud = new MasterUserDAO();
        Boolean existe_usuario=null;
        String question=null,
                answer=null,
                password_actual=null,
                password_ant=null;
        OtherFunctions of = new OtherFunctions();

        try {

            mud.setDataUser(data_user);
            mud.setDataPassword(data_password);
            muv = mud.serchMasterUserDAO(user_email, company_number);
            // Usuario existe o no registrado en la Unidad de negocios.
            existe_usuario = muv.getResult();

            if (existe_usuario){
                question=muv.getMuQuestion();
                answer=muv.getMuAnswer();
                password_actual = muv.getMuPassword();
                password_ant=muv.getMuPasswordOld();
            }
        }catch (Exception e){
            System.out.println("Error en busqueda de pregunta");
        }

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userEmail", user_email);
        request.setAttribute("rq_question", question);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_answer", of.toHex(answer));
        request.setAttribute("rq_passwordActual", of.toHex(password_actual));
        request.setAttribute("rq_passwordAnt", of.toHex(password_ant));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/renew_password.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
