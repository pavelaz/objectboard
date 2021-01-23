package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.*;
import com.psg.objectboard.model.own.ownsEntity.classVO.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UserRoleServlet", urlPatterns = "/userrole")
public class UserRoleServlet extends HttpServlet {
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
        String user_email = null;
        if(request.getParameter("p_email")!=null){
            user_email=request.getParameter("p_email");
        }
        String user_unit = null;
        if(request.getParameter("p_unit")!=null){
            user_unit=request.getParameter("p_unit");
        }
        String user_proj = null;
        if(request.getParameter("p_proj")!=null){
            user_proj=request.getParameter("p_proj");
        }
        String user_email_name = null;
        if(request.getParameter("p_email_name")!=null){
            user_email_name=request.getParameter("p_email_name");
        }
        String user_unit_name = null;
        if(request.getParameter("p_unit_name")!=null){
            user_unit_name=request.getParameter("p_unit_name");
        }
        String user_proj_name = null;
        if(request.getParameter("p_proj_name")!=null){
            user_proj_name=request.getParameter("p_proj_name");
        }

        Integer[] rol_num = new Integer[5];
        rol_num[0]= 0;
        rol_num[1]= 1;
        rol_num[2]= 2;
        rol_num[3]= 3;
        rol_num[4]= 4;
        String[] rol_nom = new String[5];
        rol_nom[0]= "Select Rol";
        rol_nom[1]= "Super User";
        rol_nom[2]= "Administrator";
        rol_nom[3]= "User 1";
        rol_nom[4]= "User 2";

        String condicion = null;

        if (acciones.equals("save")){
            ArrayList<UserRoleVO> roles = null;
            UserRoleDAO cod= new UserRoleDAO();
            condicion = "masterUser_mu_email = '" + user_email + "' AND masterUser_bussinessUnit_bu_bis_code =" + user_unit;
            condicion = condicion + " AND project_pr_id_project = " + user_proj;
            cod.setDataUser(data_user);
            cod.setDataPassword(data_pasword);
            roles= cod.getListUserRoles(condicion);

            request.setAttribute("rq_unitName", user_unit_name.replace("-"," "));
            request.setAttribute("rq_projName", user_proj_name.replace("-"," "));
            request.setAttribute("rq_emailName", user_email_name.replace("-"," "));
            request.setAttribute("rq_rol", roles.get(0).getUmRole());
            request.setAttribute("rq_sta", roles.get(0).getUmStatus());
        }else{
            BussinessUnitDAO cod = new BussinessUnitDAO();
            ArrayList<BussinessUnitVO> unidades;
            cod.setDataUser(data_user);
            cod.setDataPassword(data_pasword);

            if (user_email != null && user_unit != "") {
                MasterUserDAO sod = new MasterUserDAO();
                MasterUserVO muser = null;
                sod.setDataUser(data_user);
                sod.setDataPassword(data_pasword);
                muser = sod.serchMasterUserDAO(user_email, user_unit);
                request.setAttribute("rq_emailName", muser.getMuName());
            }else {
                request.setAttribute("rq_emailName", "Email not selected");
            }

            ProjectDAO pro = new ProjectDAO();
            ArrayList<ProjectVO> project;
            pro.setDataUser(data_user);
            pro.setDataPassword(data_pasword);
            project = pro.getListProjects("");
            request.setAttribute("rq_projectos", project);

            unidades = cod.getListBussinessUnit("");
            request.setAttribute("rq_unidades", unidades);
        }

        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_companyNumber",company_number);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_unidad", user_unit);
        request.setAttribute("rq_project", user_proj);
        request.setAttribute("rq_email", user_email);
        request.setAttribute("rq_rolNum", rol_num);
        request.setAttribute("rq_rolNom", rol_nom);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/userRole.jsp").forward(request, response);
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
