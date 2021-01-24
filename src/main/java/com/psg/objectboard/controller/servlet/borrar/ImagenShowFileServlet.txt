package com.psg.objectboard.controller.servlet.borrar;

import com.psg.objectboard.controller.common.FilesController;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ImagenShowFileServlet", urlPatterns = "/imagenshowfile.html")
public class ImagenShowFileServlet_txt extends HttpServlet {
  /*  private String format;
    private Blob photoDto;

    public void setPhotoDto(Blob photoDto) {
        this.photoDto = photoDto;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public Blob getPhotoDto() {
        return photoDto;
    }*/

    /*public byte[] pasarBlobToByte(Blob blob) throws SQLException {
        int blobLength = (int) blob.length();
        byte[] blobAsBytes = blob.getBytes(1, blobLength);
        blob.free();
        return blobAsBytes;
    }*/

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //(assuming you have a ResultSet named RS)
        String forma = null;
        if (request.getParameter("p_forma") != null) {
            forma = request.getParameter("p_forma");
        }
        // Blob photo = null;
        byte[] buff = null;
        String photo = null;
        if (request.getParameter("p_img") != null){
            photo = request.getParameter("p_img");
            String blobString= photo;
            buff = blobString.getBytes();
        }

        //imagine u have a a prepared statement like:
        //PreparedStatement ps = conn.prepareStatement("INSERT INTO table VALUES (?)");
        //String blobString= none;
        //Oracle.sql.BLOB myBlob = Oracle.sql.BLOB.createTemporary(conn, false,Oracle.sql.BLOB.DURATION_SESSION);
        //Blob myBlob = null;
       // byte[] buff = blobString.getBytes();
        //myBlob.putBytes(1,buff);
        //ps.setBlob(1, myBlob);
        //ps.executeUpdate();

        //String bussiness_unit_busBisCode = null;
        //String mu_email = null;

        /*if (user_email!=null && user_unit !=null){
            bussiness_unit_busBisCode = user_unit;
            mu_email = user_email;
        }else{*/
            //HttpSession objSesion = request.getSession();
            //bussiness_unit_busBisCode = (String) objSesion.getAttribute("companyNumber");
            //mu_email = (String) objSesion.getAttribute("userEmail");
       // }

        //if (unidad != null && correo != null){

            //MasterUserProfileController controller = new MasterUserProfileController();
           // PhotoDto photoDto = controller.getShowPhoto(Long.parseLong(bussiness_unit_busBisCode), mu_email);
            String presenta = "image/" + forma;
            FilesController filesController = new FilesController();

            ServletOutputStream outputStream = null;

            outputStream = filesController.blobToDifferentFormats(response,buff,presenta);

        System.out.println("Foto puesta en HTML");
       // }
    }

}
