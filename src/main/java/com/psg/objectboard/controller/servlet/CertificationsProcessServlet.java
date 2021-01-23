package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.CertificationsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;
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

@WebServlet(name = "CertificationsProcessServlet", urlPatterns = "/certificationsprocess")
public class CertificationsProcessServlet extends HttpServlet {
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

        String name = null;
        String code = null;

        ArrayList<Integer> cual_1 = new ArrayList<Integer>();

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
                            cual_1.add(0,Integer.parseInt(none));
                        }else{
                            cual_1.add(Integer.parseInt(none));
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_name")!=null) {
                    name = request.getParameter("p_name");
                }
                if(request.getParameter("p_code")!=null) {
                    code = request.getParameter("p_code");
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        CertificationsVO cvo = new CertificationsVO();
        CertificationsDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                cvo.setName(name);
                cdo.insertCertificationsDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_1.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteCertificationsDAO(Integer.parseInt(company_number),
                                                                    cual_1.get(x),
                                                                    con));
                        }
                    }
                }else{
                    cvo.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                    cvo.setCertificationCode(Long.parseLong(code));
                    cvo.setName(name);
                    cdo.updateCertificationsDAO(cvo,con);
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
