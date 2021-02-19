package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.HeadersSurveyDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.HeadersSurveyVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
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

@WebServlet(name = "HeaderPollsServlet", urlPatterns = "/headerpolls")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 10,  // 10 MB
        maxFileSize         = 1024 * 1024 * 100, // 100 MB
        maxRequestSize      = 1024 * 1024 * 150 // 150 MB
)
public class HeaderPollsServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String)objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String acciones = "consult";
        if(request.getParameter("p_acciones")!=null) {
            acciones = request.getParameter("p_acciones");
        }
        String column = "0";
        if(request.getParameter("p_column")!=null) {
            column = request.getParameter("p_column");
        }

        if (acciones.equals("create")) {
            String referencia = "";
            if (request.getParameter("p_refe") != null) {
                referencia = request.getParameter("p_refe");
            } else {
                OtherFunctions of = new OtherFunctions();
                referencia = of.generateRandomString(8);
            }
            request.setAttribute("rq_refe", referencia);

            String nombre = "";
            if (request.getParameter("p_name") != null) {
                nombre = request.getParameter("p_name");
            }
            request.setAttribute("rq_name", nombre);
        }

        String code = null;
        if (acciones.equals("save")){
            if(request.getParameter("p_code")!=null) {
                code = request.getParameter("p_code");
            }
            String name = null;
            if(request.getParameter("p_names")!=null) {
                name = request.getParameter("p_names");
            }
            String refe = "";
            if(request.getParameter("p_refes")!=null) {
                refe = request.getParameter("p_refes");
            }

            request.setAttribute("rq_code", code);
            request.setAttribute("rq_name", name);
            request.setAttribute("rq_refe", refe);
        }

        HeadersSurveyDAO cod= new HeadersSurveyDAO();
        ArrayList<HeadersSurveyVO> polls = null;
        cod.setDataUser(data_user);
        cod.setDataPassword(data_pasword);

        String condicion = null;
        if (acciones.equals("save")){
            condicion = "bussinessUnit_bu_bis_code = " +
                    Integer.parseInt(company_number) + " AND survey_code =" + code ;
            polls = cod.getListHeadersSurvey(condicion);
            request.setAttribute("rq_iname_an", polls.get(0).getSurveyImageName());

            condicion = "bussinessUnit_bu_bis_code = " +
                    Integer.parseInt(company_number) + " AND survey_code NOT IN (" + code + ")";
        }else{
            condicion = "bussinessUnit_bu_bis_code = " +
                    Integer.parseInt(company_number);
        }

        polls = cod.getListHeadersSurvey(condicion);
        if (polls.size() !=0){
            DateFunctions df = new DateFunctions();
            for (int x=0; x < polls.size(); x++){
                polls.get(x).setDateCreation(df.parseFecha_2(polls.get(x).getDateCreation()));
                polls.get(x).setDateLastModification(df.parseFecha_2(polls.get(x).getDateLastModification()));
            }
        }

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_numFilas", polls.size());
        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_column", column);
        //request.setAttribute("rq_companyLogoName", company_logo_name);
        //request.setAttribute("rq_companyLogoDirection", company_logo_dir);
        request.setAttribute("rq_companyNumber", company_number);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.setAttribute("rq_polls", polls);

        //  prueba objeto json
        //OtherFunctions odf = new OtherFunctions();
        //odf.crearJson(polls);
        //

        request.getRequestDispatcher("/WEB-INF/pages/jsp/customers/header_polls.jsp").forward(request, response);
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