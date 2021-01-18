package com.psg.objectboard.controller.servlet.common;

import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ShowFileImageServlet", urlPatterns = "/showfileimage.html")
public class ShowFileImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bub = null;
        String mu_email = null;

        HttpSession objSesion = request.getSession();
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

}