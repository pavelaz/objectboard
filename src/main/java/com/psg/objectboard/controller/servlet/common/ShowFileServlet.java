package com.psg.objectboard.controller.servlet.common;

import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BodyConductSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BodyConductSurveyVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ShowFileServlet", urlPatterns = "/showfile.html")
public class ShowFileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        //  si es png, jpeg, etc...
        String forma = null;
        if (request.getParameter("p_forma") != null) {
            forma = request.getParameter("p_forma");
        }

        //  unidad de negocio
        String unidad = null;
        if (request.getParameter("p_unit") != null) {
            unidad = request.getParameter("p_unit");
        }
        // correo del usuario a buscar en masteruser
        String correo = null;
        if (request.getParameter("p_email") != null) {
            correo = request.getParameter("p_email");
        }

        // numero del archivo en el que se buscara la imagen
        String archivo = null;
        if (request.getParameter("p_archivo") != null) {
            archivo = request.getParameter("p_archivo");
        }

        // archivo = null  --> Archivo: MasterUser no dirigido
        if (unidad == null && correo == null && archivo == null){
            String bub = null;
            String mu_email = null;

            //HttpSession objSesion = request.getSession();
            bub = (String) objSesion.getAttribute("companyNumber");
            mu_email = (String) objSesion.getAttribute("userEmail");

            if (bub != null && mu_email != null){

                FilesController fco = new FilesController();
                MasterUserDAO mud = new MasterUserDAO();
                MasterUserVO muv = mud.serchMasterUserDAO(mu_email,bub);

                ServletOutputStream outputStream = null;
                outputStream = fco.blobToDifferentFormats(response,muv.getMuPhotoByte(),"image/png");

                System.out.println("Foto puesta en HTML");
            }
        }

        // archivo = null  --> Archivo: MasterUser dirigido
        if (unidad != null && correo != null){

            MasterUserDAO photoDto = new MasterUserDAO();
            photoDto.setDataUser(data_user);
            photoDto.setDataPassword(data_pasword);

            MasterUserVO photoVO = photoDto.serchMasterUserDAO(correo, unidad);

            FilesController filesController = new FilesController();

            ServletOutputStream outputStream = null;
            String presenta = "image/" + forma;
            outputStream = filesController.blobToDifferentFormats(response,photoVO.getMuPhotoByte(),presenta);

            System.out.println("Foto puesta en HTML");
        }

        // archivo = "1"  --> Archivo: Bussiness unit
        if (archivo.equals("1")){

            BussinessUnitDAO photoDto = new BussinessUnitDAO();
            photoDto.setDataUser(data_user);
            photoDto.setDataPassword(data_pasword);

            BussinessUnitVO photoVO = photoDto.serchBussinessUnitDAO(unidad);

            FilesController filesController = new FilesController();

            ServletOutputStream outputStream = null;

            String presenta = "image/" + forma;
            outputStream = filesController.blobToDifferentFormats(response,photoVO.getBuLogoImageByte(),presenta);

            System.out.println("Logo puesto en HTML");
        }

        // archivo = "2"  --> Archivo: bodyConductSurvey
        if (archivo.equals("2")){
            String conduct = null;
            if (request.getParameter("p_conduct") != null) {
                conduct = request.getParameter("p_conduct");
            }
            String question = null;
            if (request.getParameter("p_question") != null) {
                question = request.getParameter("p_question");
            }
            String survey = null;
            if (request.getParameter("p_survey") != null) {
                survey = request.getParameter("p_survey");
            }

            BodyConductSurveyDAO photoDto = new BodyConductSurveyDAO();
            photoDto.setDataUser(data_user);
            photoDto.setDataPassword(data_pasword);

            BodyConductSurveyVO photoVO = photoDto.serchBodyConductSurveyDAO(unidad,conduct,question,survey);

            FilesController filesController = new FilesController();

            ServletOutputStream outputStream = null;
            OtherFunctions of = new OtherFunctions();
            forma = of.buscaExtencionFiles(photoVO.getBcsNameAnnexFile(),"2");
            String presenta = "image/" + forma;
            outputStream = filesController.blobToDifferentFormats(response,photoVO.getBcsAnnexFileByte(),presenta);

            System.out.println("Logo puesto en HTML");
        }
    }

}
