package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.CertificationsDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactsDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.CertificationsVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactsVO;
import com.psg.objectboard.model.service.Other.OtherConexion;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ContactsProcessServlet", urlPatterns = "/contactsprocess")
public class ContactsProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        //String company_name = (String)objSesion.getAttribute("companyName");
        //String user_name = (String)objSesion.getAttribute("userName");
        String user_email = (String)objSesion.getAttribute("userEmail");
        String user_project = (String)objSesion.getAttribute("companyProject");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String)objSesion.getAttribute("companyNumber");

        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla = request.getParameter("p_pantalla");
        }

        String acciones = " ";
        if(request.getParameter("p_acciones")!=null){
            acciones = request.getParameter("p_acciones");
        }

        ArrayList<String> cual_1 = new ArrayList<String>();
        String usr_email = null,
               name = null,
               bname = null,
               ad1 = null,
               ad2 = null,
               cell = null,
               land = null,
               email = "F",
               sms = "F",
               type = null,
               from = "F",
               back = "F";

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
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_user_email")!=null) {
                    usr_email = request.getParameter("p_user_email");
                }
                if(request.getParameter("p_name")!=null) {
                    name = request.getParameter("p_name");
                }
                if(request.getParameter("p_bname")!=null) {
                    bname = request.getParameter("p_bname");
                }
                if(request.getParameter("p_ad1")!=null) {
                    ad1 = request.getParameter("p_ad1");
                }
                if(request.getParameter("p_ad2")!=null) {
                    ad2 = request.getParameter("p_ad2");
                }
                if(request.getParameter("p_cell")!=null) {
                    cell = request.getParameter("p_cell");
                    cell = cell.replace("-","");
                }
                if(request.getParameter("p_land")!=null) {
                    land = request.getParameter("p_land");
                    land = land.replace("-","");
                }
                if(request.getParameter("p_email")!=null) {
                    email = request.getParameter("p_email");
                }
                if(request.getParameter("p_sms")!=null) {
                    sms = request.getParameter("p_sms");
                }
                if(request.getParameter("p_type")!=null) {
                    type = request.getParameter("p_type");
                }
                if(request.getParameter("p_from")!=null) {
                    from = request.getParameter("p_from");
                }
                if(request.getParameter("p_back")!=null) {
                    back = request.getParameter("p_back");
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        ContactsVO cvo = new ContactsVO();
        ContactsDAO cdo = new ContactsDAO();
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cvo.setMasterUser_bussinessUnit_bu_bis_code(Long.parseLong(company_number));
                cvo.setMasterUser_mu_email(user_email);
                cvo.setCto_project(Integer.parseInt(user_project));
                cvo.setCto_email_direction(usr_email.trim());
                cvo.setCto_name(name);
                cvo.setCto_business_name(bname);
                cvo.setCto_address_1(ad1);
                cvo.setCto_address_2(ad2);
                cvo.setCto_phone_cell(cell);
                cvo.setCto_landline(land);
                cvo.setCto_email_message(email);
                cvo.setCto_sms_message(sms);
                cvo.setCto_front_yard(from);
                cvo.setCto_back_yard(back);
                cvo.setCto_type(type);
                cdo.insertContactsDAO(cvo,con);
            }else{
                /*
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
                */
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

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }
}
