package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.BussinessUnitDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.ProjectDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.ProjectVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet (name="ProyectServlet", urlPatterns = "/project")
public class ProjectServlet extends HttpServlet {
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
        String pantalla = " ";
        if(request.getParameter("p_pantalla")!=null){
            pantalla=request.getParameter("p_pantalla");
        }
        ProjectDAO prodao= new ProjectDAO();
        ProjectVO pvo = new ProjectVO();

        if (acciones.equals("create")){
            String none = null;
            if(request.getParameter("p_id")!=null) {
                none = request.getParameter("p_id");
                //pvo.setPrIdProject(Long.parseLong(none));
                request.setAttribute("rq_id", none);
            }
            if(request.getParameter("p_name")!=null) {
                none = request.getParameter("p_name");
                //pvo.setPrName(none);
                request.setAttribute("rq_name", none);
            }
            if(request.getParameter("p_note")!=null) {
                none = request.getParameter("p_note");
                //pvo.setPrNote(none);
                request.setAttribute("rq_note", none);
            }
        }

        ArrayList<ProjectVO> provo = null;
        prodao.setDataUser(data_user);
        prodao.setDataPassword(data_pasword);
        String condicion = "";

        if (acciones.equals("save")){
            String none = null;
            if(request.getParameter("p_id_1")!=null) {
                none = request.getParameter("p_id_1");
                //pvo.setPrIdProject(Long.parseLong(none));
                request.setAttribute("rq_id", none);
            }
            condicion = "pr_id_project=" + Long.parseLong(none);
            provo = prodao.getListProjects(condicion);
            request.setAttribute("rq_name", provo.get(0).getPrName());
            request.setAttribute("rq_note", provo.get(0).getPrNote());
            condicion = "pr_id_project NOT IN (" + Long.parseLong(none) + ")";
        }
        provo = prodao.getListProjects(condicion);
        request.setAttribute("rq_provo", provo);

        request.setAttribute("rq_companyName", company_name);
        request.setAttribute("rq_userName", user_name);
        request.setAttribute("rq_pantalla", pantalla);
        request.setAttribute("rq_acciones", acciones);
        request.setAttribute("rq_companyNumber", company_number);

        BussinessUnitDAO bud = new BussinessUnitDAO();
        request.setAttribute("rq_format", bud.searchLogoName(company_number,data_user,data_pasword,1));

        request.getRequestDispatcher("/WEB-INF/pages/jsp/master/projects.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
