package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.TypifiedDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.TypifiedVO;
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

@WebServlet(name = "TypifiedsProcessServlet", urlPatterns = "/typifiedsprocess")
public class TypifiedsProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        //String company_logo_name = (String)objSesion.getAttribute("companyLogoName");
        //String company_logo_dir = (String)objSesion.getAttribute("companyLogoDirection");

        String acciones = " ";
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }

        Integer cual = null;
        String level1 = null,
               level2 = null,
               level3 = null,
               levels1 = null,
               levels2 = null,
               levels3 = null;
        ArrayList<String> cual_1 = new ArrayList<String>();
        ArrayList<String> cual_2 = new ArrayList<String>();
        ArrayList<String> cual_3 = new ArrayList<String>();

        // Recupero los valores necesarios para la accion
        switch(acciones){
            case "delete":
                Integer num_filas = 0;
                String none= null;
                if(request.getParameter("p_cuantos")!=null) {
                    none = request.getParameter("p_cuantos");
                    num_filas = Integer.parseInt(none);
                }
                for(int f=1 ; f < (num_filas + 1);f++){
                    if(request.getParameter("p_selec_"+f)!=null) {
                        none = request.getParameter("p_cual_1"+f);
                        if (cual_1.isEmpty()){
                            cual_1.add(0,none);
                        }else{
                            cual_1.add(none);
                        }
                        none = request.getParameter("p_cual_2"+f);
                        if (cual_2.isEmpty()){
                            cual_2.add(0,none);
                        }else{
                            cual_2.add(none);
                        }
                        none = request.getParameter("p_cual_3"+f);
                        if (cual_3.isEmpty()){
                            cual_3.add(0,none);
                        }else{
                            cual_3.add(none);
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_level1")!=null) {
                    level1 = request.getParameter("p_level1");
                }
                if(request.getParameter("p_level2")!=null) {
                    level2 = request.getParameter("p_level2");
                }
                if(request.getParameter("p_level3")!=null) {
                    level3 = request.getParameter("p_level3");
                }

                if(request.getParameter("p_levels1")!=null) {
                    levels1 = request.getParameter("p_levels1");
                }
                if(request.getParameter("p_levels2")!=null) {
                    levels2 = request.getParameter("p_levels2");
                }
                if(request.getParameter("p_levels3")!=null) {
                    levels3 = request.getParameter("p_levels3");
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        TypifiedVO cvo = new TypifiedVO();
        TypifiedVO old = new TypifiedVO();
        TypifiedDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cvo.setBussinessUnitBuBbisCode(Long.parseLong(company_number));
                cvo.setCtypifiedCode1(level1);
                cvo.setCtypifiedCode2(level2);
                cvo.setCtypifiedCode3(level3);
                cdo.insertTypifiedDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_1.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteTypifiedDAO(Integer.parseInt(company_number),
                                                                    cual_1.get(x),
                                                                    cual_2.get(x),
                                                                    cual_3.get(x),
                                                                    con));
                        }
                    }
                }else{
                    cvo.setBussinessUnitBuBbisCode(Long.parseLong(company_number));
                    cvo.setCtypifiedCode1(level1);
                    cvo.setCtypifiedCode2(level2);
                    cvo.setCtypifiedCode3(level3);

                    old.setBussinessUnitBuBbisCode(Long.parseLong(company_number));
                    old.setCtypifiedCode1(levels1);
                    old.setCtypifiedCode2(levels2);
                    old.setCtypifiedCode3(levels3);
                    cdo.updateTypifiedDAO(cvo,old,con);
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
        request.setAttribute("rq_companyNumber", company_number);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);
        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("LogoutServlet");
        }
    }
}
