package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.controller.MasterUserProfileController;
import com.psg.objectboard.controller.common.FilesController;
import com.psg.objectboard.model.datatransferobject.MasterUserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;

@WebServlet (name="MasterUserProfileServlet", urlPatterns = "/masteruser")
public class MasterUserProfileServlet extends HttpServlet {

    public MasterUserProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        final HttpSession objSesion = request.getSession();

        final String company_name = (String) objSesion.getAttribute("companyName");
        final String company_number = (String) objSesion.getAttribute("companyNumber");
        final String user_email = (String) objSesion.getAttribute("userEmail");
        final String user_name = (String) objSesion.getAttribute("userName");
        MasterUserDto masterUserDto = null;

        String metodo = "0";
        if(request.getParameter("p_metodo")!=null){
            metodo=request.getParameter("p_metodo");
        }

        if (request.getMethod().equals("GET")) {
            if (company_number != null) {
                MasterUserProfileController controller = new MasterUserProfileController();
                masterUserDto = controller.DetailModuleProfileUser(Long.parseLong(company_number), user_email);
                request.setAttribute("rq_masterUserDto", masterUserDto);
                request.setAttribute("rq_companyName", company_name);
                request.setAttribute("rq_companyNumber", company_number);
                request.setAttribute("rq_userName", user_name);
            }
            System.out.println("MasterUserServlet metodo 'GET', muestra informacion de formulario");
        }

        else if (request.getMethod().equals("OPTIONS")) {//EL METODO OPCIONS, FUNCIONA PARA ENVIOS DE FORMULARIOS CON ARCHIVOS Y PARA EL ESTADO DE (MODIFICAR).

            MasterUserDto master_user_dto = new MasterUserDto();
            MasterUserProfileController masterUserProfileController = new MasterUserProfileController();
            FilesController filesController = new FilesController();

            if (company_number.equals("1")) {/*Services PVSoft*/
                System.out.println("dentro de servlet  Empresa Services PVSoft");
                if ((request.getParameter("p_muName")) != null && /*ok*/
                    (request.getParameter("p_muRepeat")) != null && /*ok*/
                    (request.getParameter("p_muQuestion")) != null && /*ok*/
                    (request.getParameter("p_muAnswer")) != null && /*ok*/
                    (request.getParameter("p_muSectionTime")) != null && /*ok*/
                    (request.getParameter("p_muStatus")) != null && /*ok*/
                    (request.getParameter("p_muEffectiveDays")) != null && /*ok*/
                    (request.getParameter("p_muEmailConfirm")) != null && /*ok*/
                    (request.getParameter("p_muGender")) != null) {/*ok*/

                    master_user_dto.setBussinessUnitBuBisCode(Long.parseLong(company_number));
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
                }
            }else { /*Client Companies*/
                if ((request.getParameter("p_muName")) != null && /*ok*/
                    (request.getParameter("p_muRepeat")) != null && /*ok*/
                    (request.getParameter("p_muQuestion")) != null && /*ok*/
                    (request.getParameter("p_muAnswer")) != null && /*ok*/
                    (request.getParameter("p_muGender")) != null) {/*ok*/

                    master_user_dto.setBussinessUnitBuBisCode(Long.parseLong(company_number));
                    master_user_dto.setMuEmail(user_email);
                    master_user_dto.setMuName(request.getParameter("p_muName"));
                    master_user_dto.setMuPassword(request.getParameter("p_muRepeat"));
                    master_user_dto.setMuQuestion(request.getParameter("p_muQuestion"));
                    master_user_dto.setMuAnswer(request.getParameter("p_muAnswer"));
                    master_user_dto.setMuGender(request.getParameter("p_muGender"));
                }
            }

            /*Start*********************AddPhoto to Object master_user_dto *********/
            String photo = filesController.updateFile(request, (1024 * 1024 * 1),"p_file"); // 1024 * 1024 * 1,= 1 MB
            System.out.println("Dentro de la servlet en via al controlador photo: " + photo);
            master_user_dto.setRoutePhoto(photo);
            /*End*********************AddPhoto to Object master_user_dto *********/

            masterUserProfileController.updateMasterUser(master_user_dto);

            System.out.println("MasterUserServlet metodo 'OPTIONS', recibe objeto de JSP");
        }

        if (!metodo.equals("0"))
            request.getRequestDispatcher("WEB-INF/pages/jsp/customers/masterUserProfile.jsp").forward(request, response);
        else {
            String none = "?p_country_number=" +
                    masterUserDto.getCityStatesCountryCoCountryCode() + "&p_state_number=" +
                    masterUserDto.getCityStatesStStateCode() + "&p_city_number=" +
                    masterUserDto.getCityCiCityCode();
            request.getRequestDispatcher("WEB-INF/pages/jsp/customers/masterUserProfile.jsp" + none).forward(request, response);
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

    @Override
    public void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
