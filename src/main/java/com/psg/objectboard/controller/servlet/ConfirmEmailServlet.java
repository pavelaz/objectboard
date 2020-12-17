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
import java.sql.SQLException;

@WebServlet(name = "ConfirmEmailServlet", urlPatterns = "/confirmemail")
public class ConfirmEmailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        // valores  ue vienen por el post
        String company_number=null;
        if(request.getParameter("pc")!=null){
            company_number=request.getParameter("pc");
        }
        String user_serial=null;
        if(request.getParameter("ps")!=null){
            user_serial=request.getParameter("ps");
        }
        System.out.println("Serial a Confirmar: "+user_serial);
        String user_email=null;
        if(request.getParameter("pu")!=null){
            user_email=request.getParameter("pu");
        }

        String data_user = "boarduser",
               data_password = "1#Object5Board*%";
        MasterUserVO muv = null;
        MasterUserDAO mud = new MasterUserDAO();
        Boolean existe_usuario = null,
                existe_serial = null,
                existe_previo = null;
        mud.setDataUser(data_user);
        mud.setDataPassword(data_password);

        try {
            muv = mud.serchMasterUserDAO(user_email,company_number);
            // Usuario existe o no registrado en la Unidad de negocios.
            existe_usuario= muv.getResult();
            // valida se ya se habia confirmado previamente
            if (muv.getMuEmailConfirm().equals("T"))
                existe_previo = true;
            else
                existe_previo  = false;
            if (!existe_previo) {
                // crea el objeto de coneccion
                OtherConexion ocn = new OtherConexion();
                // instancio y establesco coneccion
                Connection con = null;
                con = ocn.conectarse(data_user,data_password);
                // iniciar transacion
                ocn.init_trans(con);
                if (existe_usuario) {
                    if (muv.getMuConfirmCode().equals(user_serial)) {
                        mud.updateEmailConfirmDAO(muv, con);
                        existe_serial = true;
                    } else
                        existe_serial = false;
                }
                // valida status de la transacion
                ocn.valida_trans(con, muv.getResult());
                //
                ocn.cierra_coneccion(con);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        request.setAttribute("rq_existeUsuario", existe_usuario);
        request.setAttribute("rq_existePrevio", existe_previo);
        request.setAttribute("rq_existeSerial", existe_serial);
        request.setAttribute("rq_result", muv.getResult());

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/confirm_email.jsp").forward(request, response);
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
