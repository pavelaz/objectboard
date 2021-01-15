package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessTypeDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessTypeVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "BussinessUnitServlet", urlPatterns = "/bussinessunit")
@MultipartConfig
public class BussinessUnitServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String company_name = (String)objSesion.getAttribute("companyName");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String acciones = null;
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String unit_number = null;
        if(request.getParameter("p_unit")!=null){
            unit_number=request.getParameter("p_unit");
        }

        BussinessTypeDAO cod = new BussinessTypeDAO();
        ArrayList<BussinessTypeVO> tipos;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);
        tipos = cod.getListBussinessType();

        String unit_name = null,
             fed_number = null,
             pro_number = null,
             unit_email = null,
             unit_satatus = null,
             unit_phone = null,
             unit_zipcode = null,
             unit_supercode = null,
             unit_type = null,
             unit_address = null,
             unit_webpage = null,
             unit_admincode = null,
             unit_user1code = null,
             unit_user2code = null,
             unit_logo_name = null,
             unit_logo_name_ant = null,
             unit_logo_dir = null;
        OtherFunctions of = new OtherFunctions();

        if (acciones.equals("save")) {
            BussinessUnitVO buvo = new BussinessUnitVO();
            BussinessUnitDAO budo = null;
            budo.setDataUser(data_user);
            budo.setDataPassword(data_pasword);
            buvo = budo.serchBussinessUnitDAO(unit_number);

            if(request.getParameter("p_unit_name")!=null)
                unit_name=request.getParameter("p_unit_name");
            else
                unit_name= buvo.getBuName();
            if(request.getParameter("p_fed_number")!=null)
                fed_number=request.getParameter("p_fed_number");
            else
                fed_number= buvo.getBuFederalNumber();
            if(request.getParameter("p_pro_number")!=null)
                pro_number=request.getParameter("p_pro_number");
            else
                pro_number= buvo.getBuProvincialNumber();
            if(request.getParameter("p_email")!=null)
                unit_email=request.getParameter("p_email");
            else
                unit_email=buvo.getBuEmail();
            if(request.getParameter("p_status")!=null)
                unit_satatus=request.getParameter("p_status");
            else
                unit_satatus=buvo.getBuStatus();
            if(request.getParameter("p_phone")!=null)
                unit_phone=request.getParameter("p_phone");
            else
                unit_phone=buvo.getBuPhone();
            if(request.getParameter("p_zipcode")!=null)
                unit_zipcode=request.getParameter("p_zipcode");
            else
                unit_zipcode=buvo.getBuZipCode();
            if(request.getParameter("p_supercode")!=null)
                unit_supercode=request.getParameter("p_supercode");
            else
                unit_supercode=buvo.getBuSuperCode();
            if(request.getParameter("p_type")!=null)
                unit_type=request.getParameter("p_type");
            else
                unit_type=String.valueOf(buvo.getBussinessTypeBtCodeType());
            if(request.getParameter("p_webpage")!=null)
                unit_webpage=request.getParameter("p_webpage");
            else
                unit_webpage=buvo.getBuWebPage();
            if(request.getParameter("p_address")!=null)
                unit_address=request.getParameter("p_address");
            else
                unit_address=buvo.getBuAddress();
            if(request.getParameter("p_admincode")!=null)
                unit_admincode=request.getParameter("p_admincode");
            else
                unit_admincode=buvo.getBuAdminCode();
            if(request.getParameter("p_user1code")!=null)
                unit_user1code=request.getParameter("p_user1code");
            else
                unit_user1code=buvo.getBuUser1Code();
            if(request.getParameter("p_user2code")!=null)
                unit_user2code=request.getParameter("p_user2code");
            else
                unit_user2code=buvo.getBuUser2Code();
            if(request.getParameter("p_file")!=null)
                unit_logo_name=request.getParameter("p_file");
            else
                unit_logo_name=buvo.getBuLogoName();

            if (Integer.parseInt(unit_number) == 1){
                unit_logo_dir = "/" + of.searchLink("3") + "/img/";
                //unit_logo_dir = "/complements/img/";
            }else{
                if (unit_logo_name.equals("favicon2.png")){
                    unit_logo_dir = "/" + of.searchLink("3") + "/img/";
                    //unit_logo_dir = "/complements/img/";
                }else {
                    if (Integer.parseInt(unit_number) < 10) {
                        unit_logo_dir = of.searchLink("5") + "0" + unit_number + "/";
                        //unit_logo_dir = "/" + of.searchLink("3") + "/img/logos/0" + unit_number + "/";
                        //unit_logo_dir = "/complements/img/logos/0" + unit_number + "/";
                    } else {
                        unit_logo_dir = of.searchLink("5") + unit_number + "/";
                        //unit_logo_dir = "/" + of.searchLink("5") + "/img/logos/" + unit_number + "/";
                        //unit_logo_dir = "/complements/img/logos/" + unit_number + "/";
                    }
                }
            }
            if(request.getParameter("p_file_ant")!=null)
                unit_logo_name_ant=request.getParameter("p_file_ant");
            else
                unit_logo_name_ant=buvo.getBuLogoName();
        }else{
            OtherFunctions odf = new OtherFunctions();
            if(request.getParameter("p_unit_name")!=null){
                unit_name=request.getParameter("p_unit_name");
            }
            if(request.getParameter("p_fed_number")!=null){
                fed_number=request.getParameter("p_fed_number");
            }
            if(request.getParameter("p_pro_number")!=null){
                pro_number=request.getParameter("p_pro_number");
            }
            if(request.getParameter("p_email")!=null){
                unit_email=request.getParameter("p_email");
            }
            if(request.getParameter("p_status")!=null){
                unit_satatus=request.getParameter("p_status");
            }
            if(request.getParameter("p_phone")!=null){
                unit_phone=request.getParameter("p_phone");
            }
            if(request.getParameter("p_zipcode")!=null){
                unit_zipcode=request.getParameter("p_zipcode");
            }
            if(request.getParameter("p_supercode")!=null){
                unit_supercode=request.getParameter("p_supercode");
            }else{
                unit_supercode = odf.generateRandomString(12);
            }
            if(request.getParameter("p_type")!=null){
                unit_type=request.getParameter("p_type");
            }
            if(request.getParameter("p_webpage")!=null){
                unit_webpage=request.getParameter("p_webpage");
            }
            if(request.getParameter("p_address")!=null){
                unit_address=request.getParameter("p_address");
            }
            if(request.getParameter("p_admincode")!=null){
                unit_admincode=request.getParameter("p_admincode");
            }else{
                unit_admincode= odf.generateRandomString(12);
            }
            if(request.getParameter("p_user1code")!=null){
                unit_user1code = request.getParameter("p_user1code");
            }else{
                unit_user1code = odf.generateRandomString(12);
            }
            if(request.getParameter("p_user2code")!=null){
                unit_user2code=request.getParameter("p_user2code");
            }else{
                unit_user2code = odf.generateRandomString(12);
            }
            if(request.getParameter("p_file")!=null){
                unit_logo_name=request.getParameter("p_file");
            }
        }

        request.setAttribute("rq_companyNumber", company_number);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_tipos", tipos);
        request.setAttribute("rq_unitName", unit_name);
        request.setAttribute("rq_unitNumber", unit_number);
        request.setAttribute("rq_fedNumber", fed_number);
        request.setAttribute("rq_proNumber", pro_number);
        request.setAttribute("rq_unitEmail", unit_email);
        request.setAttribute("rq_unitPhone", unit_phone);
        request.setAttribute("rq_unitStatus", unit_satatus);
        request.setAttribute("rq_unitPhone", unit_phone);
        request.setAttribute("rq_unitZipcode", unit_zipcode);
        request.setAttribute("rq_unitSupercode", unit_supercode);
        request.setAttribute("rq_unitType", unit_type);
        request.setAttribute("rq_unitWebpage", unit_webpage);
        request.setAttribute("rq_unitAddress", unit_address);
        request.setAttribute("rq_unitAdminCode", unit_admincode);
        request.setAttribute("rq_unitUser1Code", unit_user1code);
        request.setAttribute("rq_unitUser2Code", unit_user2code);
        request.setAttribute("rq_unitLogoName", unit_logo_name);
        request.setAttribute("rq_unitLogoDir", unit_logo_dir);
        request.setAttribute("rq_unitLogoNameAnt", unit_logo_name_ant);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/bussinessUnit.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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