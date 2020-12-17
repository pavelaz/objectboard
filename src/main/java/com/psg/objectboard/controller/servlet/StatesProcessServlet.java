package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.StateDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.StateVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "StatesProcessServlet", urlPatterns = "/statesprocess")
public class StatesProcessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String acciones = " ";
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        Integer cual_p = null;
        Integer cual_s = null;
        String name = null;
        ArrayList<Integer> cual_pais = new ArrayList<Integer>();
        ArrayList<Integer> cual_state = new ArrayList<Integer>();
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
                        none = request.getParameter("p_cual_p"+f);
                        if (cual_pais.isEmpty()){
                            cual_pais.add(0,Integer.parseInt(none));
                        }else{
                            cual_pais.add(Integer.parseInt(none));
                        }
                        //
                        none = request.getParameter("p_cual_s"+f);
                        if (cual_state.isEmpty()){
                            cual_state.add(0,Integer.parseInt(none));
                        }else{
                            cual_state.add(Integer.parseInt(none));
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_name")!=null) {
                    name = request.getParameter("p_name");
                }
                if(request.getParameter("p_country_number")!=null){
                    none=request.getParameter("p_country_number");
                    cual_p = Integer.parseInt(none);
                }
                if(request.getParameter("p_state")!=null){
                    none=request.getParameter("p_state");
                    cual_s = Integer.parseInt(none);
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        StateVO cvo = new StateVO();
        StateDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        cdo.setDataUser(data_user);
        cdo.setDataPassword(data_pasword);
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cvo.setStName(name);
                cvo.setCountryCoCountryCode(cual_p);
                cdo.insertStateDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_pais.size();x++){
                        if (cvo.getResult()){
                            System.out.println("Estado: "+cual_state.get(x)+ " Pais: "+cual_pais.get(x));
                            cvo.setResult(cdo.deleteStateDAO(cual_state.get(x),cual_pais.get(x),con));
                        }
                    }
                }else{
                    cvo.setCountryCoCountryCode(cual_p);
                    cvo.setStStateCode(cual_s);
                    cvo.setStName(name);
                    cdo.updateStatetDAO(cvo,con);
                }
            }
            // valida status de la transacion
            ocn.valida_trans(con,cvo.getResult());
            //
            ocn.cierra_coneccion(con);
        }catch (Exception es){
            System.out.println(es.getMessage());
        }
        //cvo.setResult(false);
        request.setAttribute("rq_result", cvo.getResult());
        request.setAttribute("rq_pantalla", pantalla);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
