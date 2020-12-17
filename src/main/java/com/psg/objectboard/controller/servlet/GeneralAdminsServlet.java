package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.DischargeDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.DischargeVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.DateFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "GeneralAdminsServlet", urlPatterns = "/graladmins")
public class GeneralAdminsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // valores  que vienen por el post
        String logo_name = null;
        String company_number=null;
        if(request.getParameter("p_company_number")!=null){
            company_number = request.getParameter("p_company_number");
        }
        if(request.getParameter("p_company_logo")!=null){
            logo_name=request.getParameter("p_company_logo");
        }
        String company_name=null;
        if(request.getParameter("p_company_name")!=null){
            company_name=request.getParameter("p_company_name");
        }
        String user_email=null;
        if(request.getParameter("p_email")!=null){
            user_email=request.getParameter("p_email");
        }
        String user_passwd=null;
        if(request.getParameter("p_password")!=null){
            user_passwd=request.getParameter("p_password");
        }

        MasterUserVO muv = null;
        MasterUserDAO mud = new MasterUserDAO();
        String nombre_user = null;
        Integer user_time_seccion = 60;
        String data_user = "boarduser",
               data_password = "1#Object5Board*%",
               logo_direction = " ";

        if (Integer.parseInt(company_number) == 1){
           logo_direction = "/complements/img/";
        }else{
            if (logo_name.equals("favicon2.png")){
                logo_direction = "/objectboard/complements/img/";
            }else {
                if (Integer.parseInt(company_number) < 10) {
                    logo_direction = "/objectboard/complements/img/logos/0" + company_number + "/";
                } else {
                    logo_direction = "/objectboard/complements/img/logos/" + company_number + "/";
                }
            }
        }

        Boolean existe_usuario=null,        // para validar si el usuario existe o no
                existe_status=null,         // para validar si el usuario esta activo o no
                existe_passwd=null,         // para validar si passwor coincide o no
                existe_email_confirm=null,  // para validar si el email a sido confirmado o no
                pwd_vigente = null,         // para saber si el password a caducado o no de acuerdo a los dias de vigencia
                existe_todo=null,           // para validar si todas las condiciones anteriores son ciertas o no
                user_not_expires=null,      // para validar si el usuario ha caducado
                existe_licence=true;        // para validar si existen licencias activas para algun modulo para este cliente
        mud.setDataUser(data_user);
        mud.setDataPassword(data_password);

        try {
            muv = mud.serchMasterUserDAO(user_email,company_number);
            // Usuario existe o no registrado en la Unidad de negocios.
            existe_usuario= muv.getResult();
            nombre_user = muv.getMuName();
            user_time_seccion = muv.getMuSectionTime();
            data_user=muv.getMuDataUser();
            data_password=muv.getMuDataPassword();

            String  falso = "F";

            if (existe_usuario) {
                // verifica si la contrasena esta vendida ("yyyy-dd-MM")
                //fecha = fnew.sacaFecha(muv.getMuDate());
                Date fecha_new = null,
                     fecha_hoy = null;
                DateFunctions fnew = new DateFunctions();
                fecha_hoy = fnew.parseFecha(fnew.fechaFull(7));
                fecha_new = fnew.sumarRestarDiasFecha(fnew.sacaFecha(muv.getMuDate()),muv.getMuEffectiveDays());
                int dif = fnew.diasEntreFechas(fecha_new,fecha_hoy);
                if (dif > 0)
                    pwd_vigente = true;
                else
                    pwd_vigente = false;
                // Verifica si el usuario caduca verifica si aun no ha caducado
                if (!muv.getMuExpires().equals(falso)){
                    fecha_new = fnew.sacaFecha(muv.getMuDateExpires());
                    dif = fnew.diasEntreFechas(fecha_new,fecha_hoy);
                    if (dif > 0)
                        user_not_expires = true;
                    else
                        user_not_expires = false;
                }else
                    user_not_expires=true;
                // verifica que el passsword registrado sea igual al introducido.
                if (muv.getMuPassword().equals(user_passwd))
                    existe_passwd = true;
                else
                    existe_passwd = false;
                // verifica si el email puesto a la hora del registro del usuario
                // Ya ha sido confirmado.  Por lo que le permite o no entrar por ello.
                if (!muv.getMuEmailConfirm().equals(falso))
                    existe_email_confirm = true;
                else
                    existe_email_confirm = false;
                // verifica se el estatus del usuario es activo o no dentro del sistema
                // y por ello les permite o no continuar.
                if (!muv.getMuStatus().equals(falso))
                    existe_status = true;
                else
                    existe_status = false;
                /* verifica si el susuario que va a entrar pertenece a la compania Super usuaria.*/
            }
            // Si la compania no es la 1, verifica si el cliente posee licencias activas para algun modulo
            if(Integer.parseInt(company_number) != 1){
                String condicion = "bussinessUnit_bu_bis_code = "+Integer.parseInt(company_number);
                ArrayList<DischargeVO> licences = new ArrayList<DischargeVO>();
                DischargeDAO did = new DischargeDAO();
                did.setDataUser(data_user);
                did.setDataPassword(data_password);
                licences = did.getListDischargeActive(condicion);
                if (licences.size() == 0)
                    existe_licence = false;
            }

            if (existe_usuario && existe_passwd && existe_email_confirm &&
                existe_status && pwd_vigente && user_not_expires && existe_licence)
                existe_todo=true;
            else
                existe_todo=false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        request.setAttribute("rq_existeTodo", existe_todo);
        request.setAttribute("rq_existeUsuario", existe_usuario);
        request.setAttribute("rq_existePasswd", existe_passwd);
        request.setAttribute("rq_existeEmailConfirm", existe_email_confirm);
        request.setAttribute("rq_existeStatus", existe_status);
        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userEmail", user_email);
        request.setAttribute("rq_userName", nombre_user);
        request.setAttribute("rq_userPasswd", user_passwd);
        request.setAttribute("rq_userTimeSeccion", user_time_seccion);
        request.setAttribute("rq_pwdVigente", pwd_vigente);
        request.setAttribute("rq_userNotExpires", user_not_expires);
        request.setAttribute("rq_dataUser", data_user);
        request.setAttribute("rq_dataPassword", data_password);
        request.setAttribute("rq_existeLicence", existe_licence);
        request.setAttribute("rq_logoName", logo_name);
        request.setAttribute("rq_logoDirection", logo_direction);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_admins.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
