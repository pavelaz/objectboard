package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.controller.common.ImageResizer;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.MasterUserDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.MasterUserVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
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

@WebServlet (name="UserProfileServlet", urlPatterns = "/userprofile")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 10,  // 10 MB
        maxFileSize         = 1024 * 1024 * 100, // 100 MB
        maxRequestSize      = 1024 * 1024 * 150 // 150 MB
)
public class UserProfileServlet extends HttpServlet {

    public UserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String) objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String acciones = null;
        if (request.getParameter("p_acciones") != null) {
            acciones = request.getParameter("p_acciones");
        }
        String user_email = null;
        if (request.getParameter("p_email") != null) {
            user_email = request.getParameter("p_email");
        }
        String number_company = null;
        if (request.getParameter("p_unit") != null) {
            number_company = request.getParameter("p_unit");
        }
        String fecha_venc = null;
        if (request.getParameter("p_datexpires") != null) {
            fecha_venc = request.getParameter("p_datexpires");
        }
        Integer method = 0;
        if (request.getParameter("p_method") != null) {
            method = Integer.parseInt(request.getParameter("p_method"));
        }
        String viene = "L";
        if (request.getParameter("p_viene") != null) {
            viene = request.getParameter("p_viene");
        }
        OtherFunctions of = new OtherFunctions();

        if (method == 1) {
            MasterUserDAO userdao = new MasterUserDAO();
            userdao.setDataUser(data_user);
            userdao.setDataPassword(data_pasword);
            MasterUserVO master_user_dto = new MasterUserVO();
            DateFunctions df = new DateFunctions();

            // guarda
            String fech = request.getParameter("p_datexpires");
            String fecha = df.parseFecha_1(fech);
            master_user_dto.setBussinessUnitBuBisCode(Long.parseLong(number_company));
            master_user_dto.setMuEmail(user_email);
            master_user_dto.setMuName(request.getParameter("p_muName"));
            master_user_dto.setMuPassword(request.getParameter("p_muRepeat"));
            master_user_dto.setMuQuestion(request.getParameter("p_muQuestion"));
            master_user_dto.setMuAnswer(request.getParameter("p_muAnswer"));
            master_user_dto.setMuSectionTime(Integer.parseInt(request.getParameter("p_muSectionTime")));
            master_user_dto.setMuStatus(request.getParameter("p_muStatus"));
            master_user_dto.setMuEffectiveDays(Integer.parseInt(request.getParameter("p_muEffectiveDays")));
            master_user_dto.setMuEmailConfirm(request.getParameter("p_muEmailConfirm"));
            master_user_dto.setMuGender(request.getParameter("p_muGender"));

            master_user_dto.setMuExpires(request.getParameter("p_muexpires"));
            master_user_dto.setMuDateExpires(fecha+" "+request.getParameter("p_hora"));
            master_user_dto.setCityCiCityCode(Integer.parseInt(request.getParameter("p_country_number")));
            master_user_dto.setCityStatesStStateCode(Integer.parseInt(request.getParameter("p_state_number")));
            master_user_dto.setCityStatesCountryCoCountryCode(Integer.parseInt(request.getParameter("p_city_number")));

            /*Start*********************AddPhoto to Object master_user_dto *********/
            try{
                OtherConexion ocn = new OtherConexion();
                Connection con = null;
                con=ocn.conectarse(data_user,data_pasword);
                // iniciar transacion
                ocn.init_trans(con);

                userdao.updateMasterUserDAO(master_user_dto,con);

                // valida status de la transacion
                ocn.valida_trans(con,master_user_dto.getResult());
                //
                ocn.cierra_coneccion(con);

                request.setAttribute("rq_result", master_user_dto.getResult());
                request.setAttribute("rq_pantalla", of.buscaRetornoUserProfile(company_number,viene));
                request.setAttribute("rq_companyNumber", company_number);
                request.setAttribute("rq_viene", viene);

                BussinessUnitDAO bud = new BussinessUnitDAO();
                request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

                request.getRequestDispatcher("WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);

            }catch (Exception es){
                System.out.println(es.getMessage());
            }
        }else{
            String none = null;
            if (acciones.equals("save")) {
                if (number_company != null) {
                    MasterUserDAO userdao = new MasterUserDAO();
                    userdao.setDataUser(data_user);
                    userdao.setDataPassword(data_pasword);
                    MasterUserVO masterUserDto = userdao.serchMasterUserDAO(user_email, number_company);
                    String hora = masterUserDto.getMuDateExpires().substring(11, 19);
                    if (fecha_venc == null) {
                        String fecha = masterUserDto.getMuDateExpires().substring(5, 7) + "/" +
                                masterUserDto.getMuDateExpires().substring(8, 10) + "/" +
                                masterUserDto.getMuDateExpires().substring(0, 4);
                        request.setAttribute("rq_fecha", fecha);
                    } else
                        request.setAttribute("rq_fecha", fecha_venc);

                    request.setAttribute("rq_masterUserDto", masterUserDto);

                    request.setAttribute("rq_userName", user_name);
                    request.setAttribute("rq_acciones", acciones);
                    request.setAttribute("rq_hora", hora);
                    none =  "WEB-INF/pages/jsp/master/userProfile.jsp" +
                            "?p_country_number=" + masterUserDto.getCityStatesCountryCoCountryCode() +
                            "&p_state_number=" + masterUserDto.getCityStatesStStateCode() +
                            "&p_city_number=" + masterUserDto.getCityCiCityCode();
                }
                request.setAttribute("rq_companyName", company_name);
                request.setAttribute("rq_companyNumber", company_number);
                request.setAttribute("rq_viene", viene);

                BussinessUnitDAO bud = new BussinessUnitDAO();
                request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

                if (request.getParameter("p_country_number") == null &&
                    request.getParameter("p_state_number") == null &&
                    request.getParameter("p_city_number") == null) {
                    request.getRequestDispatcher(none).forward(request, response);
                }else{
                    request.getRequestDispatcher("WEB-INF/pages/jsp/master/userProfile.jsp").forward(request, response);
                }

            }else{
                Integer num_filas = 0;
                ArrayList<Integer> cual_unit = new ArrayList<Integer>();
                ArrayList<String> cual_email = new ArrayList<String>();
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
                        //
                        none = request.getParameter("p_cual_e"+f);
                        if (cual_email.isEmpty()){
                            cual_email.add(0,none);
                        }else{
                            cual_email.add(none);
                        }
                    }
                }

                MasterUserVO cvo = new MasterUserVO();
                MasterUserDAO cdo = null;
                OtherConexion ocn = new OtherConexion();
                Connection con = null;

                cvo.setResult(true);
                try{
                    cdo.setDataUser(data_user);
                    cdo.setDataPassword(data_pasword);
                    con=ocn.conectarse(data_user,data_pasword);
                    // iniciar transacion
                    ocn.init_trans(con);

                    for(int x=0 ; x < cual_unit.size();x++){
                        if (cvo.getResult()){
                            System.out.println("Unidad: "+cual_unit.get(x)+ " Email: "+cual_email.get(x));
                            cvo.setResult(cdo.deleteMasterUserDAO(cual_unit.get(x),cual_email.get(x),con));
                        }
                    }
                    // valida status de la transacion
                    ocn.valida_trans(con,cvo.getResult());
                    //
                    ocn.cierra_coneccion(con);

                    request.setAttribute("rq_companyNumber", company_number);
                    request.setAttribute("rq_result", cvo.getResult());
                    request.setAttribute("rq_pantalla", of.buscaRetornoUserProfile(company_number,viene));
                    request.setAttribute("rq_viene", viene);

                    BussinessUnitDAO bud = new BussinessUnitDAO();
                    request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

                    request.getRequestDispatcher("WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);

                }catch (Exception es) {
                    System.out.println(es.getMessage());
                }
            }
        }
    }

    @Override
    public void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession objSesion = request.getSession();
        String company_number = (String) objSesion.getAttribute("companyNumber");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String user_email = null;
        if (request.getParameter("p_email") != null) {
            user_email = request.getParameter("p_email");
        }
        String number_company = null;
        if (request.getParameter("p_unit") != null) {
            number_company = request.getParameter("p_unit");
        }
        String viene = "L";
        if (request.getParameter("p_viene") != null) {
            viene = request.getParameter("p_viene");
        }

        MasterUserDAO userdao = new MasterUserDAO();
        userdao.setDataUser(data_user);
        userdao.setDataPassword(data_pasword);
        MasterUserVO master_user_dto = new MasterUserVO();
        DateFunctions df = new DateFunctions();
        // guarda
        String fech = request.getParameter("p_datexpires");
        String fecha = df.parseFecha_1(fech);
        master_user_dto.setBussinessUnitBuBisCode(Long.parseLong(number_company));
        master_user_dto.setMuEmail(user_email);
        String none = request.getParameter("p_muName");
        master_user_dto.setMuName(none);
        none = request.getParameter("p_muRepeat");
        master_user_dto.setMuPassword(none);
        none = request.getParameter("p_muQuestion");
        master_user_dto.setMuQuestion(none);
        none = request.getParameter("p_muAnswer");
        master_user_dto.setMuAnswer(none);
        none = request.getParameter("p_muSectionTime");
        master_user_dto.setMuSectionTime(Integer.parseInt(none));
        none = request.getParameter("p_muStatus");
        master_user_dto.setMuStatus(none);
        none = request.getParameter("p_muEffectiveDays");
        master_user_dto.setMuEffectiveDays(Integer.parseInt(none));
        none = request.getParameter("p_muEmailConfirm");
        master_user_dto.setMuEmailConfirm(none);
        none = request.getParameter("p_muGender");
        master_user_dto.setMuGender(none);
        none = request.getParameter("p_muexpires");
        master_user_dto.setMuExpires(none);
        none = fecha + " " + request.getParameter("p_hora");
        master_user_dto.setMuDateExpires(none);
        none = request.getParameter("p_country_number");
        master_user_dto.setCityCiCityCode(Integer.parseInt(none));
        none = request.getParameter("p_state_number");
        master_user_dto.setCityStatesStStateCode(Integer.parseInt(none));
        none = request.getParameter("p_city_number");
        master_user_dto.setCityStatesCountryCoCountryCode(Integer.parseInt(none));

        /*End*********************AddPhoto to Object master_user_dto *********/
        Part file_imagen = null;
        String file_name = "";
        OtherFunctions of = new OtherFunctions();
        File f = null;
        FilesController filesController = new FilesController();

        if(request.getPart("p_file")!=null){
            file_name = filesController.getNameFile(request.getPart("p_file"));
            if (file_name.equals("")) {
                master_user_dto.setMuPhotoName("");
            }else {
                master_user_dto.setMuPhotoName(file_name);
                file_imagen = request.getPart("p_file");
                InputStream is = file_imagen.getInputStream();
                f = new File(of.searchLink("4") + master_user_dto.getMuPhotoName());
                File ff = new File(of.searchLink("4") + "copia_" + master_user_dto.getMuPhotoName());
                OtherFunctions.subirArchivos(is, ff);
                ImageResizer imarez = new ImageResizer();
                imarez.copyImage(of.searchLink("4") + "copia_" + master_user_dto.getMuPhotoName(),
                        of.searchLink("4") + master_user_dto.getMuPhotoName(),499,498);
                of.eliminarFichero(ff);
                master_user_dto.setRuta_imagen(of.searchLink("4") + file_name);
            }
        }

            OtherConexion ocn = new OtherConexion();
            Connection con = null;
            con = ocn.conectarse(data_user, data_pasword);
            // iniciar transacion
            ocn.init_trans(con);

            userdao.updateMasterUserDAO(master_user_dto, con);

            if (master_user_dto.getResult()){
                if (!master_user_dto.getMuPhotoName().equals("")){
                    userdao.updateMasterUserImage(master_user_dto, con);
                    filesController.deleteFile(of.searchLink("4") + master_user_dto.getMuPhotoName());
                }
            }

            // valida status de la transacion
            ocn.valida_trans(con, master_user_dto.getResult());
            //
            ocn.cierra_coneccion(con);

            request.setAttribute("rq_result", master_user_dto.getResult());
            request.setAttribute("rq_pantalla", of.buscaRetornoUserProfile(company_number,viene));
            request.setAttribute("rq_companyNumber", company_number);
            request.setAttribute("rq_viene", viene);

            BussinessUnitDAO bud = new BussinessUnitDAO();
            request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

            request.getRequestDispatcher("WEB-INF/pages/jsp/process/general_process.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}