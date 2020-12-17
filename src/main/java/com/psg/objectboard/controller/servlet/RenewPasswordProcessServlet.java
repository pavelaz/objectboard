package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "RenewPasswordProcessServlet", urlPatterns = "/renewpasswordprocess")
public class RenewPasswordProcessServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String company_number=null;
        if(request.getParameter("p_company_number")!=null){
            company_number=request.getParameter("p_company_number");
        }
        String new_password=null;
        if(request.getParameter("p_new_password")!=null){
            new_password=request.getParameter("p_new_password");
        }
        String user_email=null;
        if(request.getParameter("p_email")!=null){
            user_email=request.getParameter("p_email");
        }
        String data_user = "boarduser",
               data_password = "1#Object5Board*%";

        MasterUserVO muv = null;
        MasterUserDAO mud = new MasterUserDAO();

        try {
            muv = mud.serchMasterUserDAO(user_email,company_number);

            if (muv.getResult()) {
                // crea el objeto de coneccion
                OtherConexion ocn = new OtherConexion();
                // instancio y establesco coneccion
                Connection con = null;
                con = ocn.conectarse(data_user,data_password);
                // iniciar transacion
                ocn.init_trans(con);
                // cambia el password en los archivos
                mud.updatePasswordValidDAO( muv, con, new_password);

                // valida status de la transacion
                ocn.valida_trans(con, muv.getResult());
                //
                ocn.cierra_coneccion(con);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        request.setAttribute("rq_result", muv.getResult());

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/renew_password_process.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
