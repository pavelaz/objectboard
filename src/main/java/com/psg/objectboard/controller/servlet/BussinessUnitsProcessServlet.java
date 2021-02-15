package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.controller.common.ImageResizer;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.service.Other.OtherConexion;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "BussinessUnitsProcessServlet", urlPatterns = "/bussinessunitsprocess")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 10,  // 10 MB
        maxFileSize         = 1024 * 1024 * 100, // 100 MB
        maxRequestSize      = 1024 * 1024 * 150 // 150 MB
)
public class BussinessUnitsProcessServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");
        String company_number = (String) objSesion.getAttribute("companyNumber");

        String acciones = "create";
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }

        //Integer cual_u = null;
        String none= null,
        //        file_name_ant = null,
                file_name = null;
        Part file_imagen = null;

        OtherFunctions of = new OtherFunctions();
        FilesController fc = new FilesController();
        File f = null;

        ArrayList<Integer> cual_unit = new ArrayList<Integer>();
        ArrayList<String> cual_logo = new ArrayList<String>();
        BussinessUnitVO codo = new BussinessUnitVO();
        // Recupero los valores necesarios para la accion
        switch(acciones){
            case "delete":
                Integer num_filas = 0;
                if(request.getParameter("p_cuantos")!=null) {
                    none = request.getParameter("p_cuantos");
                    num_filas = Integer.parseInt(none);
                }
                for(int x=1 ; x < (num_filas + 1);x++){
                    if(request.getParameter("p_selec_"+x)!=null) {
                        none = request.getParameter("p_cual_u"+x);
                        if (cual_unit.isEmpty()){
                            cual_unit.add(0,Integer.parseInt(none));
                        }else{
                            cual_unit.add(Integer.parseInt(none));
                        }
                        none = request.getParameter("p_cual_l"+x);
                        if (cual_logo.isEmpty()){
                            cual_logo.add(0,none);
                        }else{
                            cual_logo.add(none);
                        }
                    }
                }
                break; // break es opcional
            case "create":
            case "save":
                if(request.getParameter("p_unit") != null) {
                    none = request.getParameter("p_unit");
                    if (!none.equals("")){
                        codo.setBuBisCode(Long.parseLong(request.getParameter("p_unit")));
                    }
                }
                if(request.getParameter("p_unit_name")!=null){
                    none = request.getParameter("p_unit_name");
                    codo.setBuName(none);
                }
                if(request.getParameter("p_fed_number")!=null){
                    codo.setBuFederalNumber(request.getParameter("p_fed_number"));
                }
                if(request.getParameter("p_pro_number")!=null){
                    codo.setBuProvincialNumber(request.getParameter("p_pro_number"));
                }
                if(request.getParameter("p_email")!=null) {
                    codo.setBuEmail(request.getParameter("p_email"));
                }
                if(request.getParameter("p_phone")!=null){
                    codo.setBuPhone(request.getParameter("p_phone"));
                }
                if(request.getParameter("p_zipcode")!=null){
                    codo.setBuZipCode(request.getParameter("p_zipcode"));
                }
                if(request.getParameter("p_supercode")!=null){
                    codo.setBuSuperCode(request.getParameter("p_supercode"));
                }
                if(request.getParameter("p_address")!=null) {
                    codo.setBuAddress(request.getParameter("p_address"));
                }
                if(request.getParameter("p_status")!=null){
                    codo.setBuStatus(request.getParameter("p_status"));
                }
                if(request.getParameter("p_type")!=null){
                    codo.setBussinessTypeBtCodeType(Long.valueOf(request.getParameter("p_type")));
                }
                if(request.getParameter("p_webpage")!=null){
                    codo.setBuWebPage(request.getParameter("p_webpage"));
                }
                if(request.getParameter("p_country_number")!=null) {
                    codo.setCityStatesCountryCoCountryCode(Long.valueOf(request.getParameter("p_country_number")));
                }
                if(request.getParameter("p_state_number")!=null){
                    codo.setCityStatesStStateCode(Long.valueOf(request.getParameter("p_state_number")));
                }
                if(request.getParameter("p_city_number")!=null){
                    codo.setCityCiCityCode(Long.valueOf(request.getParameter("p_city_number")));
                }
                if(request.getParameter("p_admincode")!=null){
                    codo.setBuAdminCode(request.getParameter("p_admincode"));
                }
                if(request.getParameter("p_user1code")!=null){
                    codo.setBuUser1Code(request.getParameter("p_user1code"));
                }
                if(request.getParameter("p_user2code")!=null){
                    codo.setBuUser2Code(request.getParameter("p_user2code"));
                }

                if(request.getPart("p_file")!=null){
                    FilesController filesController = new FilesController();
                    file_name = filesController.getNameFile(request.getPart("p_file"));
                    if (file_name.equals("")) {
                        codo.setBuLogoName(of.searchLink("7") );
                        codo.setRuta_imagen(of.searchLink("0") + "img/" + codo.getBuLogoName());
                    }else {
                        codo.setBuLogoName(file_name);
                        file_imagen = request.getPart("p_file");
                        InputStream is = file_imagen.getInputStream();
                        //File f = new File(path + primaryDirectory + codo.getBuLogoName());
                        f = new File(of.searchLink("4") + codo.getBuLogoName());
                        File ff = new File(of.searchLink("4") + "copia_" + codo.getBuLogoName());
                        filesController.subirArchivos(is, ff);
                        ImageResizer imarez = new ImageResizer();
                        imarez.copyImage(of.searchLink("4") + "copia_" + codo.getBuLogoName(),
                                of.searchLink("4") + codo.getBuLogoName(),432,432);
                        filesController.eliminarFichero(ff);
                        codo.setRuta_imagen(of.searchLink("4") + file_name);
                    }
                }else{
                    codo.setBuLogoName(of.searchLink("7") );
                    codo.setRuta_imagen(of.searchLink("0") + "img/" + codo.getBuLogoName());
                }
                break;
            default :
                System.out.println("Accion solicitada no Existe");
                break;
        }
        // Realizo las acciones solicitadas sobe la base de datos
        BussinessUnitDAO cdo = new BussinessUnitDAO();
        OtherConexion ocn = new OtherConexion();
        Connection con = null;
        BussinessUnitDAO.setDataUser(data_user);
        BussinessUnitDAO.setDataPassword(data_pasword);
        con=ocn.conectarse(data_user,data_pasword);

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cdo.insertBussinessUnitDAO(codo,con);
                if (codo.getResult()){
                    if (!codo.getBuLogoName().equals(of.searchLink("7") )) {
                        codo.setResult(fc.eliminarFichero(f));
                    }
                }
            }else{
                if (acciones.equals("delete")){
                    codo.setResult(true);
                    for(int x=0 ; x < cual_unit.size();x++){
                        if (codo.getResult()){
                            System.out.println("Bussiness Unit: " + cual_unit.get(x));
                            codo.setResult(cdo.deleteBussinessUnitDAO(cual_unit.get(x),con));
                        }
                    }
                }else{
                    cdo.updateBussinessUnittDAO(codo,con);
                    if (codo.getResult()){
                        if (!codo.getBuLogoName().equals(of.searchLink("7") )) {
                            cdo.updateBussinessUnitImage(codo, con);
                        }
                    }
                    if (codo.getResult()){
                        if (!codo.getBuLogoName().equals(of.searchLink("7") )) {
                            codo.setResult(fc.eliminarFichero(f));
                        }
                    }
                }
            }
            // valida status de la transacion
            ocn.valida_trans(con,codo.getResult());
            //
            ocn.cierra_coneccion(con);
        }catch (Exception es){
            System.out.println(es.getMessage());
        }

        request.setAttribute("rq_result", codo.getResult());
        request.setAttribute("rq_pantalla", pantalla);

        request.setAttribute("rq_format", cdo.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
