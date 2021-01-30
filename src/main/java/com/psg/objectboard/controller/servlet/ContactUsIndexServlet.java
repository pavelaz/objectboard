package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.batch.App;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ContactusDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.MailUtilDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ContactusVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.MailSendVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherConexion;

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
        Properties vProp = new Properties();
        String texto = null;
        InputStream vInputStream = null;
        DateFunctions df = new DateFunctions();
        try {
            vInputStream = App.class.getResourceAsStream("/app.properties");
            vProp.load(vInputStream);
        } finally {
            if (vInputStream != null){
                vInputStream.close();
            }
        }

        ContactusVO cotavo = new ContactusVO();
        ContactusDAO cotado = new ContactusDAO();
        cotavo.setResult(true);
        String data_user = "root",
               data_password = "01!ObjectBoard*%";
        OtherConexion ocn = new OtherConexion();

        Connection con = null;
        // instancio y establesco coneccion
        con=ocn.conectarse(data_user,data_password);
        // iniciar transacion
        ocn.init_trans(con);

        if (request.getParameter("name") != null &&
            request.getParameter("email") != null &&
            request.getParameter("phone") != null &&
            request.getParameter("message") != null) {

            // Envio de Correo de verificacion
            MailSendVO corre = new MailSendVO();

            corre.setSMTP_SERVER(vProp.getProperty("propert-smtpserver"));
            corre.setUSERNAME(vProp.getProperty("propert-emailusrname"));
            corre.setPASSWORD(vProp.getProperty("propert-emailpassword"));
            corre.setEMAIL_FROM(request.getParameter("Index page"));
            corre.setEMAIL_TO("serviciospvsoft@gmail.com");
            // Las direcciones de correo deben ir separadas por coma y luego espacio
            corre.setEMAIL_TO_CC("");
            corre.setEMAIL_TO_BCC("");
            //
            corre.setEMAIL_SUBJECT("Email verification account registration");
            // Busca cuerpo de email confirmacion
            corre.setEMAIL_TEXT(request.getParameter("name") +
                                request.getParameter("phone") +
                                request.getParameter("message"));

            corre.setEMAIL_RUTARCH("");
            // Si funciona el envio de adjuntos

            MailUtilDAO codao = new MailUtilDAO();

            try {
                cotavo.setResult(codao.sendMail(corre));
                texto = "Data to send..";
                System.out.println(texto);

            } catch (Exception e) {
                System.out.println(texto + " " + e);
                texto = "Data not send..";
            }
        }
        request.setAttribute("rq_responseEmail", texto);

        if (cotavo.getResult()){
            String none = request.getParameter("name");
            cotavo.setCtNombre(none);
            none = request.getParameter("phone");
            cotavo.setCtPhone(none);
            none = request.getParameter("message");
            cotavo.setCtMessage(none);
            String fecha = df.fechaFull(9);
            cotavo.setCtDate(fecha);
            cotado.insertContactusDAO(cotavo,con);
        }

        // valida status de la transacion
        ocn.valida_trans(con,cotavo.getResult());
        //
        ocn.cierra_coneccion(con);

        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
