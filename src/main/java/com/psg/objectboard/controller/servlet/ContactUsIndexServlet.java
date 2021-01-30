package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.batch.App;
import com.psg.objectboard.model.own.ownsEntity.classDAO.*;
import com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.MailUtilDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.*;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.MailSendVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "ContactUsIndexServlet", urlPatterns = "/contactusindex")
public class ContactUsIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lee los valores  que vienen por el post
        String correo_clte=null;
        if(request.getParameter("p_email")!=null){
            correo_clte=request.getParameter("p_email");
        }
        String phone_clte=null;
        if(request.getParameter("p_phone")!=null){
            phone_clte=request.getParameter("p_phone");
        }
        String name_clte=null;
        if(request.getParameter("p_name")!=null){
            name_clte=request.getParameter("p_name");
        }
        String message_clte=null;
        if(request.getParameter("p_name")!=null){
            message_clte=request.getParameter("p_message");
        }

        DateFunctions df = new DateFunctions();
        String fecha = df.fechaFull(9);

        String data_user = "root",
               data_password = "01!ObjectBoard*%";

        ContactusVO muv = new ContactusVO();
        ContactusDAO mud = new ContactusDAO();
        Connection con = null;
        /*Boolean result = null,
                existe_usuario=null,
                crea_todo=null,
                crea_usuario=false,
                crea_email=false,
                crea_roles=false,
                crea_usr_sql=false;
        mud.setDataUser(data_user);
        mud.setDataPassword(data_password);*/

        try {
            /*muv = mud.serchMasterUserDAO(user_email,company_number);
            existe_usuario= muv.getResult();
            if (!muv.getResult())
                result = true;
            else
                result = false;*/
            // crea el objeto de coneccion
            OtherConexion ocn = new OtherConexion();
            // valida si exise el usuario previamente
            OtherFunctions of = new OtherFunctions();
            //DateFunctions df = new DateFunctions();
            // Crea usuario en tablas de base de datos
            // Inicializa Variable de control de transacion
            muv.setResult(true);
            Properties vProp = new Properties();
            if(muv.getResult()){
                // obtengo decha del dia en formato string
                //String fecha = null;
                //fecha = df.fechaFull(9);
                // obtengo la fecha del dia en formato Date y luego obtengo la fecha de desactivacion
                //Date fecha1 = new Date();
                //Date fe = df.sumarRestarDiasFecha(fecha1,90);
                //String DATE_FORMAT = "yyyy/MM/dd";
                //SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                //String fe_venc = sdf.format(fe)+" " +fecha.substring(11,19);
                // obtengo numero aleatorio de licencia.
                //String rand;
                //rand=of.generateRandomString(45);
                // instancio y establesco coneccion
                con=ocn.conectarse(data_user,data_password);
                // iniciar transacion
                //ocn.init_trans(con);
                // Crear campos para asignacion
                //String falso = "F",ruta=null,verdadero="T";
                InputStream vInputStream = null;
                try {
                    vInputStream = App.class.getResourceAsStream("/app.properties");
                    vProp.load(vInputStream);
                } finally {
                    if (vInputStream != null){
                        vInputStream.close();
                    }
                }
                /*(Production)*/
                /*String path = vProp.getProperty("propert-pathcomplements");

                if(user_sexo.equals("M")) {
                    ruta = path + "img/photoProfileMen2.png";
                    muv.setMuPhotoName("photoProfileMen2.png");
                }else {
                    ruta = path + "img/photoProfileWomen.png";
                    muv.setMuPhotoName("photoProfileWomen.png");
                }*/
                // mueve valores al objeto
                muv.setCtNombre(name_clte);
                muv.setCtPhone(phone_clte);
                muv.setCtEmail(correo_clte);
                if (message_clte.length()< 160)
                    muv.setCtMessage(message_clte);
                else
                    muv.setCtMessage(message_clte.substring(0,159));
                muv.setCtDate(fecha);
                /*muv.setMuEmail(user_email);
                muv.setBussinessUnitBuBisCode(Integer.parseInt(company_number));
                muv.setMuPassword(user_passwd);
                muv.setMuPasswordOld(user_passwd);
                muv.setMuName(user_name);
                if(muv.getBussinessUnitBuBisCode()==1)
                    muv.setMuSectionTime(600);
                else
                    muv.setMuSectionTime(1200);
                muv.setMuQuestion(user_question);
                muv.setMuAnswer(user_answer);
                muv.setMuStartDate(fecha);
                muv.setMuStatus(falso);
                muv.setMuDate(fecha);
                muv.setMuEffectiveDays(90);
                muv.setMuConfirmCode(rand);
                muv.setMuEmailConfirm(falso);
                muv.setMuDateResetPwd(fecha);
                muv.setMuGender(user_sexo);
                muv.setRuta_imagen(ruta); // incluye la foto en mapa de bit en atributo de bd

                muv.setMuDateExpires(fe_venc);
                muv.setMuExpires(verdadero);
                //muv.setMuDataUser("boarduser");
                muv.setMuDataUser(of.generateRandomString(8));
                muv.setMuDataPassword(of.generateRandomComplex(16));

                muv.setCityStatesCountryCoCountryCode(Integer.parseInt(country_number));
                muv.setCityStatesStStateCode(Integer.parseInt(state_number));
                muv.setCityCiCityCode(Integer.parseInt(city_number));*/

                //  hace el insert del usuario
                mud.insertContactusDAO(muv,con);
                //crea_usuario = muv.getResult();
                //result = muv.getResult();
            }
            /*else{
                System.out.println(" Usuario Existe Previamente");
                result = false;
                muv.setResult(false);
            }*/
            // Envio de Correo de verificacion
            if(muv.getResult()){
                MailSendVO corre = new MailSendVO();
                //corre.setSMTP_SERVER("smtp.gmail.com");
                //corre.setUSERNAME("serviciospvsoft");
                //corre.setPASSWORD("Solitariop1");
                corre.setSMTP_SERVER(vProp.getProperty("propert-smtpserver"));
                corre.setUSERNAME(vProp.getProperty("propert-emailusrname"));
                corre.setPASSWORD(vProp.getProperty("propert-emailpassword"));
                corre.setEMAIL_FROM("serviciospvsoft@gmail.com");
                corre.setEMAIL_TO(muv.getCtEmail().trim());
                // Las direcciones de correo deben ir separadas por coma y luego espacio
                corre.setEMAIL_TO_CC("ContactUs@serviciospvsoft.com");
                corre.setEMAIL_TO_BCC("");
                //
                corre.setEMAIL_SUBJECT("Information request");
                // Busca cuerpo de email confirmacion
                // corre.setEMAIL_TEXT("Hello Java Mail \n ABC123");
                corre.setEMAIL_TEXT(of.bodyInformationRequest(name_clte, correo_clte, phone_clte, message_clte, fecha));
                corre.setEMAIL_RUTARCH("");
                // Si funciona el envio de adjuntos
                //corre.setEMAIL_RUTARCH(path + "imagen_perfil.png");

                MailUtilDAO codao = new MailUtilDAO();
                muv.setResult(codao.sendMail(corre));
                //crea_email = muv.getResult();
                //result = muv.getResult();
            }
            // Crea los roles del usuario para los modulos activos de la empresa
            /*if( result){
                // Busca el super code de la unidad de negocios en cuestion
                BussinessUnitVO buvo = new BussinessUnitVO();
                BussinessUnitDAO buda = null;
                buda.setDataUser(data_user);
                buda.setDataPassword(data_password);
                buvo = buda.serchBussinessUnitDAO(company_number);
                // Busca licencias Activas
                ArrayList<DischargeVO> consulta = new ArrayList<DischargeVO>();
                DischargeDAO dida = new DischargeDAO();
                dida.setDataUser(data_user);
                dida.setDataPassword(data_password);
                String condicion = "bussinessUnit_bu_bis_code = "+Integer.parseInt(company_number);
                // condicion = condicion + " and project_pr_id_project = 1";
                consulta = dida.getListDischargeActive(condicion);
                if (consulta.size()!= 0){
                    UserRoleVO urv = new UserRoleVO();
                    UserRoleDAO urd = new UserRoleDAO();
                    urd.setDataUser(data_user);
                    urd.setDataPassword(data_password);
                    urv.setResult(true);
                    for(int x = 0; x <= consulta.size() -1; x++){
                        if (urv.getResult()) {
                            urv = new UserRoleVO();
                            urv.setMasterUserMuEmail(muv.getMuEmail());
                            urv.setMasterUserBussinessUnitBuBisCode(Integer.parseInt(company_number));
                            urv.setProjectPrIdProject(consulta.get(x).getProjectPrIdProject());
                            if (buvo.getBuSuperCode().equals(super_code)) {
                                urv.setUmStatus("T");
                            } else {
                                urv.setUmStatus("F");
                            }
                            urv.setUmRole(Integer.valueOf(role_code));
                            //inserta el perfil
                            urd.insertUserRolesDAO(urv,con);
                            crea_roles = urv.getResult();
                            result = urv.getResult();
                        }else{
                            muv.setResult(urv.getResult());
                            result = urv.getResult();
                        }
                    }
                }
            }
            // Crea usuario MySql
            if( result ) {
                crea_usr_sql = ocn.usersCreations(con, muv.getMuDataUser(), muv.getMuDataPassword());
                muv.setResult(crea_usr_sql);
                result = crea_usr_sql;
            }*/
            // valida status de la transacion
            ocn.valida_trans(con,muv.getResult());
            //
            ocn.cierra_coneccion(con);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

       /* if(crea_email && crea_usuario && crea_roles && crea_usr_sql)
            crea_todo = true;
        else
            crea_todo = false;

        request.setAttribute("rq_creaUsuario", crea_usuario);
        request.setAttribute("rq_creaEmail", crea_email);
        request.setAttribute("rq_creaRoles", crea_roles);
        request.setAttribute("rq_creaUsrSql", crea_usr_sql);
        request.setAttribute("rq_creaTodo", crea_todo);

        request.setAttribute("rq_existeUsuario", existe_usuario);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);*/

        //request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
