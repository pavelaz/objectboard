package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.batch.App;
import com.psg.objectboard.model.own.ownsEntity.classDAO.*;
import com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.MailUtilDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.*;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.MailSendVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;
import com.psg.objectboard.model.service.Other.OtherInserts;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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

        // crea el objeto de coneccion
        OtherConexion ocn = new OtherConexion();
        //
        //OtherFunctions of = new OtherFunctions();
        OtherInserts oi = new OtherInserts();
        // Inicializa Variable de control de transacion
        muv.setResult(true);
        Properties vProp = new Properties();
        // Crear campos para asignacion
        InputStream vInputStream = null;
        try {
            vInputStream = App.class.getResourceAsStream("/app.properties");
            vProp.load(vInputStream);
        } finally {
            if (vInputStream != null){
               vInputStream.close();
            }
        }
        // mueve valores al objeto
        muv.setCtNombre(name_clte);
        muv.setCtPhone(phone_clte);
        muv.setCtEmail(correo_clte);
        if (message_clte.length()< 160)
            muv.setCtMessage(message_clte);
        else
            muv.setCtMessage(message_clte.substring(0,159));
            muv.setCtDate(fecha);
            MailSendVO corre = new MailSendVO();
            corre.setSMTP_SERVER(vProp.getProperty("propert-smtpserver"));
            corre.setUSERNAME(vProp.getProperty("propert-emailusrname"));
            corre.setPASSWORD(vProp.getProperty("propert-emailpassword"));
            corre.setEMAIL_FROM("serviciospvsoft@gmail.com");
            corre.setEMAIL_TO(muv.getCtEmail().trim());
            // Las direcciones de correo deben ir separadas por coma y luego espacio
            corre.setEMAIL_TO_CC("");
            corre.setEMAIL_TO_BCC("ContactUs@serviciospvsoft.com");
            //
            corre.setEMAIL_SUBJECT("Information request");
            // Busca cuerpo de email confirmacion
            corre.setEMAIL_TEXT(oi.bodyInformationRequest(name_clte, correo_clte, phone_clte, message_clte, fecha));
            corre.setEMAIL_RUTARCH("");
            MailUtilDAO codao = new MailUtilDAO();
            try{
                if (muv.getResult()){
                    // instancio y establesco coneccion
                    con=ocn.conectarse(data_user,data_password);
                    // iniciar transacion
                    ocn.init_trans(con);
                    mud.insertContactusDAO(muv,con);
                    if (muv.getResult()){
                        muv.setResult(codao.sendMail(corre));
                    }
                    // valida status de la transacion
                    ocn.valida_trans(con,muv.getResult());
                    //
                    ocn.cierra_coneccion(con);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
