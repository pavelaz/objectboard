package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.AssignmentsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.AssignmentsVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
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

@WebServlet(name = "AssignmentsProcessServlet", urlPatterns = "/assignmentsprocess")
public class AssignmentsProcessServlet extends HttpServlet {
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

        String user = null,
                pool_code = null,
                frecuency = null,
                note_code = null,
                legend_code = null,
                exception_code = null,
                term_code = null,
                timef = null;

        ArrayList<Integer> cual_1 = new ArrayList<Integer>();
        ArrayList<String>  cual_0 = new ArrayList<String>();
        // Recupero los valores necesarios para la accion
        switch(acciones){
            case "delete":
                Integer num_filas = 0;
                String  none= null;
                if(request.getParameter("p_cuantos")!=null) {
                    none = request.getParameter("p_cuantos");
                    num_filas = Integer.parseInt(none);
                }
                for(int f=1 ; f < (num_filas + 1);f++){
                    if(request.getParameter("p_selec_"+f)!=null) {
                        none = request.getParameter("p_cual_0"+f);
                        if (cual_0.isEmpty()){
                            cual_0.add(0,none);
                        }else{
                            cual_0.add(none);
                        }
                        none = request.getParameter("p_cual_1"+f);
                        if (cual_1.isEmpty()){
                            cual_1.add(0,Integer.parseInt(none));
                        }else{
                            cual_1.add(Integer.parseInt(none));
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_user")!=null) {
                    user = request.getParameter("p_user");
                }
                if(request.getParameter("p_poll")!=null) {
                    pool_code = request.getParameter("p_poll");
                }
                if(request.getParameter("p_frequency")!=null) {
                    frecuency = request.getParameter("p_frequency");
                }
                if(request.getParameter("p_timef")!=null) {
                    timef = request.getParameter("p_timef");
                }
                if(request.getParameter("p_note")!=null) {
                    note_code = request.getParameter("p_note");
                }
                if(request.getParameter("p_legend")!=null) {
                    legend_code = request.getParameter("p_legend");
                }
                if(request.getParameter("p_exception")!=null) {
                    exception_code = request.getParameter("p_exception");
                }
                if(request.getParameter("p_term")!=null) {
                    term_code = request.getParameter("p_term");
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicited sobe la base de datos
        AssignmentsVO cvo = new AssignmentsVO();
        AssignmentsDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                DateFunctions df = new DateFunctions();
                String fecha = null;
                fecha = df.fechaFull(9);
                cvo.setMasterUserMuEmail(user);
                cvo.setMasterUserBussinessUnitBuBisCode(Long.parseLong(company_number));
                cvo.setHeadersSurveySurveyCode(Long.parseLong(pool_code));
                cvo.setFrecuency(Integer.parseInt(frecuency));
                cvo.setTimeByFrecuency(Integer.parseInt(timef));
                cvo.setNotes(Long.parseLong(note_code));
                cvo.setLegends(Long.parseLong(legend_code));
                cvo.setExceptions(Long.parseLong(exception_code));
                cvo.setTerms(Long.parseLong(term_code));
                cvo.setDateStart(fecha);
                cdo.insertAssignmentsDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_1.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteAssignmentsDAO(Integer.parseInt(company_number),
                                                                    cual_0.get(x),cual_1.get(x),
                                                                    con));
                        }
                    }
                }else{
                    cvo.setMasterUserMuEmail(user);
                    cvo.setMasterUserBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setHeadersSurveySurveyCode(Long.parseLong(pool_code));
                    cvo.setFrecuency(Integer.parseInt(frecuency));
                    cvo.setTimeByFrecuency(Integer.parseInt(timef));
                    cvo.setNotes(Long.parseLong(note_code));
                    cvo.setLegends(Long.parseLong(legend_code));
                    cvo.setExceptions(Long.parseLong(exception_code));
                    cvo.setTerms(Long.parseLong(term_code));
                    cdo.updateAssignmentsDAO(cvo,con);
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
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);
        request.setAttribute("rq_companyNumber", company_number);
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
