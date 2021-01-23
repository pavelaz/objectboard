package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.CityDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CityVO;
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

@WebServlet(name = "CitiesProcessServlet", urlPatterns = "/citiesprocess")
public class CitiesProcessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

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
        Integer cual_c = null;
        String name = null;
        ArrayList<Integer> cual_pais = new ArrayList<Integer>();
        ArrayList<Integer> cual_state = new ArrayList<Integer>();
        ArrayList<Integer> cual_city = new ArrayList<Integer>();
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
                        //
                        none = request.getParameter("p_cual_c"+f);
                        if (cual_city.isEmpty()){
                            cual_city.add(0,Integer.parseInt(none));
                        }else{
                            cual_city.add(Integer.parseInt(none));
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
                if(request.getParameter("p_state_number")!=null){
                    none=request.getParameter("p_state_number");
                    cual_s = Integer.parseInt(none);
                }
                if(request.getParameter("p_city_number")!=null){
                    none=request.getParameter("p_city_number");
                    cual_c = Integer.parseInt(none);
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        CityVO cvo = new CityVO();
        CityDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        cdo.setDataUser(data_user);
        cdo.setDataPassword(data_pasword);
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cvo.setCiName(name);
                cvo.setStatesCountryCoCountryCode(cual_p);
                cvo.setStatesStStateCode(cual_s);
                cdo.insertCityDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_pais.size();x++){
                        if (cvo.getResult()){
                            System.out.println("City: " +cual_city.get(x)+" State: "+cual_state.get(x)+ " Country: "+cual_pais.get(x));
                            cvo.setResult(cdo.deleteCityDAO(cual_city.get(x),cual_state.get(x),cual_pais.get(x),con));
                        }
                    }
                }else{
                    cvo.setStatesCountryCoCountryCode(cual_p);
                    cvo.setStatesStStateCode(cual_s);
                    cvo.setCiCityCode(cual_c);
                    cvo.setCiName(name);
                    cdo.updateCitytDAO(cvo,con);
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

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
