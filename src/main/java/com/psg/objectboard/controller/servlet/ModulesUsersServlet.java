package com.psg.objectboard.controller.servlet;

import com.psg.objectboard.model.own.ownsEntity.classDAO.DischargeDAO;
import com.psg.objectboard.model.own.ownsEntity.classDAO.UserRoleDAO;
import com.psg.objectboard.model.own.ownsEntity.classVO.DischargeVO;
import com.psg.objectboard.model.own.ownsEntity.classVO.UserRoleVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ModulesUsersServlet", urlPatterns = "/modulesusers")
public class ModulesUsersServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {

        String company_number = request.getParameter("p_company_number");
        String company_name = request.getParameter("p_company_name");
        String user_email = request.getParameter("p_email");
        String user_name = request.getParameter("p_name");
        String user_password = request.getParameter("p_password");
        String user_time_seccion = request.getParameter("p_seccion_time");
        String user_data_password = request.getParameter("p_data_password");
        String user_data_user = request.getParameter("p_data_user");
        String company_logo_name = request.getParameter("p_logo_name");
        String company_logo_dir = request.getParameter("p_logo_dir");

        Boolean existe_master = null;

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(30*60); // 30 minutos

        // Variables Comunes de seccion
        session.setAttribute("companyNumber", company_number );
        session.setAttribute("companyName", company_name );
        session.setAttribute("userEmail", user_email );
        session.setAttribute("userName", user_name );
        session.setAttribute("userPassword", user_password );
        session.setAttribute("dataPassword", user_data_password );
        session.setAttribute("dataUser", user_data_user );
        session.setAttribute("companyLogoName", company_logo_name );
        session.setAttribute("companyLogoDirection", company_logo_dir );


        System.out.println("Poniendo usuario en sesion ...");

        // si el usuario en de la cia master es true y si es otra es false;
        assert company_number != null;
        if(company_number.equals("1")) {
            // Aqui falta incluir la busqueda del roll del usuario dentro de la master
            existe_master = true;
        } else {
            existe_master = false;
        }
        // Aqui fija el tiempo de duraccion de la seccion segun el usuario, en segundos.
        session.setMaxInactiveInterval(Integer.parseInt(user_time_seccion));
        //session.setMaxInactiveInterval(60);

        // aqui va el bloque que verifica los modulos activos del usuario cliente para mostrarlos
        // valida si la cia tiene licencias activas para el modulo board
        Boolean modulo_board_cia = false;
        //Boolean modulo_board_user = false;
        ArrayList<DischargeVO> consulta = new ArrayList<DischargeVO>();
        DischargeDAO did = new DischargeDAO();
        did.setDataUser(user_data_user);
        did.setDataPassword(user_data_password );
        String condicion = "bussinessUnit_bu_bis_code = "+Integer.parseInt(company_number);
               condicion = condicion + " and project_pr_id_project = 1";
        consulta = did.getListDischargeActive(condicion);
        if (consulta.size()!= 0)
            modulo_board_cia = true;
        // valida el role y el status del rol del usuario para el modulo Board
        condicion = "masterUser_mu_email = '" + user_email +
                    "' and masterUser_bussinessUnit_bu_bis_code = " + company_number +
                    " and project_pr_id_project = 1 ";
        ArrayList<UserRoleVO> roles = new ArrayList<UserRoleVO>();
        UserRoleDAO urd = new UserRoleDAO();
        urd.setDataUser(user_data_user);
        urd.setDataPassword(user_data_password );
        roles = urd.getListUserRoles(condicion);
        Integer user_board_role = null;
        Boolean user_role_status_board = null;
        if (roles.size() == 0){
            user_board_role = 4;
            user_role_status_board = true;
        }else{
            if (roles.get(0).getUmStatus().equals("T"))
                user_role_status_board = true;
            else
                user_role_status_board = false;
            user_board_role = roles.get(0).getUmRole();
        }

        Integer otra_razon = 0;
        if (Integer.parseInt(company_number) == 1 &&
           (!user_role_status_board || user_board_role != 1)) {
            otra_razon = 1;
        }
        String none = String.valueOf(user_board_role);

        session.setAttribute("userBoardRole", none);

        request.setAttribute("rq_existeMaster", existe_master);
        request.setAttribute("rq_moduloBoardCia", modulo_board_cia);
        request.setAttribute("rq_userRoleStatusBoard", user_role_status_board);
        request.setAttribute("rq_userBoardRole", user_board_role);
        request.setAttribute("rq_otraRazon", otra_razon);

        request.getRequestDispatcher("/WEB-INF/pages/jsp/process/modules_users.jsp").forward(request, response);
    }

    /*******Metodos Get, Post, Put y Delete********/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en el Module User Servlet");
        }
    }
}
