package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.DischargeDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ProjectDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.BussinessUnitVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.DischargeVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ProjectVO;
import com.psg.objectboard.model.service.Other.DateFunctions;
import com.psg.objectboard.model.service.Other.OtherFunctions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "DischargeServlet", urlPatterns = "/discharge")
public class DischargeServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession objSesion = request.getSession();
        String company_name = (String)objSesion.getAttribute("companyName");
        String company_number = (String) objSesion.getAttribute("companyNumber");
        String user_name = (String)objSesion.getAttribute("userName");
        String data_user = (String)objSesion.getAttribute("dataUser");
        String data_pasword = (String)objSesion.getAttribute("dataPassword");

        String acciones = null;
        if(request.getParameter("p_acciones")!=null){
            acciones=request.getParameter("p_acciones");
        }
        String dischar = null;
        if(request.getParameter("p_discharge")!=null){
            dischar=request.getParameter("p_discharge");
        }
        String unidad = null;
        if(request.getParameter("p_unidad")!=null){
            unidad=request.getParameter("p_unidad");
        }
        String project = null;
        if(request.getParameter("p_project")!=null){
            project=request.getParameter("p_project");
        }

        ArrayList<BussinessUnitVO> units = null;
        BussinessUnitDAO bd = new BussinessUnitDAO();
        bd.setDataUser(data_user);
        bd.setDataPassword(data_pasword);
        units = bd.getListBussinessUnit("");

        ArrayList<ProjectVO> projects = null;
        ProjectDAO pd = new ProjectDAO();
        pd.setDataUser(data_user);
        pd.setDataPassword(data_pasword);
        projects = pd.getListProjects("");

        if (acciones.equals("save")){
            DischargeDAO cod= new DischargeDAO();
            ArrayList<DischargeVO> discharge = null;
            DateFunctions df = new DateFunctions();
            String condicion = "di_license_num = " + dischar;
            cod.setDataUser(data_user);
            cod.setDataPassword(data_pasword);
            discharge= cod.getListDischarge(condicion);

            String fech = discharge.get(0).getDiStartDate();
            String fecha_desde = df.parseFecha_2(fech);
                   fech = discharge.get(0).getDiEndDate();
            String fecha_hasta = df.parseFecha_2(fech);

            request.setAttribute("rq_discharge", discharge);
            request.setAttribute("rq_fechaDesde", fecha_desde);
            request.setAttribute("rq_fechaHasta", fecha_hasta);
        }else{
            OtherFunctions od = new OtherFunctions();
            String license_code = od.generateRandomString(16);
            DateFunctions df = new DateFunctions();
            String fecha_hoy = df.fechaFull(10);

            request.setAttribute("rq_licenseCode", license_code);
            request.setAttribute("rq_fechaHoy", fecha_hoy);
        }

        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber",company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_units", units);
        request.setAttribute("rq_projects", projects);
        request.setAttribute("rq_dischar", dischar);
        request.setAttribute("rq_unidad", unidad);
        request.setAttribute("rq_project", project);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/discharge.jsp").forward(request, response);
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
