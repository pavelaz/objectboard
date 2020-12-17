package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "ConfirmResetServlet", urlPatterns = "/confirmreset")
public class ConfirmResetServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        // valores  ue vienen por el get
        String company_number=null;
        if(request.getParameter("pc")!=null){
            company_number=request.getParameter("pc");
        }
        String new_password=null;
        if(request.getParameter("ps")!=null){
            new_password=request.getParameter("ps");
        }
        //System.out.println("Serial a Confirmar: "+serial);
        String user_email=null;
        if(request.getParameter("pu")!=null){
            user_email=request.getParameter("pu");
        }
        String fecha_email=null;
        if(request.getParameter("pf")!=null){
            fecha_email=request.getParameter("pf");
        }
        String data_user = "boarduser",
               data_password = "1#Object5Board*%";

        System.out.println("New:"+new_password+ " Fecha: "+fecha_email);
        MasterUserVO muv = null;
        MasterUserDAO mud = new MasterUserDAO();
        Boolean existe_usuario = null,
                existe_fecha= null;
        mud.setDataUser(data_user);
        mud.setDataPassword(data_password);

        try {
            muv = mud.serchMasterUserDAO(user_email,company_number);
            // Usuario existe o no registrado en la Unidad de negocios.
            existe_usuario= muv.getResult();

            if (existe_usuario) {
                OtherFunctions of = new OtherFunctions();
                String fecha_archivos = null;
                fecha_archivos = of.sacaFecha(muv.getMuDateResetPwd());
                //
                DateFunctions df = new DateFunctions();
                String fecha_hoy = null;
                fecha_hoy = of.sacaFecha(df.fechaFull(9));

                assert fecha_email != null;
                if (fecha_email.equals(fecha_archivos) && fecha_email.equals(fecha_hoy)) {
                    existe_fecha = true;
                } else {
                    existe_fecha = false;
                    muv.setResult(false);
                }
            }

            if (muv.getResult()) {
                // crea el objeto de coneccion
                OtherConexion ocn = new OtherConexion();
                // instancio y establesco coneccion
                Connection con = null;
                con = ocn.conectarse(data_user,data_password);
                // iniciar transacion
                ocn.init_trans(con);

                mud.updateFinalResetDAO( muv, con, new_password);

                // valida status de la transacion
                ocn.valida_trans(con, muv.getResult());
                //
                ocn.cierra_coneccion(con);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        request.setAttribute("rq_existeUsuario", existe_usuario);
        request.setAttribute("rq_existeFecha", existe_fecha);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/confirm_reset.jsp").forward(request, response);
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
