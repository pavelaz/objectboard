package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.UserRoleDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.UserRoleVO;
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

@WebServlet(name = "UserRolesProcessServlet", urlPatterns = "/userrolesprocess")
public class UserRolesProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String data_user = (String) objSesion.getAttribute("dataUser");
        String data_pasword = (String) objSesion.getAttribute("dataPassword");

        String acciones = " ";
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        Integer cual = null;
        String none= null;
        UserRoleVO cvo = new UserRoleVO();
        //DateFunctions df = new DateFunctions();
        ArrayList<String> cual_e = new ArrayList<String>();
        ArrayList<Integer> cual_u = new ArrayList<Integer>();
        ArrayList<Integer> cual_p = new ArrayList<Integer>();

        // Recupero los valores necesarios para la accion
        switch(acciones){
            case "delete":
                Integer num_filas = 0;

                if(request.getParameter("p_cuantos")!=null) {
                    none = request.getParameter("p_cuantos");
                    num_filas = Integer.parseInt(none);
                }
                for(int f=1 ; f < (num_filas + 1);f++){
                    if(request.getParameter("p_selec_"+f)!=null) {
                        none = request.getParameter("p_cual_e"+f);
                        if (cual_e.isEmpty())
                            cual_e.add(0,none);
                        else
                            cual_e.add(none);
                        none = request.getParameter("p_cual_u"+f);
                        if (cual_u.isEmpty())
                            cual_u.add(0,Integer.parseInt(none));
                        else
                            cual_u.add(Integer.parseInt(none));
                        none = request.getParameter("p_cual_p"+f);
                        if (cual_p.isEmpty())
                            cual_p.add(0,Integer.parseInt(none));
                        else
                            cual_p.add(Integer.parseInt(none));
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                //String fecha = null;
                if(request.getParameter("p_unit")!=null &&
                        request.getParameter("p_unit")!=""){
                    none=request.getParameter("p_unit");
                    cvo.setMasterUserBussinessUnitBuBisCode(Long.parseLong(none));
                }
                if(request.getParameter("p_email")!=null) {
                    none = request.getParameter("p_email");
                    cvo.setMasterUserMuEmail(none);
                }
                if(request.getParameter("p_proj")!=null) {
                    none = request.getParameter("p_proj");
                    cvo.setProjectPrIdProject(Long.parseLong(none));
                }
                if(request.getParameter("p_role")!=null) {
                    none = request.getParameter("p_role");
                    cvo.setUmRole(Integer.parseInt(none));
                }
                if(request.getParameter("p_status")!=null) {
                    none = request.getParameter("p_status");
                    cvo.setUmStatus(none);
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        UserRoleDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cdo.insertUserRolesDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_e.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteUserRoleDAO(cual_e.get(x),cual_u.get(x),cual_p.get(x),con));
                        }
                    }
                }else{
                    cdo.updateUserRoleDAO(cvo,con);
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

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
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
