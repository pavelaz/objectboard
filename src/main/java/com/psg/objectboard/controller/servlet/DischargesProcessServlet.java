package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.DischargeDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.DischargeVO;
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

@WebServlet(name = "DischargesProcessServlet", urlPatterns = "/dischargesprocess")
public class DischargesProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String) objSesion.getAttribute("companyName");
        String user_name = (String) objSesion.getAttribute("userName");
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
        DischargeVO cvo = new DischargeVO();
        DateFunctions df = new DateFunctions();
        ArrayList<Integer> cual_lic = new ArrayList<Integer>();

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
                        none = request.getParameter("p_cual_l"+f);
                        if (cual_lic.isEmpty()){
                            cual_lic.add(0,Integer.parseInt(none));
                        }else{
                            cual_lic.add(Integer.parseInt(none));
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                String fecha = null;
                if(request.getParameter("p_license_number")!=null &&
                   request.getParameter("p_license_number")!=""){
                    none=request.getParameter("p_license_number");
                    cvo.setDiLicenseNum(Long.parseLong(none));
                }
                if(request.getParameter("p_start_d")!=null) {
                    none = df.parseFecha_1(request.getParameter("p_start_d"));
                    cvo.setDiStartDate(none);
                }
                if(request.getParameter("p_end_d")!=null) {
                    none = df.parseFecha_1(request.getParameter("p_end_d"));
                    cvo.setDiEndDate(none);
                }
                if(request.getParameter("p_number_user")!=null) {
                    none = request.getParameter("p_number_user");
                    cvo.setDiNumberUser(Integer.parseInt(none));
                }
                if(request.getParameter("p_permanent")!=null) {
                    none = request.getParameter("p_permanent");
                    cvo.setDiPermanent(none);
                }
                if(request.getParameter("p_sale_code")!=null) {
                    none = request.getParameter("p_sale_code");
                    cvo.setDiSalesCode(Integer.parseInt(none));
                }
                if(request.getParameter("p_license_code")!=null) {
                    none = request.getParameter("p_license_code");
                    cvo.setDiLicenseCode(none);
                }
                if(request.getParameter("p_unit_number")!=null) {
                    none = request.getParameter("p_unit_number");
                    cvo.setBussinessUnitBuBisCode(Long.parseLong(none));
                }
                if(request.getParameter("p_proj_number")!=null) {
                    none = request.getParameter("p_proj_number");
                    cvo.setProjectPrIdProject(Long.parseLong(none));
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        DischargeDAO cdo = null;
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cdo.insertDischargeDAO(cvo,con);
            }else{
                if (acciones.equals("delete")){
                    cvo.setResult(true);
                    for(int x=0 ; x < cual_lic.size();x++){
                        if (cvo.getResult()){
                            cvo.setResult(cdo.deleteDischargeDAO(cual_lic.get(x),con));
                        }
                    }
                }else{
                    cdo.updateDischargeDAO(cvo,con);
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
