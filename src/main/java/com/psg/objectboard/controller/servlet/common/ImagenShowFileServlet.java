package com.psg.objectboard.controller.servlet.common;

import com.psg.objectboard.controller.MasterUserProfileController;
import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.model.datatransferobject.PhotoDto;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ImagenShowFileServlet", urlPatterns = "/imagenshowfile.html")
public class ImagenShowFileServlet extends HttpServlet {

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

        // archivo Bussiness unit
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
    }

}
