package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "MasterUserProcessServlet", urlPatterns = "/masteruserprocess")
public class MasterUserProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String) objSesion.getAttribute("companyName");
        String user_name = (String) objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }

        Integer cual_u = null;
        Integer cual_e = null;
        ArrayList<Integer> cual_user = new ArrayList<Integer>();
        ArrayList<String> cual_email = new ArrayList<String>();
        // Recupero los valores necesarios para la accion
        Integer num_filas = 0;
        String none= null;
        if(request.getParameter("p_cuantos")!=null) {
            none = request.getParameter("p_cuantos");
            num_filas = Integer.parseInt(none);
        }
        for(int f=1 ; f < (num_filas + 1);f++){
            if(request.getParameter("p_selec_"+f)!=null) {
                    none = request.getParameter("p_cual_u"+f);
                    if (cual_user.isEmpty()){
                        cual_user.add(0,Integer.parseInt(none));
                    }else{
                        cual_user.add(Integer.parseInt(none));
                    }
                    //
                    none = request.getParameter("p_cual_e"+f);
                    if (cual_email.isEmpty()){
                        cual_email.add(0,none);
                    }else{
                        cual_email.add(none);
                    }
                }
        }
        // Realizo las acciones solicitadas sobe la base de datos
        MasterUserVO cvo = new MasterUserVO();
        MasterUserDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        cdo.setDataUser(data_user);
        cdo.setDataPassword(data_pasword);
        con=ocn.conectarse(data_user,data_pasword);

        try{
           // iniciar transacion
           ocn.init_trans(con);
           cvo.setResult(true);
           for(int x=0 ; x < cual_user.size();x++){
               if (cvo.getResult()){
                        System.out.println("User: " +cual_user.get(x)+" Email: "+cual_email.get(x));
                        cvo.setResult(cdo.deleteMasterUserDAO(cual_user.get(x),cual_email.get(x),con));
                    }
           }
           // valida status de la transacion
           ocn.valida_trans(con,cvo.getResult());
           //
           ocn.cierra_coneccion(con);
        }catch (Exception es){
            System.out.println(es.getMessage());
        }

        request.setAttribute("rq_result", cvo.getResult());
        request.setAttribute("rq_pantalla", pantalla);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/masterUsers.jsp").forward(request, response);
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
