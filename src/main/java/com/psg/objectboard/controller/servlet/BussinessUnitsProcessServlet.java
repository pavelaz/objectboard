package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.controller.common.FilesController;
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

        String acciones = "create";
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }

        Integer cual_u = null;
        String none= null,
                file_name_ant = null,
                file_name = null;
        Part file_imagen = null;
        String path = System.getProperty("user.home");
        String primaryDirectory = "/IdeaProjects/objectboard/src/main/webapp/complements/temporaryfiles/";
        String secundaryDirectory = "/IdeaProjects/objectboard/src/main/webapp/complements/img/logos/";
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
                for(int f=1 ; f < (num_filas + 1);f++){
                    if(request.getParameter("p_selec_"+f)!=null) {
                        none = request.getParameter("p_cual_u"+f);
                        if (cual_unit.isEmpty()){
                            cual_unit.add(0,Integer.parseInt(none));
                        }else{
                            cual_unit.add(Integer.parseInt(none));
                        }
                        none = request.getParameter("p_cual_l"+f);
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
                if(request.getParameter("p_file_ant")!=null){
                    file_name_ant=(request.getParameter("p_file_ant"));
                }

                /*Start*********************AddPhoto to Object master_user_dto *********/
                /*if (request.getPart("p_file") != null){
                    FilesController filesController = new FilesController();
                    file_dir = filesController.updateFile(request, (1024 * 1024 * 10),"p_file"); // 1024 * 1024 * 1,= 1 MB
                    file_name = filesController.getNameFile(request.getPart("p_file"));
                }*/
                /*End*********************AddPhoto to Object master_user_dto *********/

                if(request.getPart("p_file")!=null){
                    FilesController filesController = new FilesController();
                    file_name = filesController.getNameFile(request.getPart("p_file"));
                    if (file_name.equals(""))
                        codo.setBuLogoName("favicon2.png");
                    else {
                        codo.setBuLogoName(file_name);
                        file_imagen = request.getPart("p_file");
                        InputStream is = file_imagen.getInputStream();
                        File f = new File(path + primaryDirectory + codo.getBuLogoName());
                        OtherFunctions.subirArchivos(is, f);
                    }
                }else{
                    codo.setBuLogoName("favicon2.png");
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
        OtherFunctions of = new OtherFunctions();
        String directorio = null;

        try{
            // iniciar transacion
            ocn.init_trans(con);
            if (acciones.equals("create")){
                cdo.insertBussinessUnitDAO(codo,con);
                String new_company_number = "0";
                if (codo.getResult()){
                    if (!codo.getBuLogoName().equals("favicon2.png")) {
                        none = "bu_federal_number = '" + codo.getBuFederalNumber() + "' AND bu_provincial_number = '" + codo.getBuProvincialNumber() +
                                "' AND bu_super_code = '" + codo.getBuSuperCode() + "' AND bu_admin_code = '" + codo.getBuAdminCode() +
                                "' AND bu_user1_code = '" + codo.getBuUser1Code() + "' AND bu_user2_code = '" + codo.getBuUser2Code() + "'";
                        new_company_number = cdo.getLastBussinessUnitCreate(none, con);
                        if (new_company_number.equals("0"))
                            codo.setResult(false);
                    }
                }
                if (codo.getResult()){
                    if (!codo.getBuLogoName().equals("favicon2.png")) {
                        if (Long.parseLong(new_company_number) < 10)
                            directorio = "0" + Long.parseLong(new_company_number);
                        else
                            directorio = new_company_number;
                        of.CrearDirectorio(path + secundaryDirectory + directorio);
                        of.moverArchivos(path + primaryDirectory, path + secundaryDirectory + directorio + "/", file_name);
                    }
                }
            }else{
                if (acciones.equals("delete")){
                    codo.setResult(true);
                    for(int x=0 ; x < cual_unit.size();x++){
                        if (codo.getResult()){
                            System.out.println("Bussiness Unit: " +cual_unit.get(x));
                            codo.setResult(cdo.deleteBussinessUnitDAO(cual_unit.get(x),con));
                        }
                        if (codo.getResult()) {
                            if (!cual_logo.get(x).equals("favicon2.png")){
                                if (cual_unit.get(x) < 10)
                                    directorio = "0" + cual_unit.get(x);
                                else
                                    directorio = String.valueOf(cual_unit.get(x));
                                File ficheroAnt = new File(path + secundaryDirectory + directorio);
                                // borra carpeta con ficheros internos
                                codo.setResult(of.borrarDirectorio(ficheroAnt));
                            }
                        }
                    }
                }else{
                    cdo.updateBussinessUnittDAO(codo,con);
                    if (codo.getResult()){
                        if (!codo.getBuLogoName().equals("favicon2.png")) {
                            if (codo.getBuBisCode() < 10)
                                directorio = "0" + codo.getBuBisCode();
                            else
                                directorio = "" + codo.getBuBisCode();
                            of.CrearDirectorio(path + secundaryDirectory + directorio);
                            of.moverArchivos(path + primaryDirectory, path + secundaryDirectory + directorio + "/", file_name);
                            if (file_name != file_name_ant && !file_name_ant.equals("favicon2.png")) {
                                File ficheroAnt = new File(path + secundaryDirectory + directorio + "/", file_name_ant);
                                codo.setResult(of.eliminarFichero(ficheroAnt));
                            }
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
