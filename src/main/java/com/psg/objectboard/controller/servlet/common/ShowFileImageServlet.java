package com.psg.objectboard.controller.servlet.common;

import com.psg.objectboard.controller.MasterUserProfileController;
import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.model.datatransferobject.PhotoDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ShowFileImageServlet", urlPatterns = "/showfileimage.html")
public class ShowFileImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bussiness_unit_busBisCode = null;
        String mu_email = null;

            HttpSession objSesion = request.getSession();
            bussiness_unit_busBisCode = (String) objSesion.getAttribute("companyNumber");
            mu_email = (String) objSesion.getAttribute("userEmail");

        if (bussiness_unit_busBisCode != null && mu_email != null){

            MasterUserProfileController controller = new MasterUserProfileController();
            PhotoDto photoDto = controller.getShowPhoto(Long.parseLong(bussiness_unit_busBisCode), mu_email);

            FilesController filesController = new FilesController();

            ServletOutputStream outputStream = null;
            outputStream = filesController.blobToDifferentFormats(response,photoDto.getMuPhoto(),"image/png");

            System.out.println("Foto puesta en HTML");
        }
    }

}
