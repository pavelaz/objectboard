package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.OtherDAO.MailUtilDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.OtherVO.MailSendVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ForgotProcessServlet", urlPatterns = "/forgotprocess")
public class ForgotProcessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // valores  ue vienen por el post
        String company_number=null;
        if(request.getParameter("p_company_number")!=null){
            company_number=request.getParameter("p_company_number");
        }
        String company_name=null;
        if(request.getParameter("p_company_name")!=null){
            company_name=request.getParameter("p_company_name");
        }
        String user_email=null;
        if(request.getParameter("p_email")!=null){
            user_email=request.getParameter("p_email");
        }
        String data_user = "boarduser",
               data_password = "1#Object5Board*%";

        MasterUserVO muv = null;
        MasterUserDAO mud = new MasterUserDAO();
        mud.setDataUser(data_user);
        mud.setDataPassword(data_password);
        Boolean existe_usuario=null;

        try {
            OtherFunctions of = new OtherFunctions();
            muv = mud.serchMasterUserDAO(user_email,company_number);
            // Usuario existe o no registrado en la Unidad de negocios.
            existe_usuario= muv.getResult();

            // Envio de Correo de verificacion
            if(muv.getResult()){
                MailSendVO corre = new MailSendVO();
                corre.setSMTP_SERVER("smtp.gmail.com");
                corre.setUSERNAME("serviciospvsoft");
                corre.setPASSWORD("Solitariop1");
                corre.setEMAIL_FROM("serviciospvsoft@gmail.com");
                corre.setEMAIL_TO(muv.getMuEmail().trim());
                // Las direcciones de correo deben ir separadas por coma y luego espacio
                corre.setEMAIL_TO_CC("");
                corre.setEMAIL_TO_BCC("");
                //
                corre.setEMAIL_SUBJECT("Email to reset the password");
                // Busca cuerpo de email confirmacion
                // corre.setEMAIL_TEXT("Hello Java Mail \n ABC123");
                corre.setEMAIL_TEXT(of.bodyResetPassword(muv,company_name));
                //public String bodyResetPassword(MasterUserVO muv,String company)
                corre.setEMAIL_RUTARCH("");
                // Si funciona el envio de adjuntos
                //corre.setEMAIL_RUTARCH("/home/pavelaz/IdeaProjects/objectboard/src/main/webapp/complements/img/imagen_perfil.png");

                MailUtilDAO codao = new MailUtilDAO();
                muv.setResult(codao.sendMail(corre));
            }

            // crea el objeto de coneccion
            OtherConexion ocn = new OtherConexion();
            Connection con = null;
            // instancio y establesco coneccion
            con=ocn.conectarse(data_user,data_password);
            // iniciar transacion
            ocn.init_trans(con);

            if (muv.getResult()){
                mud.updateIniciaResetDAO(muv,con);
            }
            // valida status de la transacion
            ocn.valida_trans(con,muv.getResult());
            //
            ocn.cierra_coneccion(con);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        request.setAttribute("rq_existeUsuario", existe_usuario);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber", company_number);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/forgot_process.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
