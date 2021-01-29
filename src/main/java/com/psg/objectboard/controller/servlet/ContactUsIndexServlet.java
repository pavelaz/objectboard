package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.batch.App;
import com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.MailUtilDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.MailSendVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@WebServlet(name = "ContactUsIndexServlet", urlPatterns = "/contactusindex")
public class ContactUsIndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lee los valores  que vienen por el post
        Properties vProp = new Properties();
        String texto = null;
        InputStream vInputStream = null;
        try {
            vInputStream = App.class.getResourceAsStream("/app.properties");
            vProp.load(vInputStream);
        } finally {
            if (vInputStream != null){
                vInputStream.close();
            }
        }
        if (request.getParameter("name") != null &&  request.getParameter("email") != null
            && request.getParameter("phone") != null && request.getParameter("message") != null) {

            // Envio de Correo de verificacion
            MailSendVO corre = new MailSendVO();

            corre.setSMTP_SERVER(vProp.getProperty("propert-smtpserver"));
            corre.setUSERNAME(vProp.getProperty("propert-emailusrname"));
            corre.setPASSWORD(vProp.getProperty("propert-emailpassword"));
            corre.setEMAIL_FROM(request.getParameter("email"));
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
            //corre.setEMAIL_RUTARCH(path + "imagen_perfil.png");

            MailUtilDAO codao = new MailUtilDAO();

            try {
                //codao.sendMail(corre);
                texto = "Data to send..";
                System.out.println(texto);

            } catch (Exception e) {
                System.out.println(texto + " " + e);
                texto = "Data not send..";
            }
        }
        request.setAttribute("rq_responseEmail", texto);

        request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
